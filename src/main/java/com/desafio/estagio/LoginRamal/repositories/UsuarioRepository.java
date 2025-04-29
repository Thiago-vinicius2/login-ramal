package com.desafio.estagio.LoginRamal.repositories;

import com.desafio.estagio.LoginRamal.models.user.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioModel, Integer> {

    Optional<UsuarioModel> findByEmail (String email);
}
