package br.com.lucas.pharma.dao;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.lucas.pharma.domain.Estado;

public class EstadoDAOTest {

	//Teste de Integração
	@Test	
	public void salvar() {
		Estado estado = new Estado();
		estado.setNome("aaawa");
		estado.setSigla("ad");

		EstadoDAO dao = new EstadoDAO();
		dao.salvar(estado);
	}

	@Test
	@Ignore
	public void listar() {
		EstadoDAO dao = new EstadoDAO();
		List<Estado> resultado = dao.listar();

		System.out.println("Total de registros encontrados: " + resultado.size()); // impressão da listagem (opcional)

		for(Estado estado : resultado) { // impressão da listagem (opcional)
			System.out.println(estado.getNome() + " - " + estado.getSigla());
		}
	}

	@Test
	@Ignore
	public void buscar() {
		Long codigo = 1L;

		EstadoDAO dao = new EstadoDAO();
		Estado estado = dao.buscar(codigo);

		if(estado == null) {
			System.out.println("Nenhum registro encontrado");
		} else {
			System.out.println(estado.getCodigo() + " - " + estado.getNome() + " - " + estado.getSigla());
		}
	}

	@Test	
	@Ignore
	public void excluir() {
		Long codigo = 5L;

		EstadoDAO dao = new EstadoDAO();		
		Estado estado = dao.buscar(codigo);
		if(estado == null) {
			System.out.println("Nenhum registro encontrado");
		} else {
			dao.excluir(estado);
			System.out.println("Registro removido: ");
			System.out.println(estado.getCodigo() + " - " + estado.getNome() + " - " + estado.getSigla());
		}			
	}	

	@Test
	@Ignore
	public void editar() {
		Long codigo = 1L;
		EstadoDAO dao = new EstadoDAO();		
		Estado estado = dao.buscar(codigo);
	
		if(estado == null) {
			System.out.println("Nenhum registro encontrado");
		} else {
			estado.setNome("Rio de Janeiro");
			estado.setSigla("SS");	
			dao.editar(estado);
			System.out.println("Registro editado: ");
			System.out.println(estado.getCodigo() + " - " + estado.getNome() + " - " + estado.getSigla());
		}		
	}
}
