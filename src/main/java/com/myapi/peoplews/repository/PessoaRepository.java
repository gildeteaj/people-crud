package com.myapi.peoplews.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.myapi.peoplews.model.Pessoa;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Repository
public interface PessoaRepository extends MongoRepository<Pessoa, Long>{
	Page<Pessoa> findByCpf(String cpf, Pageable paginacao);
	
	List<Pessoa> findByCpf(String cpf);
}
