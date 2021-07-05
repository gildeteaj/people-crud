package com.stefanini.selecao.peoplews.controller;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.stefanini.selecao.peoplews.controller.dto.PessoaDto;
import com.stefanini.selecao.peoplews.exception.ErroDeFormulario;
import com.stefanini.selecao.peoplews.model.Pessoa;
import com.stefanini.selecao.peoplews.repository.PessoaRepository;
import com.stefanini.selecao.peoplews.service.SequenceGenerator;

@RestController
@RequestMapping("/api/v1/pessoas")


public class PessoaController {
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private SequenceGenerator sequenceGenerator;
	
	
	
	@GetMapping	
	@Cacheable(value = "listaDePessoas")	
	public List<PessoaDto> listar(@RequestParam(required = false) String cpf) {
		
		if (cpf == null) {
			List<Pessoa> pessoas = pessoaRepository.findAll();
			return PessoaDto.converter(pessoas);
		} else {
			List<Pessoa> pessoas = pessoaRepository.findByCpf(cpf);
			return PessoaDto.converter(pessoas);
		}
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<PessoaDto> detalhar(@PathVariable Long id) {
		Optional<Pessoa> pessoa = pessoaRepository.findById(id);
		if (pessoa.isPresent()) {
			return ResponseEntity.ok(new PessoaDto(pessoa.get()));
		}
		
		return ResponseEntity.notFound().build();
	}
	
	
	@PostMapping
	@Transactional
	@CacheEvict(value = "listaDePessoas", allEntries = true)
	public ResponseEntity<?> cadastrar(@RequestBody @Valid Pessoa pessoa, UriComponentsBuilder uriBuilder) {
		List<Pessoa> pcpf = pessoaRepository.findByCpf(pessoa.getCpf());
		if (!pcpf.isEmpty()) {
			ErroDeFormulario erro = new ErroDeFormulario("CPF", "CPF já cadastrado. Não pode haver dois cadastros com mesmo CPF");
			return new ResponseEntity<>(erro, HttpStatus.BAD_REQUEST);			
		}
		
		pessoa.setId(sequenceGenerator.generateSequence(Pessoa.SEQUENCE_NAME));
		pessoa.setDataCadastro(LocalDateTime.now());
		pessoa.setDataUltAtualizacao(LocalDateTime.now());
		pessoaRepository.save(pessoa);
		
		URI uri = uriBuilder.path("/pessoas/{id}").buildAndExpand(pessoa.getId()).toUri();
		return ResponseEntity.created(uri).body(new PessoaDto(pessoa));
	}
	
	
	@PutMapping("/{id}")
	@Transactional
	@CacheEvict(value = "listaDePessoas", allEntries = true)
	public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody @Valid Pessoa pessoaForm) {
		List<Pessoa> pcpf = pessoaRepository.findByCpf(pessoaForm.getCpf());
		if (!pcpf.isEmpty() && !pcpf.get(0).getId().equals(pessoaForm.getId())) {
			ErroDeFormulario erro = new ErroDeFormulario("CPF", "CPF já cadastrado. Não pode haver dois cadastros com mesmo CPF");
			return new ResponseEntity<>(erro, HttpStatus.BAD_REQUEST);			
		}
		
		Optional<Pessoa> optional = pessoaRepository.findById(id);
		if (optional.isPresent()) {
			Pessoa pessoa = optional.get();
			pessoa.setNome(pessoaForm.getNome());
			pessoa.setCpf(pessoaForm.getCpf());
			pessoa.setDataNascimento(pessoaForm.getDataNascimento());
			pessoa.setDataUltAtualizacao(LocalDateTime.now());
			pessoa.setEmail(pessoaForm.getEmail());
			pessoa.setNacionalidade(pessoaForm.getNacionalidade());
			pessoa.setNaturalidade(pessoaForm.getNaturalidade());
			final Pessoa updatedPessoa = pessoaRepository.save(pessoa);
			return ResponseEntity.ok(new PessoaDto(updatedPessoa));
		}		
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	@CacheEvict(value = "listaDePessoas", allEntries = true)
	public ResponseEntity<?> remover(@PathVariable Long id) {
		Optional<Pessoa> optional = pessoaRepository.findById(id);
		if (optional.isPresent()) {
			pessoaRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		
		return ResponseEntity.notFound().build();
	}

}