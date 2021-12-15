package net.atos.api.cliente.repository.entity;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Embeddable
public class EnderecosPK {

	@Column(name = "ID_END")
	@NotNull(message = "Campo Id Endereço não pode ser nulo")
	private Integer idEnd;

	@ManyToOne
	@JoinColumn(name="ID_CLIENTE")
	private ClienteEntity clienteEntity;

	public Integer getIdEnd() {
		return idEnd;
	}

	public void setIdEnd(Integer idEnd) {
		this.idEnd = idEnd;
	}

	public ClienteEntity getClienteEntity() {
		return clienteEntity;
	}

	public void setClienteEntity(ClienteEntity clienteEntity) {
		this.clienteEntity = clienteEntity;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(clienteEntity, idEnd);
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
		return Objects.equals(clienteEntity, other.clienteEntity) && Objects.equals(idEnd, other.idEnd);
	}



}