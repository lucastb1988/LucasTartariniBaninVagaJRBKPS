package br.com.lucas.pharma.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.lucas.pharma.dao.ClienteDAO;
import br.com.lucas.pharma.dao.PessoaDAO;
import br.com.lucas.pharma.domain.Cliente;
import br.com.lucas.pharma.domain.Pessoa;

/**
 * @author Lucas.
 * 
 *         Classe que representa um Bean para Cliente.
 * 
 */
@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class ClienteBean implements Serializable{

	private Cliente cliente;
	private List<Cliente> clientes;

	private List<Pessoa> pessoas;

	/**
	 * Método para listar a entidade Cliente na view utilizando @PostConstruct para criar a lista ao carregar a página.	 
	 */
	@PostConstruct
	public void listar() {
		try {
			ClienteDAO clienteDAO = new ClienteDAO();
			clientes = clienteDAO.listarOrdenado();
			
		}	catch(RuntimeException ex) {
			ex.printStackTrace();
			Messages.addGlobalError("Ocorreu um erro ao tentar listar os clientes");
		}
	}
	
	/**
	 * Método para forçar o recarregamento das entidades Cliente e Pessoa na tela ao salvar um cliente.  
	 */
	public void novo() {
		try {
			cliente = new Cliente();

			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoas = pessoaDAO.listar("nome");

		} catch(RuntimeException ex) {
			ex.printStackTrace();
			Messages.addGlobalError("Ocorreu um erro ao tentar gerar um novo cliente");
		}
	}

	/**
	 * Método para salvar um cliente no banco de dados.  
	 */
	public void salvar() {
		try {
			ClienteDAO clienteDAO = new ClienteDAO();
			clienteDAO.merge(cliente);

			clientes = clienteDAO.listarOrdenado();

			novo(); // método acima... limpar o cliente, data de cadastro e status cadastrados na tela de novo... para que após salvar um cliente seja limpado os campos na página do site e criar outro cliente		

			Messages.addGlobalInfo("Cliente salvo com sucesso");
		} catch(RuntimeException ex) {
			ex.printStackTrace();
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar o cliente");
		}
	}

	/**
	 * Método para excluir um cliente no banco de dados.  
	 */
	public void excluir(ActionEvent evento) {
		try {
			cliente = (Cliente) evento.getComponent().getAttributes().get("clienteSelecionado");

			ClienteDAO clienteDAO = new ClienteDAO();
			clienteDAO.excluir(cliente);
			
			clientes = clienteDAO.listarOrdenado();

			Messages.addGlobalInfo("Cliente removido com sucesso");
		} catch(RuntimeException ex) {
			ex.printStackTrace();
			Messages.addGlobalError("Ocorreu um erro ao tentar excluir o cliente");
		}
	}

	/**
	 * Método para editar um cliente no banco de dados.  
	 */
	public void editar(ActionEvent evento) {
		try {
			cliente = (Cliente) evento.getComponent().getAttributes().get("clienteSelecionado");

			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoas = pessoaDAO.listar("nome");

			Messages.addGlobalInfo("Cliente editado com sucesso");
		} catch(RuntimeException ex) {
			ex.printStackTrace();
			Messages.addGlobalError("Ocorreu um erro ao tentar editar o cliente");
		}
	}

	/**
	 * @return the cliente
	 */
	public Cliente getCliente() {
		return cliente;
	}

	/**
	 * @param cliente the cliente to set
	 */
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	/**
	 * @return the clientes
	 */
	public List<Cliente> getClientes() {
		return clientes;
	}

	/**
	 * @param clientes the clientes to set
	 */
	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
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