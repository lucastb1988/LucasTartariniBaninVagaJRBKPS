package br.com.lucas.pharma.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import br.com.lucas.pharma.enumeracao.TipoUsuario;

@SuppressWarnings("serial")
@Entity
public class Usuario extends GenericDomain {

	@Column(length = 32, nullable = false)
	private String senha;
	
	@Transient // não precisa declarar no banco... somente temporariamente
	private String senhaSemCriptografia;
	
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private TipoUsuario tipoUsuario;

	@Column(nullable = false)
	private Boolean ativo;

	@JoinColumn(nullable = false)
	@OneToOne
	private Pessoa pessoa;

	/**
	 * @return the senha
	 */
	public String getSenha() {
		return senha;
	}

	/**
	 * @param senha the senha to set
	 */
	public void setSenha(String senha) {
		this.senha = senha;
	}

	/**
	 * @return the ativo
	 */
	public Boolean getAtivo() {
		return ativo;
	}

	@Transient // anotação referência para somente formatação (se não utilizar esta anotação o Hibernate entende como um campo normal)
	public String getAtivoFormatado() { 
		String ativoFormatado = null; // formatar o campo ativo de true, false para a sua respectiva palavra abaixo(metódo realizado para visualização na página do site)
		if(ativo == true) {
			ativoFormatado = "Ativo";
		} else if(ativo == false) {
			ativoFormatado = "Não Ativo";
		}
		return ativoFormatado;
	}

	/**
	 * @param ativo the ativo to set
	 */
	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
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
	 * @return the senhaSemCriptografia
	 */
	public String getSenhaSemCriptografia() {
		return senhaSemCriptografia;
	}

	/**
	 * @param senhaSemCriptografia the senhaSemCriptografia to set
	 */
	public void setSenhaSemCriptografia(String senhaSemCriptografia) {
		this.senhaSemCriptografia = senhaSemCriptografia;
	}

	/**
	 * @return the tipoUsuario
	 */
	public TipoUsuario getTipoUsuario() {
		return tipoUsuario;
	}

	/**
	 * @param tipoUsuario the tipoUsuario to set
	 */
	public void setTipoUsuario(TipoUsuario tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}
}