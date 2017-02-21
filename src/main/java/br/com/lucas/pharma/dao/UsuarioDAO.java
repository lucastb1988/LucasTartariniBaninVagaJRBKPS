package br.com.lucas.pharma.dao;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.lucas.pharma.domain.Usuario;
import br.com.lucas.pharma.util.HibernateUtil;

/**
 * @author Lucas.
 * 
 *         Classe que representa um DAO Usuário que extende da classe Generica DAO.
 * 
 */
public class UsuarioDAO extends GenericDAO<Usuario> {
	
	/**
	 * Método para autenticar a entidade Usuário capturando na database do banco de dados seus objetos.
	 * 
	 * * @param cpf o cpf informado para captura
	 * * @param senha a senha informada para captura
	 * 
	 * @return um usuário autenticado.
	 */
	public Usuario autenticar(String cpf, String senha) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		
		try {
			Criteria consulta = sessao.createCriteria(Usuario.class);
			consulta.createAlias("pessoa", "p"); // navegando na composição do usuario para pessoa (para capturar o cpf)
					
			consulta.add(Restrictions.eq("p.cpf", cpf));
			
			SimpleHash hash = new SimpleHash("md5", senha); // criptografando a senha do usuário e armazenando em md5
			consulta.add(Restrictions.eq("senha", hash.toHex())); // utiliza-se toHex para criptografar e armazenar na propriedade desejada (md5)
			
			Usuario resultado = (Usuario) consulta.uniqueResult();
			
			return resultado;
			
		} catch(RuntimeException ex) {
			throw ex;
		} finally {
			sessao.close();
		}
	}
}