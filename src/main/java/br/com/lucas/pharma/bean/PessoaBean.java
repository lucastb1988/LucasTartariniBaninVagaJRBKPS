package br.com.lucas.pharma.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.lucas.pharma.dao.CidadeDAO;
import br.com.lucas.pharma.dao.EstadoDAO;
import br.com.lucas.pharma.dao.PessoaDAO;
import br.com.lucas.pharma.domain.Cidade;
import br.com.lucas.pharma.domain.Estado;
import br.com.lucas.pharma.domain.Pessoa;

/**
 * @author Lucas.
 * 
 *         Classe que representa um Bean para Pessoa.
 * 
 */
@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class PessoaBean implements Serializable {
	private Pessoa pessoa; // classe objeto pessoa para capturar os campos
	private List<Pessoa> pessoas; // listar todas as pessoas no xhtml após salvar um novo

	private Estado estado; // campo estado não está dentro do dominio pessoa - é necessário variavel estado para filtrar as cidades no SelectOneMenu/ComboBox no xhtml
	private List<Estado> estados; // listar todos as estados no xhtml após salvar um novo

	private List<Cidade> cidades; // listar todas as cidades no xhtml após salvar um novo

	/**
	 * Método para listar a entidade Pessoa na view utilizando @PostConstruct para criar a lista ao carregar a página.	 
	 */
	@PostConstruct
	public void listar() {
		try {
			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoas = pessoaDAO.listar("nome");

		} catch(RuntimeException ex) {
			ex.printStackTrace();
			Messages.addGlobalError("Ocorreu um erro ao tentar listar as pessoas");
		}
	}

	/**
	 * Método para forçar o recarregamento das entidades Pessoa e Estado na tela ao salvar uma pessoa.  
	 */
	public void novo() {
		try {
			pessoa = new Pessoa(); //após a criação de uma nova pessoa na pagina do site é limpado para criar outra pessoa
			estado = new Estado(); //após a criação de uma nova cidade na pagina do site é limpado para criar outra pessoa

			EstadoDAO estadoDAO = new EstadoDAO();
			estados = estadoDAO.listar("nome"); // passando a variável "nome" para ordenação dos campos no SelectOneMenu/ComboBox nos xhtmls

			//
			//						Client cliente = ClientBuilder.newClient(); // criando um novo cliente para fazer a integração jsf(front-end) para servidor(back-end)
			//						WebTarget caminho = cliente.target("http://127.0.0.1:8080/Pharma/rest/cidade"); // definir o caminho do seu rest no qual deseja integrar
			//						String json = caminho.request().get(String.class); // request dispara uma requisição (neste caso GET) (GET, POST, PUT, DELETE)
			//						// define o tipo de retorno String dentro do get (retornará um tipo json)
			//			
			//						Gson gson = new Gson();
			//						Estado[] vetor = gson.fromJson(json, Estado[].class); //converterá o resultado do gson em um vetor de fabricantes /
			//						// necessita converter primeiramente em vetor pois a sua lista na página se encontra como List
			//			
			//						estados = Arrays.asList(vetor); // converterá o vetor para ArrayList que seria o tipo de Lista que está utilizando na página 

			// neste exemplo será primeiramente gerado a lista dos estados e a lista de cidades vazia. Após a inclusao do nome do estado na lista as cidades irão aparecer conforme estado escolhido
			cidades = new ArrayList<Cidade>(); // instanciando uma lista de cidades vazia para que fique vazio o campo antes de selecionar o estado

		} catch (RuntimeException ex) {
			ex.printStackTrace();
			Messages.addGlobalError("Ocorreu um erro ao tentar gerar uma nova pessoa");
		}
	}

	/**
	 * Método para salvar uma pessoa no banco de dados.  
	 */
	public void salvar() {
		try { 
			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoaDAO.merge(pessoa);

			pessoas = pessoaDAO.listar("nome");

			novo();	// método acima... limpar a pessoa, estado e cidade cadastrados na tela de novo... para que após salvar uma pessoa seja limpado os campos na página do site

			Messages.addGlobalInfo("Pessoa salva com sucesso");
		} catch (RuntimeException ex) {
			ex.printStackTrace();
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar a pessoa");
		}
	}

	/**
	 * Método para excluir uma pessoa no banco de dados.  
	 */
	public void excluir(ActionEvent evento) {
		try {
			pessoa = (Pessoa) evento.getComponent().getAttributes().get("pessoaSelecionada"); // Evento para recuperar o objeto cidadeSelecionada no xhtml 

			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoaDAO.excluir(pessoa);

			pessoas = pessoaDAO.listar("nome");
			
			Messages.addGlobalInfo("Pessoa removido com sucesso");
		} catch(RuntimeException ex) {
			ex.printStackTrace();
			Messages.addGlobalError("Ocorreu um erro ao excluir a pessoa");
		}
	}

	/**
	 * Método para editar uma pessoa no banco de dados.  
	 */
	public void editar(ActionEvent evento) {
		try {
			pessoa = (Pessoa) evento.getComponent().getAttributes().get("pessoaSelecionada"); // Evento para recuperar o objeto cidadeSelecionada no xhtml 

			estado = pessoa.getCidade().getEstado(); // deixar selecionado o estado corrente ao entrar no botão de edição			
			EstadoDAO estadoDAO = new EstadoDAO();
			estados = estadoDAO.listar("nome");

			CidadeDAO cidadeDAO = new CidadeDAO();
			cidades = cidadeDAO.buscarPorEstado(estado.getCodigo()); // carregar a cidade com base no estado / a cidade necessita buscar o estado primeiro / após isso selecionar o estado será mostrado o filtro das cidades de acordo com o estado selecionado

			Messages.addGlobalInfo("Pessoa editada com sucesso");
		} catch(RuntimeException ex) {
			ex.printStackTrace();
			Messages.addGlobalError("Ocorreu um erro ao excluir a pessoa");
		}
	}	

	/**
	 * Método para popular as cidades de acordo com o estado selecionado no SelectOneMenu/ComboBox no xhtml.
	 */
	public void popular() {
		try {
			if(estado != null) {
				CidadeDAO cidadeDAO = new CidadeDAO();
				cidades = cidadeDAO.buscarPorEstado(estado.getCodigo()); // carregar a cidade com base no estado / a cidade necessita buscar o estado primeiro / após isso selecionar o estado será mostrado o filtro das cidades de acordo com o estado selecionado

				//				Client cliente = ClientBuilder.newClient(); // criando um novo cliente para fazer a integração jsf(front-end) para servidor(back-end)
				//				WebTarget caminho = cliente.target("http://127.0.0.1:8080/Pharma/rest/cidade/{estadoCodigo}").resolveTemplate("estadoCodigo", estado.getCodigo()); // definir o caminho do seu rest no qual deseja integrar
				//				String json = caminho.request().get(String.class); // request dispara uma requisição (neste caso GET) (GET, POST, PUT, DELETE)
				//				// define o tipo de retorno String dentro do get (retornará um tipo json)
				//
				//				Gson gson = new Gson();
				//				Cidade[] vetor = gson.fromJson(json, Cidade[].class); //converterá o resultado do gson em um vetor de fabricantes /
				//				// necessita converter primeiramente em vetor pois a sua lista na página se encontra como List
				//							
				//				cidades = Arrays.asList(vetor); // converterá o vetor para ArrayList que seria o tipo de Lista que está utilizando na página 


			} else {
				cidades = new ArrayList<Cidade>(); // instanciando uma lista de cidades vazia para que fique vazio o campo antes de selecionar o estado
			}
		} catch (RuntimeException ex) {
			ex.printStackTrace();
			Messages.addGlobalError("Ocorreu um erro ao tentar filtrar as cidades");
		}
	}

	/**
	 * @return the pessoa
	 */
	public Pessoa getPessoa() {
		return pessoa;
	}

	/**
	 * @param pessoa the pessoa to set
	 */
	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
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

	/**
	 * @return the estado
	 */
	public Estado getEstado() {
		return estado;
	}

	/**
	 * @param estado the estado to set
	 */
	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	/**
	 * @return the estados
	 */
	public List<Estado> getEstados() {
		return estados;
	}

	/**
	 * @param estados the estados to set
	 */
	public void setEstados(List<Estado> estados) {
		this.estados = estados;
	}

	/**
	 * @return the cidades
	 */
	public List<Cidade> getCidades() {
		return cidades;
	}

	/**
	 * @param cidades the cidades to set
	 */
	public void setCidades(List<Cidade> cidades) {
		this.cidades = cidades;
	}		
}