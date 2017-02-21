package br.com.lucas.pharma.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import br.com.lucas.pharma.dao.EstadoDAO;
import br.com.lucas.pharma.domain.Estado;

/**
 * @author Lucas.
 * 
 *         Classe que representa um Bean para Estado.
 * 
 */
@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class EstadoBean implements Serializable {

	//Variaveis que se fazem de modelo para o xhtml
	private Estado estado;
	private List<Estado> estados;

	/**
	 * Método para listar a entidade Estado na view utilizando @PostConstruct para criar a lista ao carregar a página.	 
	 */
	@PostConstruct
	public void listar() {
		try {
			EstadoDAO estadodao = new EstadoDAO();
			estados = estadodao.listar("nome");
		} catch (RuntimeException ex) {
			ex.printStackTrace();
			Messages.addGlobalError("Ocorreu um erro ao tentar listar os estados");
		}
	}	

	/**
	 * Método para forçar o recarregamento da entidade Estado na tela ao salvar uma estado.  
	 */
	public void novo() {
		estado = new Estado(); // instancia um novo estado
	}

	/**
	 * Método para salvar um estado no banco de dados.  
	 */
	public void salvar() {
		try {
			EstadoDAO estadodao = new EstadoDAO();
			estadodao.merge(estado); // salvar um estado utilizando DAO  	

			novo(); // limpa a tela de estado ao salvar um estado	

			estados = estadodao.listar("nome"); // força o recarregamento dos ex.estados na página 	

			Messages.addGlobalInfo(Faces.getResourceBundle("msg").getString("mensagem.estado.salvar.sucesso")); 
			// utilizando internacionalização através do Omnifaces inserindo o var(definido no facesConfig) no ResourceBundle e a mensagem gravada no properties em getString
		} catch (RuntimeException ex) {
			ex.printStackTrace();
			Messages.addGlobalError("Ocorreu um erro ao salvar um estado");
		}
	}

	/**
	 * Método para excluir um estado no banco de dados.  
	 */
	public void excluir(ActionEvent evento) { // Parâmetro para capturar o que foi enviado através do estadoSelecionado no xhtml 
		try {
			estado = (Estado) evento.getComponent().getAttributes().get("estadoSelecionado"); // Evento para recuperar o objeto estadoSelecionado informado na view 
			
			EstadoDAO estadodao = new EstadoDAO();
			estadodao.excluir(estado); // excluir um estado utilizando DAO    
			
			estados = estadodao.listar("nome"); // força o recarregamento dos ex.estados na página

			Messages.addGlobalInfo("Estado removido com sucesso.");
		} catch (RuntimeException ex) {
			ex.printStackTrace();
			Messages.addGlobalError("Ocorreu um erro ao excluir um estado");
		}
	}

	/**
	 * Método para editar um estado no banco de dados.  
	 */
	public void editar(ActionEvent evento) {	
		estado = (Estado) evento.getComponent().getAttributes().get("estadoSelecionado"); // Evento para recuperar o objeto estadoSelecionado informado na view
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
}