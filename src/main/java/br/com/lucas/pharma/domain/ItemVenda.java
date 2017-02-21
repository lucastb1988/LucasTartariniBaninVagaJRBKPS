package br.com.lucas.pharma.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@SuppressWarnings("serial")
@Entity
public class ItemVenda extends GenericDomain {
	
	@Column(nullable = false)
	private Short quantidade;
	
	@Column(precision = 6, scale = 2, nullable = false)
	private BigDecimal precoParcial;	

	@JoinColumn( nullable = false)	
	@ManyToOne
	private Produto produto;
	
	@JoinColumn(nullable = false)
	@ManyToOne	
	private Venda venda;
	
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
	 * @return the precoParcial
	 */
	public BigDecimal getPrecoParcial() {
		return precoParcial;
	}
	
	/**
	 * @param precoParcial the precoParcial to set
	 */
	public void setPrecoParcial(BigDecimal precoParcial) {
		this.precoParcial = precoParcial;
	}
	
	/**
	 * @return the produto
	 */
	public Produto getProduto() {
		return produto;
	}
	
	/**
	 * @param produto the produto to set
	 */
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	
	/**
	 * @return the venda
	 */
	public Venda getVenda() {
		return venda;
	}
	
	/**
	 * @param venda the venda to set
	 */
	public void setVenda(Venda venda) {
		this.venda = venda;
	}
}