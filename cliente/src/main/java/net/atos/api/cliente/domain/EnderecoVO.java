package net.atos.api.cliente.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonFormat;

public class EnderecoVO {
	
	
	private Long id;
	
	@NotNull(message = "Campo Rua não pode ser nulo ou vazio")
	private String rua;
	
	//@Pattern(regexp = "[0-9]+", message = "Apenas numeros")
	@NotNull(message = "Campo Número não pode ser nulo ou vazio")
	private String numero;
	
	private String complemento;
	
	@NotNull(message = "Campo Bairro não pode ser nulo ou vazio")
	private String bairro;
	
	@NotNull(message = "Campo Cidade não pode ser nulo ou vazio")
	private String cidade;
	
	@NotNull(message = "Campo Estado não pode ser nulo ou vazio")
	private String estado;
	
	@NotNull(message = "Campo cep não pode ser nulo ou vazio")
	private String cep;
	
//	@NotNull(message = "Campo não pode ser nulo")
//	private Boolean endPadrao;
	

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
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	

	@Override
	public String toString() {
		return "EnderecoVO [rua=" + rua + ", numero=" + numero + ", complemento=" + complemento + ", bairro=" + bairro
				+ ", cidade=" + cidade + ", estado=" + estado + ", cep=" + cep + "]";
	}
	

//	public boolean isEndPadrao() {
//		return endPadrao;
//	}
//
//	public void setEndPadrao(boolean endPadrao) {
//		this.endPadrao = endPadrao;
//	}
	
	

}
