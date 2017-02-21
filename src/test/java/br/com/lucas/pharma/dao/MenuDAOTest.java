package br.com.lucas.pharma.dao;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.lucas.pharma.domain.Menu;

public class MenuDAOTest {

	@Test
	@Ignore
	public void listar() {
		MenuDAO menuDAO = new MenuDAO();
		
		List<Menu> lista = menuDAO.listar();

		for(Menu menu : lista) {
			if(menu.getCaminho() == null) {
				System.out.println(menu.getRotulo() + " - " + menu.getCaminho() + menu.getMenus());
				for(Menu item : menu.getMenus()) {
					System.out.println("\t" + item.getRotulo() + " - " + item.getCaminho());
				}
			}			
		}		
	}
}
