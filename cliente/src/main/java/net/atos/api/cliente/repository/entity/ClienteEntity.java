package net.atos.api.cliente.repository.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import net.atos.api.cliente.domain.TipoPessoaEnum;

@Entity
@Table(name = "TB_CLIENTE")
public class ClienteEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_CLIENTE")
	private Long id;

	@Column(name = "NOME_CLIENTE")
	@NotNull(message = "Campo Nome não pode ser nulo")
	private String nome;

	@Column(name = "TIPO_PESSOA", insertable = false, updatable = false)
//	@NotNull(message = "Campo Tipo Pessoa não pode ser nulo")
	@Enumerated(EnumType.STRING)
	private TipoPessoaEnum tipoPessoa;

	@Column(name = "DOC_PRINCIPAL")
	@NotNull(message = "Campo docPrincipal não pode ser nulo e nem vazio")
	private String docPrincipal;

	@Column(name = "TELEFONE")
	@NotNull(message = "Campo telefone não pode ser nulo e nem vazio")
	private String telefone;

	@Column(name = "CELULAR")
	private String celular;

	@Column(name = "EMAIL")
	@NotNull(message = "Campo e-mail não pode ser nulo e nem vazio")
	private String email;

	@Column(name = "DATA_NASCIMENTO")
	@NotNull(message = "Campo DataNascimento não pode ser nulo")
	private LocalDate dataNascimento;

	@Column(name = "DATA_CRIACAO")
	@NotNull(message = "Campo DataCriacao não pode ser nulo")
	private LocalDate dataCriacao;


//	@NotNull(message = "Campo Endereços não pode ser nulo")
//	@Size(min = 1, message = "Campo Endereços deve ser pelo menos um item")
//	@Valid
//	@OneToMany(mappedBy = "tb_cliente")
//	@JsonIgnore
//	@OneToMany(fetch = FetchType.EAGER)
	@OneToMany(cascade = CascadeType.ALL)
	@JsonIgnore
	private List<EnderecosEntity> enderecos;


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public TipoPessoaEnum getTipoPessoa() {
		return tipoPessoa;
	}


	public void setTipoPessoa(TipoPessoaEnum tipoPessoa) {
		this.tipoPessoa = tipoPessoa;
	}


	public String getDocPrincipal() {
		return docPrincipal;
	}


	public void setDocPrincipal(String docPrincipal) {
		this.docPrincipal = docPrincipal;
	}


	public String getTelefone() {
		return telefone;
	}


	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}


	public String getCelular() {
		return celular;
	}


	public void setCelular(String celular) {
		this.celular = celular;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public LocalDate getDataNascimento() {
		return dataNascimento;
	}


	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}


	public LocalDate getDataCriacao() {
		return dataCriacao;
	}


	public void setDataCriacao(LocalDate dataCriacao) {
		this.dataCriacao = dataCriacao;
	}


	public List<EnderecosEntity> getEnderecos() {
		return enderecos;
	}


	public void setEnderecos(List<EnderecosEntity> enderecos) {
		this.enderecos = enderecos;
	}

	public void add(EnderecosEntity endereco) {
		List<EnderecosEntity> enderecosLocal =
				Optional.ofNullable(this.getEnderecos()).orElseGet(() -> new ArrayList());
		enderecosLocal.add(endereco);

		this.enderecos = enderecosLocal;
	}



	@Override
	public int hashCode() {
		return Objects.hash(id);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ClienteEntity other = (ClienteEntity) obj;
		return Objects.equals(id, other.id);
	}


	@Override
	public String toString() {
		return "ClienteEntity [id=" + id + ", nome=" + nome + ", tipoPessoa=" + tipoPessoa + ", docPrincipal="
				+ docPrincipal + ", telefone=" + telefone + ", celular=" + celular + ", email=" + email
				+ ", dataNascimento=" + dataNascimento + ", dataCriacao=" + dataCriacao + ", enderecos=" + enderecos
				+ "]";
	}
	
	



}