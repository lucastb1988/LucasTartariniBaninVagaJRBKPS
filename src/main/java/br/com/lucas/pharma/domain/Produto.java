package br.com.lucas.pharma.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@SuppressWarnings("serial")
@Entity
public class Produto extends GenericDomain {

	@Column(length = 80, nullable = false)
	private String descricao;

	@Column(nullable = false)
	private Short quantidade;

	@Column(precision = 6, scale = 2, nullable = false)
	private BigDecimal preco;

	@JoinColumn(nullable = false)
	@ManyToOne
	private Fabricante fabricante;

	/**
	 * @return the descricao
	 */	
	public String getDescricao() {
		return descricao;
	}
	
	/**
	 * @param descricao the descricao to set
	 */	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
		
	/**
	 * @return the quantidade
	 */
	public Short getQuantidade() {
		
		return quantidade;
	}
	
	/**
	 * @param quantidade the quantidade to set
	 */
	public void setQuantidade(Short quantidade) {
		this.quantidade = quantidade;
	}
	
	/**
	 * @return the preco
	 */
	
	public BigDecimal getPreco() {
		return preco;
	}
	/**
	 * @param preco the preco to set
	 */
	public void setPreco(BigDecimal preco) {
		this.preco = preco;
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
}