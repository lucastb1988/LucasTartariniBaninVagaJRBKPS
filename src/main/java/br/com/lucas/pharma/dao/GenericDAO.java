package br.com.lucas.pharma.dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.lucas.pharma.util.HibernateUtil;

/**
 * @author Lucas.
 * 
 *         Classe que representa o DAO Genérico do projeto.
 * 
 */
public class GenericDAO<Entidade> { //Entidade será substituido por um dos domains

	private Class<Entidade> classe;	

	@SuppressWarnings("unchecked")
	public GenericDAO() {
		this.classe = (Class<Entidade>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		// receberá o valor da Classe Filha + o valor da Classe Generica + resultado dentro da Classe Filha (neste caso <Estado>)
	}

	/**
	 * Método para salvar uma entidade na database do banco de dados.
	 * 
	 * @param entidade a entidade a ser salva no banco de dados.	
	 */
	public void salvar(Entidade entidade) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession(); // abre a sessão com o bd
		Transaction transacao = null;//Transaction - incluir, editar ou excluir é recomendavel utilizar transações

		try {
			transacao = sessao.beginTransaction(); //iniciar a transação
			sessao.save(entidade); //realizar o trabalho (neste caso irá salvar uma entidade genérica aberta)
			transacao.commit(); //confirmar a transação
			
		} catch (RuntimeException ex) {
			if(transacao != null) {
				transacao.rollback();
			}
			throw ex;
		} finally {
			sessao.close();
		}
	}	

	/**
	 * Método para listar uma entidade capturando na database do banco de dados seus objetos.
	 * 
	 * @return uma lista da entidade com seus objetos.
	 */
	@SuppressWarnings("unchecked")
	public List<Entidade> listar() {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession(); // abre a sessão com o bd
		try{
			Criteria consulta = sessao.createCriteria(classe); //API Criteria para consulta de classe generica
			List<Entidade> resultado = consulta.list(); //resultará em toda lista que possui
			
			return resultado;
			
		} catch (RuntimeException ex) {
			throw ex;
		} finally {
			sessao.close();
		}
	}
	
	/**
	 * Método para listar uma entidade capturando na database do banco de dados seus objetos e listando por campo de ordenação.
	 * 
	 * * @param campoOrdenacao o campoOrdenacao ordenando em ordem crescente
	 * 
	 * @return uma lista da entidade com seus objetos.
	 */
	@SuppressWarnings("unchecked")
	public List<Entidade> listar(String campoOrdenacao) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession(); // abre a sessão com o bd
		try{
			Criteria consulta = sessao.createCriteria(classe); //API Criteria para consultar
			
			consulta.addOrder(Order.asc(campoOrdenacao)); //addOrder - comando do Criteria para ordenação (foi implementado este campo para ordenar o SelectOneMenu/ComboBox dos xhtmls)
			
			List<Entidade> resultado = consulta.list(); //resultará em toda lista que possui
			
			return resultado;
			
		} catch (RuntimeException ex) {
			throw ex;
		} finally {
			sessao.close();
		}
	}
	
	/**
	 * Método para buscar uma entidade capturando na database do banco de dados.
	 * 
	 * * @param codigo o codigo(id) da entidade a ser buscada
	 * 
	 * @return uma entidade buscada no banco de dados.
	 */
	@SuppressWarnings("unchecked")
	public Entidade buscar(Long codigo) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession(); // abre a sessão com o bd
		try{
			Criteria consulta = sessao.createCriteria(classe); //API Criteria para consultar
			consulta.add(Restrictions.idEq(codigo)); //utiliza-se restrições para verificar se o parâmetro código é igual ao código passado 
			Entidade resultado = (Entidade) consulta.uniqueResult(); //uniqueResult - resultará na busca de um resultado unico 
			
			return resultado;
			
		} catch (RuntimeException ex) {
			throw ex;
		} finally {
			sessao.close();
		}
	}
	
	/**
	 * Método para excluir uma entidade da database do banco de dados.
	 * 
	 * @param entidade a entidade a ser excluida no banco de dados.	
	 */
	public void excluir(Entidade entidade) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession(); // abre a sessão com o bd
		Transaction transacao = null;//Transaction - incluir, editar ou excluir é recomendavel utilizar transações

		try {
			transacao = sessao.beginTransaction(); //iniciar a transação
			sessao.delete(entidade); //realizar o trabalho (neste caso irá excluir uma entidade genérica aberta)
			transacao.commit(); //confirmar a transação
			
		} catch (RuntimeException ex) {
			if(transacao != null) {
				transacao.rollback();
			}
			throw ex;
		} finally {
			sessao.close();
		}
	}	
	
	/**
	 * Método para editar uma entidade na database do banco de dados.
	 * 
	 * @param entidade a entidade a ser editada no banco de dados.	
	 */
	public void editar(Entidade entidade) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession(); // abre a sessão com o bd
		Transaction transacao = null;//Transaction - incluir, editar ou excluir é recomendavel utilizar transações

		try {
			transacao = sessao.beginTransaction(); //iniciar a transação
			sessao.update(entidade); //realizar o trabalho (neste caso irá editar uma entidade genérica aberta)
			transacao.commit(); //confirmar a transação
			
		} catch (RuntimeException ex) {
			if(transacao != null) {
				transacao.rollback();
			}
			throw ex;
		} finally {
			sessao.close();
		}
	}	
	
	/**
	 * Método para salvar/editar uma entidade na database do banco de dados.
	 * 
	 * @param entidade a entidade a ser salva/editada no banco de dados.	
	 */
	public void merge(Entidade entidade) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession(); // abre a sessão com o bd
		Transaction transacao = null;//Transaction - para incluir, editar ou excluir é recomendavel utilizar transações

		try {
			transacao = sessao.beginTransaction(); //iniciar a transação			
			sessao.merge(entidade); //realizar o trabalho (neste caso irá editar uma entidade já criada ou salvar uma nova entidade genérica aberta)		
			transacao.commit(); //confirmar a transação			
			
		} catch (RuntimeException ex) {
			if(transacao != null) {
				transacao.rollback();
			}
			throw ex;
		} finally {
			sessao.close();
		}
	}	
}