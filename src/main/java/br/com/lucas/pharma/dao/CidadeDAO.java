package br.com.lucas.pharma.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.lucas.pharma.domain.Cidade;
import br.com.lucas.pharma.util.HibernateUtil;

/**
 * @author Lucas.
 * 
 *         Classe que representa um DAO Cidade que extende da classe Generica DAO.
 * 
 */
public class CidadeDAO extends GenericDAO<Cidade> { 

	/**
	 * Método para buscar por estado informando uma cidade capturando na database do banco de dados.
	 * 
	 * * @param estadoCodigo o estadoCodigo(id) da Classe Estado a ser buscada
	 * 
	 * @return uma lista de cidades referente ao estado buscado.
	 */
	// método para buscar as cidades filtradas de acordo com o estado selecionado no SelectOneMenu/ComboBox nos xhtmls
	// método semelhante ao do genericDao porém este é especifico para Cidade
	@SuppressWarnings("unchecked")
	public List<Cidade> buscarPorEstado(Long estadoCodigo) { // receber parametro da chave primaria do estado (Long Codigo)
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession(); // abre a sessão
		try{
			Criteria consulta = sessao.createCriteria(Cidade.class); // API Criteria para consultar a classe especifica necessária
			consulta.add(Restrictions.eq("estado.codigo", estadoCodigo)); // Restrição para encontrar chave primaria - procurar dentro do property name que é a chave primaria do estado o value que é o parametro da chave primaria
			
			consulta.addOrder(Order.asc("nome")); // addOrder - comando do Criteria para ordenação (foi implementado este campo para ordenar o SelectOneMenu/ComboBox dos xhtmls)
			
			List<Cidade> resultado = consulta.list(); // resultará em toda lista que possui
			
			return resultado;
			
		} catch (RuntimeException ex) {
			throw ex;
		} finally {
			sessao.close();
		}
	}
}