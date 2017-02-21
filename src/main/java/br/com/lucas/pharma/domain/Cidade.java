package br.com.lucas.pharma.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@SuppressWarnings("serial")
@Entity
public class Cidade extends GenericDomain {

	@Column(length = 50, nullable = false) // informa o tamanho da variavel no banco de dados, informa que a variável não pode ser nula
	private String nome;
	
	@JoinColumn(nullable = false) // JoinCollum é utilizado para chave estrangeira (não pode ser utilizado Column)
	@ManyToOne // relacionamento 1 para muitos (neste caso, 1 estado possui muitas cidades)
	private Estado estado;
	
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
}