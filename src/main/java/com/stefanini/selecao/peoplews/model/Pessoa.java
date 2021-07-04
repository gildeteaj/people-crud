package com.stefanini.selecao.peoplews.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.stefanini.selecao.peoplews.controller.v2.dto.PessoaComEnderecoFormDto;

import io.swagger.annotations.ApiModelProperty;

@Document(collection = "Pessoa")
public class Pessoa {
	/*
	 * Nome - obrigatório  Sexo  E-mail - não obrigatório, deve ser validado caso
	 * preenchido  Data de Nascimento - obrigatório, deve ser validada 
	 * Naturalidade  Nacionalidade  CPF - obrigatório, deve ser validado (formato
	 * e não pode haver dois cadastros com mesmo CPF)
	 * 
	 */

	@Transient
	public static final String SEQUENCE_NAME = "pessoa_sequence";

	@Id
	private Long id;

	@NotBlank(message = "Nome deve ser preenchido")
	@Size(max = 100)
	private String nome;

	private String sexo;

	@Email(message = "Email Inválido")
	private String email;

	@NotNull(message = "Data de Nascimento deve ser preenchida")
	@JsonSerialize(as = LocalDate.class)
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy")
	@ApiModelProperty(example = "15/03/1980")		
	private LocalDate dataNascimento;
	
	private String naturalidade;
	private String nacionalidade;

	@NotBlank(message = "CPF deve ser preenchido")
	@Indexed(unique = true)
	@CPF(message = "CPF Inválido. Formato=999.999.999-99")
	private String cpf;

	@JsonIgnore
	@DateTimeFormat(pattern="dd/MM/yyyy hh:mm:ss")
	private LocalDateTime dataCadastro;
	
	@JsonIgnore
	@DateTimeFormat(pattern="dd/MM/yyyy hh:mm:ss")
	private LocalDateTime dataUltAtualizacao;
	
	
	private Endereco endereco;

	public Pessoa() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Pessoa(Long id, @NotBlank @Size(max = 100) String nome, String sexo, @Email String email,
			LocalDate dataNascimento, String naturalidade, String nacionalidade, @NotNull @CPF String cpf) {
			
		super();
		this.id = id;
		this.nome = nome;
		this.sexo = sexo;
		this.email = email;
		this.dataNascimento = dataNascimento;
		this.naturalidade = naturalidade;
		this.nacionalidade = nacionalidade;
		this.cpf = cpf;
		
	}
	
	public Pessoa(PessoaComEnderecoFormDto pessoa) {
		super();		
		this.nome = pessoa.getNome();
		this.sexo = pessoa.getSexo();
		this.email = pessoa.getEmail();
		this.dataNascimento = pessoa.getDataNascimento();
		this.naturalidade = pessoa.getNaturalidade();
		this.nacionalidade = pessoa.getNacionalidade();
		this.cpf = pessoa.getCpf();	
		this.endereco = pessoa.getEndereco();
	}

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

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
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

	public String getNaturalidade() {
		return naturalidade;
	}

	public void setNaturalidade(String naturalidade) {
		this.naturalidade = naturalidade;
	}

	public String getNacionalidade() {
		return nacionalidade;
	}

	public void setNacionalidade(String nacionalidade) {
		this.nacionalidade = nacionalidade;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public LocalDateTime getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(LocalDateTime dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public LocalDateTime getDataUltAtualizacao() {
		return dataUltAtualizacao;
	}

	public void setDataUltAtualizacao(LocalDateTime dataUltAtualizacao) {
		this.dataUltAtualizacao = dataUltAtualizacao;
	}
	
	
	

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cpf);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pessoa other = (Pessoa) obj;
		return Objects.equals(cpf, other.cpf);
	}

	@Override
	public String toString() {
		return "Pessoa [nome=" + nome + ", sexo=" + sexo + ", email=" + email + ", dataNascimento=" + dataNascimento
				+ ", naturalidade=" + naturalidade + ", nacionalidade=" + nacionalidade + ", cpf=" + cpf
				+ ", dataCadastro=" + dataCadastro + ", dataUltAtualizacao=" + dataUltAtualizacao + "]";
	}

}
