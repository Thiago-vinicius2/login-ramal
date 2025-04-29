package com.desafio.estagio.LoginRamal.dto;

import com.desafio.estagio.LoginRamal.models.ramal.RamalModel;

public record ResponseLoginRamalDto
        (Integer numeroRamal,
         String nomeUsuario,
         String emailUsuario,
         String statusRamal)
{
    public ResponseLoginRamalDto(RamalModel ramal) {
        this(
                ramal.getNumeroRamal(),
                ramal.getUsuario() != null ? ramal.getUsuario().getNome() + " " + ramal.getUsuario().getSobrenome() : null,
                ramal.getUsuario() != null ? ramal.getUsuario().getEmail() : null,
                ramal.getStatusRamal().name()
        );
    }
}
