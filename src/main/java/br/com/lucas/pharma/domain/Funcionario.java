package br.com.lucas.pharma.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@SuppressWarnings("serial")
@Entity
public class Funcionario extends GenericDomain {
	
	@Column(length = 20, nullable = false)
	private String carteiraTrabalho;
	
	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date dataAdmissao;
	
	@JoinColumn(nullable = false)
	@OneToOne	
	private Pessoa pessoa;
	
	/**
	 * @return the carteiraTrabalho
	 */
	public String getCarteiraTrabalho() {
		return carteiraTrabalho;		
	}
	
	/**
	 * @param carteiraTrabalho the carteiraTrabalho to set
	 */
	public void setCarteiraTrabalho(String carteiraTrabalho) {
		this.carteiraTrabalho = carteiraTrabalho;
	}
	
	/**
	 * @return the dataAdmissao
	 */
	public Date getDataAdmissao() {
		return dataAdmissao;
	}
	
	/**
	 * @param dataAdmissao the dataAdmissao to set
	 */
	public void setDataAdmissao(Date dataAdmissao) {
		this.dataAdmissao = dataAdmissao;
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
}