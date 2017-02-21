package br.com.lucas.pharma.dao;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.lucas.pharma.domain.Cidade;
import br.com.lucas.pharma.domain.Estado;

public class CidadeDAOTest {

	@Test
	@Ignore
	public void salvar() {
		EstadoDAO estadoDAO = new EstadoDAO();
		Estado estado = estadoDAO.buscar(1L);

		Cidade cidade = new Cidade();
		cidade.setNome("Santo André");
		cidade.setEstado(estado);

		CidadeDAO cidadeDAO = new CidadeDAO();
		cidadeDAO.salvar(cidade);
	}

	@Test
	@Ignore
	public void listar() {
		CidadeDAO cidadeDAO = new CidadeDAO();
		List<Cidade> resultado = cidadeDAO.listar();

		for(Cidade cidade : resultado) { // impressão da listagem (opcional)
			System.out.println(cidade.getCodigo());
			System.out.println(cidade.getNome());
			System.out.println(cidade.getEstado().getCodigo());
			System.out.println(cidade.getEstado().getNome());
			System.out.println(cidade.getEstado().getSigla());
			System.out.println();
		}
	}

	@Test
	@Ignore
	public void buscar() {
		Long codigo = 1L;

		CidadeDAO cidadeDAO = new CidadeDAO();
		Cidade cidade = cidadeDAO.buscar(codigo);

		System.out.println(cidade.getCodigo());
		System.out.println(cidade.getNome());
		System.out.println(cidade.getEstado().getCodigo());
		System.out.println(cidade.getEstado().getNome());
		System.out.println(cidade.getEstado().getSigla());		
	}

	@Test	
	@Ignore
	public void excluir() {
		Long codigo = 1L;

		CidadeDAO cidadeDAO = new CidadeDAO();
		Cidade cidade = cidadeDAO.buscar(codigo);
		cidadeDAO.excluir(cidade);
		
		if(cidade == null) {
			System.out.println("Nenhum registro encontrado");
		} else {			
			System.out.println("Registro removido: ");
			System.out.println(cidade.getCodigo());
			System.out.println(cidade.getNome());
			System.out.println(cidade.getEstado().getCodigo());
			System.out.println(cidade.getEstado().getNome());
			System.out.println(cidade.getEstado().getSigla());
		}			
	}	

	@Test
	@Ignore
	public void editar() {
		Long codigoCidade = 1L;
		Long codigoEstado = 1L;
		
		EstadoDAO estadoDAO = new EstadoDAO();
		Estado estado = estadoDAO.buscar(codigoEstado);		

		CidadeDAO cidadeDAO = new CidadeDAO();
		Cidade cidade = cidadeDAO.buscar(codigoCidade);

		if(cidade == null) {
			System.out.println("Nenhum registro encontrado");
		} else {
			cidade.setNome("Santo André");	
			cidade.setEstado(estado);
			
			cidadeDAO.editar(cidade);			
			
			System.out.println("Registro editado: ");
			System.out.println(cidade.getCodigo());
			System.out.println(cidade.getNome());
			System.out.println(cidade.getEstado().getCodigo());
			System.out.println(cidade.getEstado().getNome());
			System.out.println(cidade.getEstado().getSigla());
		}		
	}
	
	//método para testar buscarPorEstado no qual filtrará as cidades de acordo com o estado selecionado
	@Test
	@Ignore
	public void buscarPorEstado() {
		Long estadoCodigo = 1L;		
		
		CidadeDAO cidadeDAO = new CidadeDAO();
		List<Cidade> resultado = cidadeDAO.buscarPorEstado(estadoCodigo);

		for(Cidade cidade : resultado) { // impressão da listagem (opcional)
			System.out.println(cidade.getCodigo());
			System.out.println(cidade.getNome());
			System.out.println(cidade.getEstado().getCodigo());
			System.out.println(cidade.getEstado().getNome());
			System.out.println(cidade.getEstado().getSigla());
			System.out.println();
		}
	}
}