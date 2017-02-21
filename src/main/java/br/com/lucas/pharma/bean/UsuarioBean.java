package br.com.lucas.pharma.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.lucas.pharma.dao.PessoaDAO;
import br.com.lucas.pharma.dao.UsuarioDAO;
import br.com.lucas.pharma.domain.Pessoa;
import br.com.lucas.pharma.domain.Usuario;

/**
 * @author Lucas.
 * 
 *         Classe que representa um Bean para Usuário.
 * 
 */
@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class UsuarioBean implements Serializable {

	private Usuario usuario;
	private List<Usuario> usuarios;
	private List<Pessoa> pessoas;

	/**
	 * Método para listar a entidade Usuário na view utilizando @PostConstruct para criar a lista ao carregar a página.	 
	 */
	@PostConstruct
	public void listar() {
		try {
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			usuarios = usuarioDAO.listar();
			
		}catch(RuntimeException ex) {
			ex.printStackTrace();
			Messages.addGlobalError("Ocorreu um erro ao tentar listar os usuários");
		}
	}

	/**
	 * Método para forçar o recarregamento das entidades Usuário e Pessoa na tela ao salvar uma cidade.  
	 */
	public void novo() {
		try {
			usuario = new Usuario();

			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoas = pessoaDAO.listar();

		}catch(RuntimeException ex) {
			ex.printStackTrace();
			Messages.addGlobalError("Ocorreu um erro ao tentar gerar um novo usuário");
		}
	}

	/**
	 * Método para salvar um usuário no banco de dados.  
	 */
	public void salvar() {
		try {
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			usuarioDAO.merge(usuario);

			usuarios = usuarioDAO.listar();

			novo();

			Messages.addGlobalInfo("Usuário salvo com sucesso");
		}catch(RuntimeException ex) {
			ex.printStackTrace();
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar o usuário");
		}
	}

	/**
	 * Método para excluir um usuário no banco de dados.  
	 */
	public void excluir(ActionEvent evento) {
		try {
			usuario = (Usuario) evento.getComponent().getAttributes().get("usuarioSelecionado");

			UsuarioDAO usuarioDAO = new UsuarioDAO();
			usuarioDAO.excluir(usuario);

			usuarios = usuarioDAO.listar();
			
			Messages.addGlobalInfo("Usuário removido com sucesso");
		}catch(RuntimeException ex) {
			ex.printStackTrace();
			Messages.addGlobalError("Ocorreu um erro ao tentar excluir o usuário");
		}
	}

	/**
	 * Método para editar um usuário no banco de dados.  
	 */
	public void editar(ActionEvent evento) {
		try {
			usuario = (Usuario) evento.getComponent().getAttributes().get("usuarioSelecionado");

			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoas = pessoaDAO.listar();

			Messages.addGlobalInfo("Usuário editado com sucesso");
		}catch(RuntimeException ex) {
			ex.printStackTrace();
			Messages.addGlobalError("Ocorreu um erro ao tentar editar o usuário");
		}
	}

	/**
	 * @return the usuario
	 */
	public Usuario getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario the usuario to set
	 */
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	/**
	 * @return the usuarios
	 */
	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	/**
	 * @param usuarios the usuarios to set
	 */
	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	/**
	 * @return the pessoas
	 */
	public List<Pessoa> getPessoas() {
		return pessoas;
	}

	/**
	 * @param pessoas the pessoas to set
	 */
	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}	
}