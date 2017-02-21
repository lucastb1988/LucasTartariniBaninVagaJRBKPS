package br.com.lucas.pharma.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

@SuppressWarnings("serial")
@Entity //anotação que informa que é uma entidade do Hibernate
public class Estado extends GenericDomain {
	
	@Column(length = 2, nullable = false) //informa o tamanho da variavel no banco de dados, informa que a variável não pode ser nula
	private String sigla;
	
	@Column(length = 50, nullable = false) //informa o tamanho da variavel no banco de dados, informa que a variável não pode ser nula
	private String nome;
	
	/**
	 * @return the sigla
	 */
	public String getSigla() {
		return sigla;
	}
	
	/**
	 * @param sigla the sigla to set
	 */
	public void setSigla(String sigla) {
		this.sigla = sigla;
	}
	
	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}
	
	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}	
}