package com.stefanini.selecao.peoplews.controller.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.stefanini.selecao.peoplews.model.Pessoa;

import io.swagger.annotations.ApiModelProperty;

public class PessoaDto {

	private Long id;

	private String nome;

	private String sexo;

	private String email;

	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy")
	@JsonSerialize(as = LocalDate.class)
	@ApiModelProperty(example = "15/03/1980")		
	private LocalDate dataNascimento;
	
	private String naturalidade;
	private String nacionalidade;

	private String cpf;

	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy hh:mm:ss")
	@JsonSerialize(as = LocalDateTime.class)
	private LocalDateTime dataCadastro;
	
	@JsonSerialize(as = LocalDateTime.class)
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy hh:mm:ss")
	private LocalDateTime dataUltAtualizacao;
	
	

	public PessoaDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PessoaDto(Pessoa pessoa) {
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

	public static List<PessoaDto> converter( List<Pessoa> pessoas) {
		List<PessoaDto> lista = new ArrayList<PessoaDto>();
		for (Pessoa p: pessoas) {
			lista.add(new PessoaDto(p));
		}		
		return lista;
	}

}
