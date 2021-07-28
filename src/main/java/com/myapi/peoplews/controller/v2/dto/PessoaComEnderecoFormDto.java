package com.myapi.peoplews.controller.v2.dto;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;
import org.springframework.data.domain.Page;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.myapi.peoplews.model.Endereco;
import com.myapi.peoplews.model.Pessoa;

public class PessoaComEnderecoFormDto {

	private Long id;

	@NotBlank(message = "Nome deve ser preenchido")
	@Size(max = 100)
	private String nome;

	private String sexo;

	@Email(message = "Email Inválido")
	private String email;

	@NotNull(message = "Data de Nascimento deve ser preenchida")
	@JsonSerialize(as = LocalDate.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate dataNascimento;

	private String naturalidade;
	private String nacionalidade;

	@NotBlank(message = "CPF deve ser preenchido")
	@CPF(message = "CPF Inválido. Formato=999.999.999-99")
	private String cpf;

	
	@NotNull(message = "Endereço deve ser preenchido")
	private Endereco endereco;
	
	
	

	public PessoaComEnderecoFormDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PessoaComEnderecoFormDto(Pessoa pessoa) {
		super();
		this.id = pessoa.getId();
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

	
	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public static Page<PessoaComEnderecoFormDto> converter(Page<Pessoa> pessoas) {
		return pessoas.map(PessoaComEnderecoFormDto::new);
	}

}
