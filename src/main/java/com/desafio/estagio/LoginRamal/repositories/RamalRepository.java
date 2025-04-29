package com.desafio.estagio.LoginRamal.repositories;

import com.desafio.estagio.LoginRamal.models.ramal.RamalModel;
import com.desafio.estagio.LoginRamal.models.ramal.StatusRamal;
import com.desafio.estagio.LoginRamal.models.user.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RamalRepository extends JpaRepository<RamalModel, Integer> {

    // RETORNA RAMAIS PELO STATUS
    List<RamalModel> findByStatusRamal(StatusRamal statusRamal);

    // BUSCAR RAMAL POR NUMERO
    Optional<RamalModel> findByNumeroRamal(Integer numeroRamal);

    // VERIFICAR SE O USUARIO ESTÁ LOGADO EM OUTRO RAMAL
    Optional<RamalModel> findByUsuarioAndStatusRamal(UsuarioModel usuarioModel, StatusRamal statusRamal);
}