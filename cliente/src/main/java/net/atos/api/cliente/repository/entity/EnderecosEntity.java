package net.atos.api.cliente.repository.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "TB_CLIENTE_ENDERECO")
public class EnderecosEntity implements Serializable{
	
	/**
	 * SERIAL UID
	 */
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_CLIENTE")
	private Long id;

	@Column(name = "RUA")
	@NotNull(message = "Campo Rua não pode ser nulo")
	private String rua;

	@Column(name = "NUMERO")
	@NotNull(message = "Campo Número não pode ser nulo ou vazio")
	private String numero;

	@Column(name = "COMPLEMENTO")
	private String complemento;

	@Column(name = "BAIRRO")
	@NotNull(message = "Campo Bairro não pode ser nulo ou vazio")
	private String bairro;

	@Column(name = "CIDADE")
	@NotNull(message = "Campo Cidade não pode ser nulo ou vazio")
	private String cidade;

	@Column(name = "ESTADO")
	@NotNull(message = "Campo Estado não pode ser nulo ou vazio")
	private String estado;

	@Column(name = "CEP")
	@NotNull(message = "Campo cep não pode ser nulo ou vazio")
	private String cep;

	@Column(name = "END_PADRAO", insertable = false, updatable = false)
	@NotNull(message = "Campo não pode ser nulo")
	private Boolean endPadrao;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="id_cliente", insertable = false, updatable = false)
	private ClienteEntity cliente;
	

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public Boolean isEndPadrao() {
		return endPadrao;
	}

	public void setEndPadrao(Boolean endPadrao) {
		this.endPadrao = endPadrao;
	}

	@Override
	public String toString() {
		return "EnderecosEntity [id=" + id + ", rua=" + rua + ", numero=" + numero + ", complemento=" + complemento
				+ ", bairro=" + bairro + ", cidade=" + cidade + ", estado=" + estado + ", cep=" + cep + ", endPadrao="
				+ endPadrao + "]";
	}

	

}