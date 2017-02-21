package br.com.lucas.pharma.dao;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.lucas.pharma.domain.Fabricante;
import br.com.lucas.pharma.domain.Produto;

public class ProdutoDAOTest {

	@Test	
	@Ignore
	public void salvar() {
		FabricanteDAO fabricanteDAO = new FabricanteDAO();
		Fabricante fabricante = fabricanteDAO.buscar(7L);

		Produto produto = new Produto();
		produto.setDescricao("Novalgina com 20 comprimidos");
		produto.setPreco(new BigDecimal("9.99"));
		produto.setQuantidade(new Short("55"));			
		produto.setFabricante(fabricante);

		ProdutoDAO produtoDAO = new ProdutoDAO();
		produtoDAO.salvar(produto);

	}

	@Test
	@Ignore
	public void listar() {
		ProdutoDAO produtoDAO = new ProdutoDAO();
		List<Produto> resultado = produtoDAO.listar();

		for(Produto produto : resultado) { // impressão da listagem (opcional)
			System.out.println(produto.getCodigo());
			System.out.println(produto.getDescricao());	
			System.out.println(produto.getPreco());
			System.out.println(produto.getQuantidade());				
			System.out.println(produto.getFabricante().getCodigo());
			System.out.println();
		}
	}

	@Test
	@Ignore
	public void buscar() {
		Long codigo = 1L;

		ProdutoDAO produtoDAO = new ProdutoDAO();
		Produto produto = produtoDAO.buscar(codigo);

		System.out.println(produto.getCodigo());
		System.out.println(produto.getDescricao());	
		System.out.println(produto.getPreco());
		System.out.println(produto.getQuantidade());				
		System.out.println(produto.getFabricante().getCodigo());	
	}

	@Test	
	@Ignore
	public void excluir() {
		Long codigo = 1L;

		ProdutoDAO produtoDAO = new ProdutoDAO();
		Produto produto = produtoDAO.buscar(codigo);
		produtoDAO.excluir(produto);

		if(produto == null) {
			System.out.println("Nenhum registro encontrado");
		} else {			
			System.out.println("Registro removido: ");
			System.out.println(produto.getCodigo());
			System.out.println(produto.getDescricao());	
			System.out.println(produto.getPreco());
			System.out.println(produto.getQuantidade());				
			System.out.println(produto.getFabricante().getCodigo());
		}			
	}	

	@Test
	@Ignore
	public void editar() {
		Long codigoProduto = 1L;

		ProdutoDAO produtoDAO = new ProdutoDAO();
		Produto produto = produtoDAO.buscar(codigoProduto);

		FabricanteDAO fabricanteDAO = new FabricanteDAO();
		Fabricante fabricante = fabricanteDAO.buscar(1L);

		if(produto == null) {
			System.out.println("Nenhum registro encontrado");
		} else {
			produto.setDescricao("Cataflan em gotas");
			produto.setPreco(new BigDecimal("5.95"));
			produto.setQuantidade(new Short("18"));
			produto.setFabricante(fabricante);

			produtoDAO.editar(produto);			

			System.out.println("Registro editado: ");
			System.out.println(produto.getCodigo());
			System.out.println(produto.getDescricao());	
			System.out.println(produto.getPreco());
			System.out.println(produto.getQuantidade());				
			System.out.println(produto.getFabricante().getCodigo());
		}		
	}
}