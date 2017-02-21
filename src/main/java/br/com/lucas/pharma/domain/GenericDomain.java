package br.com.lucas.pharma.domain;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@SuppressWarnings("serial") //esconde os warnings que a ide está informando
@MappedSuperclass //Anotação para informar que não corresponde a uma tabela, porém será usada por outros para geração de tabelas //Classe pai neste caso nunca será uma tabela utilizando @MappedSuperclass, somente as classes filhas
public class GenericDomain implements Serializable{

	@Id //Informa que essa classe é uma chave primaria
	@GeneratedValue(strategy = GenerationType.AUTO) //Informa que o banco de dados irá controlar automaticamente esta variavel chave primaria
	private Long codigo;	

	@Override
	public String toString() { // este toString é utilizado para conversão do SelectOneMenu (utilizando Omnifaces.SelectItemsConverter)
		return String.format("%s[codigo = %d]", getClass().getSimpleName(), getCodigo());
	}	

	// Necessita gerar hashCode e equals para carregar o selectOneMenu na pagina da chave estrangeira dos xhtmls (utilizando Omnifaces.SelectItemsConverter)
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GenericDomain other = (GenericDomain) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

	/**
	 * @return the codigo
	 */
	public Long getCodigo() {
		return codigo;
	}

	/**
	 * @param codigo the codigo to set
	 */
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}	
}