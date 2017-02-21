package br.com.lucas.pharma.dao;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import br.com.lucas.pharma.domain.Caixa;

public class CaixaDAOTest {

	@Test
	@Ignore
	public void salvar() throws ParseException {
		Caixa caixa = new Caixa();
		caixa.setDataAbertura(new SimpleDateFormat("dd/MM/yyyy").parse("04/07/1988"));
		caixa.setValorAbertura(new BigDecimal("100.00"));

		CaixaDAO caixaDAO = new CaixaDAO();
		caixaDAO.salvar(caixa);
	}

	@Test
	@Ignore
	public void buscar() throws ParseException {
		CaixaDAO caixaDAO = new CaixaDAO();
		Caixa caixa = caixaDAO.buscar(new SimpleDateFormat("dd/MM/yyyy").parse("04/07/1988"));
		Assert.assertNull(caixa);
	}
}