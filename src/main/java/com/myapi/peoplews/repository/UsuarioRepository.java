package com.myapi.peoplews.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.myapi.peoplews.model.Usuario;

@Repository
public interface UsuarioRepository extends MongoRepository<Usuario, Long> {

}
