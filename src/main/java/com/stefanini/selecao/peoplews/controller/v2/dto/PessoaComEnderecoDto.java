package com.stefanini.selecao.peoplews.controller.v2.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.data.domain.Page;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.stefanini.selecao.peoplews.model.Endereco;
import com.stefanini.selecao.peoplews.model.Pessoa;

public class PessoaComEnderecoDto {

	private Long id;

	
	private String nome;

	private String sexo;

	
	private String email;

	
	@JsonSerialize(as = LocalDate.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate dataNascimento;

	private String naturalidade;
	private String nacionalidade;

	
	private String cpf;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy hh:mm:ss")
	private LocalDateTime dataCadastro;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy hh:mm:ss")
	private LocalDateTime dataUltAtualizacao;

	
	private Endereco endereco;
	
	
	

	public PessoaComEnderecoDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PessoaComEnderecoDto(Pessoa pessoa) {
		super();
		this.id = pessoa.getId();
		this.nome = pessoa.getNome();
		this.sexo = pessoa.getSexo();
		this.email = pessoa.getEmail();
		this.dataNascimento = pessoa.getDataNascimento();
		this.naturalidade = pessoa.getNaturalidade();
		this.nacionalidade = pessoa.getNacionalidade();
		this.cpf = pessoa.getCpf();
		this.dataCadastro = pessoa.getDataCadastro();
		this.dataUltAtualizacao = pessoa.getDataUltAtualizacao();
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

	public static Page<PessoaComEnderecoDto> converter(Page<Pessoa> pessoas) {
		return pessoas.map(PessoaComEnderecoDto::new);
	}

}
