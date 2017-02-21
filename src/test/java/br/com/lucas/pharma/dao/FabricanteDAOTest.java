package br.com.lucas.pharma.dao;

import org.junit.Ignore;
import org.junit.Test;

import br.com.lucas.pharma.domain.Fabricante;

public class FabricanteDAOTest {

	@Test	
	@Ignore
	public void merge() {
		Fabricante fabricante = new Fabricante();
		fabricante.setDescricao("Fabricante A");		
		FabricanteDAO fabricanteDAO = new FabricanteDAO();
		fabricanteDAO.merge(fabricante); // merge neste exemplo serve para salvar - sem chave primaria ele irá incluir
		
		/*FabricanteDAO fabricanteDAO = new FabricanteDAO();
		Fabricante fabricante = fabricanteDAO.buscar(1L);
		fabricante.setDescricao("Fabricante B");
		fabricanteDAO.merge(fabricante);*/ // merge neste exemplo serve para editar - com chave primaria ele irá editar
	}
}
