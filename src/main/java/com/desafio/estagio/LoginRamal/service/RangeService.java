package com.desafio.estagio.LoginRamal.service;

import lombok.Getter;
import org.springframework.stereotype.Service;

@Service
@Getter
public class RangeService {
    private Integer inicio;
    private Integer fim;

    public void definirIntervalo(Integer inicio, Integer fim) {
        this.inicio = inicio;
        this.fim = fim;
    }

    public boolean isDentroDoRange (Integer numero) {
        return inicio != null && fim != null && numero >= inicio && numero <= fim;
    }
}
