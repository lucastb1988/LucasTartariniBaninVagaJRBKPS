package br.com.lucas.pharma.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

import br.com.lucas.pharma.domain.Funcionario;
import br.com.lucas.pharma.util.HibernateUtil;

/**
 * @author Lucas.
 * 
 *         Classe que representa um DAO Funcionario que extende da classe Generica DAO.
 * 
 */
public class FuncionarioDAO extends GenericDAO<Funcionario> {

	/**
	 * Método para listar a entidade Funcionário capturando na database do banco de dados seus objetos e listando ordenadamente pelo nome do cliente.
	 * 
	 * @return uma lista da entidade com seus objetos.
	 */
	@SuppressWarnings("unchecked")
	public List<Funcionario> listarOrdenado() {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession(); // abre a sessão
		try{
			Criteria consulta = sessao.createCriteria(Funcionario.class); //API Criteria para consultar
			
			// alias geralmente é criado para navegar dentro da composição e dentro da composição da composição
			consulta.createAlias("pessoa", "p"); // ensinando ao Hibernate que existe um relacionamento chamado pessoa e será chamado de p
			
			consulta.addOrder(Order.asc("p.nome")); 
			// passando p.nome para navegar dentro da herança(neste exemplo está sendo requisitado que funcionario seja ordenado através de pessoa/nome
			// addOrder - comando do Criteria para ordenação (foi implementado este campo para ordenar o SelectOneMenu/ComboBox dos xhtmls
			
			List<Funcionario> resultado = consulta.list(); //resultará em toda lista que possui
			
			return resultado;
			
		} catch (RuntimeException ex) {
			throw ex;
		} finally {
			sessao.close();
		}
	}
}