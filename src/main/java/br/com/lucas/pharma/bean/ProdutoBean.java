package br.com.lucas.pharma.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.lucas.pharma.dao.FabricanteDAO;
import br.com.lucas.pharma.dao.ProdutoDAO;
import br.com.lucas.pharma.domain.Fabricante;
import br.com.lucas.pharma.domain.Produto;

/**
 * @author Lucas.
 * 
 *         Classe que representa um Bean para Produto.
 * 
 */
@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class ProdutoBean implements Serializable {

	private Produto produto;
	private List<Produto> produtos;
	private List<Fabricante> fabricantes;

	/**
	 * Método para listar a entidade Produto na view utilizando @PostConstruct para criar a lista ao carregar a página.	 
	 */
	@PostConstruct
	public void listar() {
		try {
			ProdutoDAO produtoDAO = new ProdutoDAO();
			produtos = produtoDAO.listar("descricao");
			
		} catch (RuntimeException ex) {
			ex.printStackTrace();
			Messages.addGlobalError("Ocorreu um erro ao listar os produtos");
		}
	}

	/**
	 * Método para forçar o recarregamento das entidades Produto e Fabricante na tela ao salvar um produto.  
	 */
	public void novo() {
		try {
			produto = new Produto();

			FabricanteDAO fabricanteDAO = new FabricanteDAO();
			fabricantes = fabricanteDAO.listar("descricao"); // força o recarregamento dos ex.produtos na página / passando a variável "nome" para ordenação dos campos no SelectOneMenu/ComboBox nos xhtmls

		} catch(RuntimeException ex) {
			ex.printStackTrace();
			Messages.addGlobalError("Ocorreu um erro ao tentar gerar um novo produto");
		}
	}

	/**
	 * Método para salvar um produto no banco de dados.  
	 */
	public void salvar() {
		try{
			ProdutoDAO produtoDAO = new ProdutoDAO();
			produtoDAO.merge(produto);		

			novo(); // limpa a tela ao salvar um produto e força o recarregamento dos ex.produtos na página

			produtos = produtoDAO.listar("descricao");

			Messages.addGlobalInfo("Produto salvo com sucesso");
		} catch(RuntimeException ex) {
			ex.printStackTrace();
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar o produto");
		}
	}

	/**
	 * Método para excluir um produto no banco de dados.  
	 */
	public void excluir(ActionEvent evento) {
		try {
			produto = (Produto) evento.getComponent().getAttributes().get("produtoSelecionado"); // Evento para recuperar o objeto produtoSelecionado no xhtml 

			ProdutoDAO produtoDAO = new ProdutoDAO();
			produtoDAO.excluir(produto);

			produtos = produtoDAO.listar("descricao"); // força o recarregamento dos ex.produtos na página / passando a variável "nome" para ordenação dos campos no SelectOneMenu/ComboBox nos xhtmls

			Messages.addGlobalInfo("Produto removido com sucesso");
		} catch(RuntimeException ex) {
			ex.printStackTrace();
			Messages.addGlobalError("Ocorreu um erro ao excluir o produto");
		}
	}

	/**
	 * Método para editar um produto no banco de dados.  
	 */
	public void editar(ActionEvent evento) {
		try {
			produto = (Produto) evento.getComponent().getAttributes().get("produtoSelecionado"); // Evento para recuperar o objeto produtoSelecionado no xhtml 

			FabricanteDAO fabricanteDAO = new FabricanteDAO();
			fabricantes = fabricanteDAO.listar("descricao"); // força o recarregamento dos ex.produtos na página / passando a variável "nome" para ordenação dos campos no SelectOneMenu/ComboBox nos xhtmls

			Messages.addGlobalInfo("Produto editado com sucesso");
		} catch (RuntimeException ex) {
			ex.printStackTrace();
			Messages.addGlobalError("Ocorreu um erro ao tentar editar o produto");
		}
	}

	/**
	 * @return the produto
	 */
	public Produto getProduto() {
		return produto;
	}

	/**
	 * @param produto the produto to set
	 */
	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	/**
	 * @return the produtos
	 */
	public List<Produto> getProdutos() {
		return produtos;
	}

	/**
	 * @param produtos the produtos to set
	 */
	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	/**
	 * @return the fabricantes
	 */
	public List<Fabricante> getFabricantes() {
		return fabricantes;
	}

	/**
	 * @param fabricantes the fabricantes to set
	 */
	public void setFabricantes(List<Fabricante> fabricantes) {
		this.fabricantes = fabricantes;
	}	
}