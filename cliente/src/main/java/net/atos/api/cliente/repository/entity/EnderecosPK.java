package net.atos.api.cliente.repository.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Embeddable
public class EnderecosPK implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1830195603109602835L;

	@Column(name = "ID_END")
	@NotNull(message = "Campo Id Endereço não pode ser nulo")
	private Integer idEnd;

	@ManyToOne
	@JoinColumn(name="ID_CLIENTE")
	private ClienteEntity cliente;

	public Integer getIdEnd() {
		return idEnd;
	}

	public void setIdEnd(Integer idEnd) {
		this.idEnd = idEnd;
	}

	public ClienteEntity getCliente() {
		return cliente;
	}

	public void setCliente(ClienteEntity clienteEntity) {
		this.cliente = clienteEntity;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(cliente, idEnd);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EnderecosPK other = (EnderecosPK) obj;
		return Objects.equals(cliente, other.cliente) && Objects.equals(idEnd, other.idEnd);
	}



}