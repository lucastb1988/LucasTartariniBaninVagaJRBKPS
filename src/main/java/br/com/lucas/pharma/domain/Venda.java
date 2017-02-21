package br.com.lucas.pharma.domain;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@SuppressWarnings("serial")
@Entity
public class Venda extends GenericDomain{
	
	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date horario;
	
	@Column(precision = 7, scale = 2, nullable = false)
	private BigDecimal valorTotal;	
	
	@ManyToOne
	private Cliente cliente; // cliente será quem paga a prazo ou a vista. se não for cliente pagará somente a vista(e não será registrado o nome do cliente no banco)
	
	@JoinColumn(nullable = false)
	@ManyToOne
	private Funcionario funcionario;	
	
	/**
	 * @return the horario
	 */
	public Date getHorario() {
		return horario;
	}
	
	/**
	 * @param horario the horario to set
	 */
	public void setHorario(Date horario) {
		this.horario = horario;
	}
	
	/**
	 * @return the valorTotal
	 */
	public BigDecimal getValorTotal() {
		return valorTotal;
	}
	
	/**
	 * @param valorTotal the valorTotal to set
	 */
	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}
	
	/**
	 * @return the cliente
	 */
	public Cliente getCliente() {
		return cliente;
	}
	
	/**
	 * @param cliente the cliente to set
	 */
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	/**
	 * @return the funcionario
	 */
	public Funcionario getFuncionario() {
		return funcionario;
	}
	
	/**
	 * @param funcionario the funcionario to set
	 */
	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}	
}