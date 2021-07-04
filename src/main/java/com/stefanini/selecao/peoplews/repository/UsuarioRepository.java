package com.stefanini.selecao.peoplews.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.stefanini.selecao.peoplews.model.Usuario;

@Repository
public interface UsuarioRepository extends MongoRepository<Usuario, Long> {

}
