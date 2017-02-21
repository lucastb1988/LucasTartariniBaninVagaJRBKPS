package br.com.lucas.pharma.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.lucas.pharma.dao.ClienteDAO;
import br.com.lucas.pharma.dao.FuncionarioDAO;
import br.com.lucas.pharma.dao.ProdutoDAO;
import br.com.lucas.pharma.dao.VendaDAO;
import br.com.lucas.pharma.domain.Cliente;
import br.com.lucas.pharma.domain.Funcionario;
import br.com.lucas.pharma.domain.ItemVenda;
import br.com.lucas.pharma.domain.Produto;
import br.com.lucas.pharma.domain.Venda;

/**
 * @author Lucas.
 * 
 *         Classe que representa um Bean para Venda.
 * 
 */
@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class VendaBean implements Serializable{

	private Venda venda;

	private List<Produto> produtos;
	private List<ItemVenda> itensVenda;
	private List<Cliente> clientes;
	private List<Funcionario> funcionarios;
	private List<Venda> vendas;

	/**
	 * Método para forçar o recarregamento das entidades Venda e Produto na tela ao salvar uma cidade (@PostConstruct para carregar a página com o método novo limpo).  
	 */
	@PostConstruct
	public void novo() {
		try {
			venda = new Venda();
			venda.setValorTotal(new BigDecimal("0.00"));

			ProdutoDAO produtoDAO = new ProdutoDAO();
			produtos = produtoDAO.listar("descricao");

			itensVenda = new ArrayList<>(); // criar um carrinho de Compra/itensVenda vazio

		} catch (RuntimeException ex) {
			ex.printStackTrace();
			Messages.addGlobalError("Ocorreu um erro ao tentar carregar a tela de vendas");
		}
	}

	/**
	 * Método para listar a entidade Venda na view.	 
	 */
	public void listar() {
		try {
			ProdutoDAO produtoDAO = new ProdutoDAO();
			produtos = produtoDAO.listar("descricao");
			
			VendaDAO vendaDAO = new VendaDAO();
			vendas = vendaDAO.listar();
			
			itensVenda = new ArrayList<>(); // criar um carrinho de Compra/itensVenda vazio
			
		} catch (RuntimeException ex) {
			ex.printStackTrace();
			Messages.addGlobalError("Ocorreu um erro ao tentar listar as vendas");
		}
	}
	
	/**
	 * Método para adicionar 1 ou mais produtos na view.	 
	 */
	public void adicionar(ActionEvent evento) {
		Produto produto = (Produto) evento.getComponent().getAttributes().get("produtoSelecionado"); // Evento para recuperar o objeto produtoSelecionado no xhtml 

		int encontrou = -1; // não encontrou o produto(-1)
		// necessita encontrar a posição do produto primeiramente para conseguir adicioná-lo (corpo abaixo)
		for(int posicao = 0; posicao < itensVenda.size(); posicao++) {
			if(itensVenda.get(posicao).getProduto().equals(produto)) { //capturou o produto da linha corrente e se for igual(equals) ao mesmo produto
				encontrou = posicao; // variavel "encontrou" irá deixar de ser -1 e passará a ser a posição de onde a igualdade foi true
			}
		}
		// Se encontrar um produto que ainda não possui qtde (ainda não foi adicionado nada no carrinho de compras referente a esse produto) realiza o if
		if(encontrou < 0) {
			ItemVenda itemVenda = new ItemVenda(); // Converter o seu produto em um itemVenda
			itemVenda.setPrecoParcial(produto.getPreco());
			itemVenda.setProduto(produto);
			itemVenda.setQuantidade(new Short("1"));				
			itensVenda.add(itemVenda);
			
			// Se encontrar um produto que já possui uma qtde a ser comprada (mesmo produto adicionando quantidade) (já foi adicionado qtde no carrinho de compras referente a este produto) realiza o else
		} else {
			ItemVenda itemVenda = itensVenda.get(encontrou);
			itemVenda.setQuantidade(new Short(itemVenda.getQuantidade() + 1 + "")); 
			//captura a quantidade antiga e soma + 1 (tipo Short precisa capturar o short + short e converte para String)
			itemVenda.setPrecoParcial(produto.getPreco().multiply(new BigDecimal(itemVenda.getQuantidade()))); 
			// captura o preço * a quantidade solicitada (para multiplicação de Big Decimal é necessário converter a qtde Short em Big Decimal)
		}

		calcular();

	}

	/**
	 * Método para remover os produtos adicionados anteriormente na view.	 
	 */
	public void remover(ActionEvent evento) {
		ItemVenda itemVenda = (ItemVenda) evento.getComponent().getAttributes().get("itemSelecionado"); // Evento para recuperar o objeto produtoSelecionado no xhtml 

		int encontrou = -1; // não encontrou o produto
		// necessita encontrar a posição do produto primeiramente para conseguir removê-lo (corpo abaixo)
		for(int posicao = 0; posicao < itensVenda.size(); posicao++) {
			if(itensVenda.get(posicao).getProduto().equals(itemVenda.getProduto())); // comparação entre produtos do botão clicado com os produtos da tabela para encontrar a posição
			encontrou = posicao; // variavel "encontrou" irá deixar de ser -1 e passará a ser a posição de onde a igualdade foi true
		}
		
		if(encontrou > -1) {
			itensVenda.remove(encontrou); // captura a posição encontrada e remove
		} 

		calcular();
	}	

	/**
	 * Método para calcular o valor total após a adição ou remoção do(s) produto(s) na view.	 
	 */
	public void calcular() {
		venda.setValorTotal(new BigDecimal("0.00"));

		for(int posicao = 0; posicao < itensVenda.size(); posicao++) {
			ItemVenda itemVenda = itensVenda.get(posicao); // a cada repetição está capturando um itemVenda do carrinho de compras
			venda.setValorTotal(venda.getValorTotal().add(itemVenda.getPrecoParcial())); // setar o valor total com a soma do valor total + valor parcial de cada itemVenda (fazer a conversão de BigDecimal adicionando BigDecimal)
		}		
	}

	/**
	 * Método para finalizar apresentando a data/horario atual, lista de funcionários e clientes ao finalizar a compra.
	 */
	public void finalizar() {
		try {
			venda.setHorario(new Date()); // imprimir o horário no momento em que clicar em finalizar a compra
			venda.setCliente(null); // após salvar uma venda ao clicar em finalizar novamente o campo é limpado
			venda.setFuncionario(null); // após salvar uma venda ao clicar em finalizar novamente o campo é limpado

			FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
			funcionarios = funcionarioDAO.listarOrdenado();

			ClienteDAO clienteDAO = new ClienteDAO();
			clientes = clienteDAO.listarOrdenado();
			
		} catch(RuntimeException ex) {
			ex.printStackTrace();
			Messages.addGlobalError("Ocorreu um erro ao finalizar a venda");
		}
	}
	
	/**
	 * Método para salvar a compra na view.
	 */
	public void salvar() {
		try {
			if(venda.getValorTotal().signum() == 0) { // BigDecimal converte o numero sem sinal(signum) e realiza a comparação	
				Messages.addGlobalError("Informe no minimo um produto para venda");
				return;
			}

			VendaDAO vendaDAO = new VendaDAO();
			vendaDAO.salvar(venda, itensVenda);

			novo(); //zera a venda para 0.00 / recarrega a listagem de produtos / recarrega itensVenda(data table)

			Messages.addGlobalInfo("Venda realizada com sucesso");
		} catch(RuntimeException ex) {
			ex.printStackTrace();
			Messages.addGlobalError("Ocorreu um erro ao salvar a venda");
		}
	}

	/**
	 * @return the venda
	 */
	public Venda getVenda() {
		return venda;
	}

	/**
	 * @param venda the venda to set
	 */
	public void setVenda(Venda venda) {
		this.venda = venda;
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
	 * @return the itensVenda
	 */
	public List<ItemVenda> getItensVenda() {
		return itensVenda;
	}

	/**
	 * @param itensVenda the itensVenda to set
	 */
	public void setItensVenda(List<ItemVenda> itensVenda) {
		this.itensVenda = itensVenda;
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
	 * @return the vendas
	 */
	public List<Venda> getVendas() {
		return vendas;
	}

	/**
	 * @param vendas the vendas to set
	 */
	public void setVendas(List<Venda> vendas) {
		this.vendas = vendas;
	}	
}