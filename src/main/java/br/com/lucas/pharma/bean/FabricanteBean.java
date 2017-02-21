package br.com.lucas.pharma.bean;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.omnifaces.util.Messages;

import com.google.gson.Gson;

import br.com.lucas.pharma.dao.FabricanteDAO;
import br.com.lucas.pharma.domain.Fabricante;

/**
 * @author Lucas.
 * 
 *         Classe que representa um Bean para Fabricante.
 * 
 */
@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class FabricanteBean implements Serializable {

	//Variaveis que se fazem de modelo para o xhtml
	private Fabricante fabricante;
	private List<Fabricante> fabricantes;
	
	/**
	 * Método para listar a entidade Fabricante na view utilizando @PostConstruct para criar a lista ao carregar a página.	 
	 */
	@PostConstruct
	public void listar() {
		try {
			FabricanteDAO fabricantedao = new FabricanteDAO();
			fabricantes = fabricantedao.listar("descricao");

			// Não estará utilizando a camada DAO e sim utilizando o Service para chamar a camada DAO nesta integração

			// A vantagem em realizar esta integração abaixo (front-end / back-end) no futuro poderá ser utilizado outra linguagem de programação para rodar o mesmo projeto (desacopla do projeto)

			//			Client cliente = ClientBuilder.newClient(); // criando um novo cliente para fazer a integração jsf(front-end) para servidor(back-end)
			//			WebTarget caminho = cliente.target("http://127.0.0.1:8080/Pharma/rest/fabricante"); // definir o caminho do seu rest no qual deseja integrar
			//			String json = caminho.request().get(String.class); // request dispara uma requisição (neste caso GET) (GET, POST, PUT, DELETE)
			//			// define o tipo de retorno String dentro do get (retornará um tipo json)
			//
			//			Gson gson = new Gson();
			//			Fabricante[] vetor = gson.fromJson(json, Fabricante[].class); //converterá o resultado do gson em um vetor de fabricantes /
			//			// necessita converter primeiramente em vetor pois a sua lista na página se encontra como List
			//
			//			fabricantes = Arrays.asList(vetor); // converterá o vetor para ArrayList que seria o tipo de Lista que está utilizando na página 

		} catch (RuntimeException ex) {
			ex.printStackTrace();
			Messages.addGlobalError("Ocorreu um erro ao tentar listar os fabricantes");
		}
	}

	/**
	 * Método para forçar o recarregamento da entidade Fabricante na tela ao salvar uma estado.  
	 */
	public void novo() {
		fabricante = new Fabricante();		
	}

	/**
	 * Método para salvar um fabricante no banco de dados.  
	 */
	public void salvar() {
		try {
			FabricanteDAO fabricantedao = new FabricanteDAO(); // chama o DAO do fabricante
			fabricantedao.merge(fabricante); // salvar o fabricante utilizando DAO  		
			
			novo();	// instancia novo Fabricante após salvar		
			fabricantes = fabricantedao.listar("descricao"); // força o recarregamento da lista dos ex.fabricantes na página 	

			// Não estará utilizando a camada DAO e sim utilizando o Service para chamar a camada DAO nesta integração

			// A vantagem em realizar esta integração abaixo (front-end / back-end) no futuro poderá ser utilizado outra linguagem de programação para rodar o mesmo projeto (desacopla do projeto)

			//			Client cliente = ClientBuilder.newClient(); // criando um novo cliente para fazer a integração jsf(front-end) para servidor(back-end)
			//			WebTarget caminho = cliente.target("http://127.0.0.1:8080/Pharma/rest/fabricante"); // definir o caminho do seu rest no qual deseja integrar
			//			
			//			Gson gson = new Gson();
			//			
			//			String json = gson.toJson(fabricante);			
			//			caminho.request().post(Entity.json(json)); // request dispara uma requisição (neste caso POST) (GET, POST, PUT, DELETE)
			//			// define o protocolo junto a entidade Fabricante convertida em json para que possa realizar o salvar/editar
			//			
			//			// limpar o fabricante
			//			fabricante = new Fabricante(); // instancia novo Fabricante após salvar	
			//			
			//			// forçar o recarregamento da lista dos ex.fabricantes na página 
			//			json = caminho.request().get(String.class); // request dispara uma requisição (neste caso GET) (GET, POST, PUT, DELETE) / // define o tipo de retorno String dentro do get (retornará um tipo json)
			//			Fabricante[] vetor = gson.fromJson(json, Fabricante[].class); //converterá o resultado do gson em um vetor de fabricantes / // necessita converter primeiramente em vetor pois a sua lista na página se encontra como List
			//			fabricantes = Arrays.asList(vetor); // converterá o vetor para ArrayList que seria o tipo de Lista que está utilizando na página

			Messages.addGlobalInfo("Fabricante salvo com sucesso");
		} catch (RuntimeException ex) {
			ex.printStackTrace();
			Messages.addGlobalError("Ocorreu um erro ao salvar um fabricante");
		}
	}

	/**
	 * Método para excluir um fabricante no banco de dados.  
	 */
	public void excluir(ActionEvent evento) { // Parâmetro para capturar o que foi enviado através do fabricanteSelecionado no xhtml 
		try {
			fabricante = (Fabricante) evento.getComponent().getAttributes().get("fabricanteSelecionado"); // Evento para recuperar o objeto estadoSelecionado no xhtml 
			
			FabricanteDAO fabricantedao = new FabricanteDAO();
			fabricantedao.excluir(fabricante); // excluir utilizando DAO    
			
			fabricantes = fabricantedao.listar("descricao"); // força o recarregamento dos ex.fabricantes na página

			// Não estará utilizando a camada DAO e sim utilizando o Service para chamar a camada DAO nesta integração

			// A vantagem em realizar esta integração abaixo (front-end / back-end) no futuro poderá ser utilizado outra linguagem de programação para rodar o mesmo projeto (desacopla do projeto)

			//			Client cliente = ClientBuilder.newClient(); // criando um novo cliente para fazer a integração jsf(front-end) para servidor(back-end)
			//			WebTarget caminho = cliente.target("http://127.0.0.1:8080/Pharma/rest/fabricante"); // definir o caminho do seu rest no qual deseja integrar 

			//			WebTarget caminhoExcluir = caminho.path("{codigo}").resolveTemplate("codigo", fabricante.getCodigo()); 
			//			// inserir o path relacionado no método excluir do FabricanteService, 
			//			// após inserir path, inserir resolveTemplate para converter {codigo} no código em si do fabricante (ex. 10)
			//			caminhoExcluir.request().delete(); // dispara requisição para realizar a exclusão

			//			// atualizar a página			
			//			String json = caminho.request().get(String.class); // request dispara uma requisição (neste caso GET) (GET, POST, PUT, DELETE) / // define o tipo de retorno String dentro do get (retornará um tipo json)
			//			Gson gson = new Gson();
			//			Fabricante[] vetor = gson.fromJson(json, Fabricante[].class); //converterá o resultado do gson em um vetor de fabricantes / // necessita converter primeiramente em vetor pois a sua lista na página se encontra como List
			//			fabricantes = Arrays.asList(vetor); // converterá o vetor para ArrayList que seria o tipo de Lista que está utilizando na página

			Messages.addGlobalInfo("Fabricante removido com sucesso.");
		} catch (RuntimeException ex) {
			ex.printStackTrace();
			Messages.addGlobalError("Ocorreu um erro ao excluir um fabricante");
		}
	}

	/**
	 * Método para editar um fabricante no banco de dados.  
	 */
	public void editar(ActionEvent evento) {	
		fabricante = (Fabricante) evento.getComponent().getAttributes().get("fabricanteSelecionado"); // Evento para recuperar o objeto estadoSelecionado no xhtml 
	}

	/**
	 * @return the fabricante
	 */
	public Fabricante getFabricante() {
		return fabricante;
	}

	/**
	 * @param fabricante the fabricante to set
	 */
	public void setFabricante(Fabricante fabricante) {
		this.fabricante = fabricante;
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