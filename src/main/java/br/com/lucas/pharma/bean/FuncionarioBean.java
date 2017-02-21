package br.com.lucas.pharma.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.lucas.pharma.dao.FuncionarioDAO;
import br.com.lucas.pharma.dao.PessoaDAO;
import br.com.lucas.pharma.domain.Funcionario;
import br.com.lucas.pharma.domain.Pessoa;

/**
 * @author Lucas.
 * 
 *         Classe que representa um Bean para Funcionario.
 * 
 */
@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class FuncionarioBean implements Serializable {

	private Funcionario funcionario;
	private List<Funcionario> funcionarios;
	private List<Pessoa> pessoas;

	/**
	 * Método para listar a entidade Funcionario na view utilizando @PostConstruct para criar a lista ao carregar a página.	 
	 */
	@PostConstruct
	public void listar() {
		try {
			FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
			funcionarios = funcionarioDAO.listarOrdenado();
			
		}catch(RuntimeException ex) {
			ex.printStackTrace();
			Messages.addGlobalError("Ocorreu um erro ao tentar listar os funcionários");
		}
	}

	/**
	 * Método para forçar o recarregamento das entidades Funcionário e Pessoa na tela ao salvar um funcionário.  
	 */
	public void novo() {
		try {
			funcionario = new Funcionario();

			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoas = pessoaDAO.listar("nome");

		}catch(RuntimeException ex) {
			ex.printStackTrace();
			Messages.addGlobalError("Ocorreu um erro ao tentar gerar um novo funcionário");
		}
	}

	/**
	 * Método para salvar um funcionário no banco de dados.  
	 */
	public void salvar() {
		try {
			FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
			funcionarioDAO.merge(funcionario);

			funcionarios = funcionarioDAO.listarOrdenado();

			novo();

			Messages.addGlobalInfo("Funcionário salvo com sucesso");
		}catch(RuntimeException ex) {
			ex.printStackTrace();
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar o funcionário");
		}
	}

	/**
	 * Método para excluir um funcionário no banco de dados.  
	 */
	public void excluir(ActionEvent evento) {
		try {
			funcionario = (Funcionario) evento.getComponent().getAttributes().get("funcionarioSelecionado");

			FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
			funcionarioDAO.excluir(funcionario);

			funcionarios = funcionarioDAO.listarOrdenado();

			Messages.addGlobalInfo("Funcionário removido com sucesso");
		}catch(RuntimeException ex) {
			ex.printStackTrace();
			Messages.addGlobalError("Ocorreu um erro ao tentar excluir o funcionário");
		}
	}

	/**
	 * Método para editar um funcionário no banco de dados.  
	 */
	public void editar(ActionEvent evento) {
		try {
			funcionario = (Funcionario) evento.getComponent().getAttributes().get("funcionarioSelecionado");

			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoas = pessoaDAO.listar("nome");

			Messages.addGlobalInfo("Funcionário editado com sucesso");
		}catch(RuntimeException ex) {
			ex.printStackTrace();
			Messages.addGlobalError("Ocorreu um erro ao tentar editar o funcionário");
		}
	}

	/**
	 * @return the funcionario
	 */
	public Funcionario getFuncionario() {
		return funcionario;
	}

	/**
	 * @param funcionario the funcionario to set
	 */
	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	/**
	 * @return the funcionarios
	 */
	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	/**
	 * @param funcionarios the funcionarios to set
	 */
	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
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