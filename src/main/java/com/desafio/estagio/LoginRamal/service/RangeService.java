package com.desafio.estagio.LoginRamal.service;

import lombok.Getter;
import org.springframework.stereotype.Service;

@Service
@Getter
public class RangeService {
    private Integer inicio = null;
    private Integer fim = null;

    public void definirIntervalo(Integer inicio, Integer fim) {
        this.inicio = inicio;
        this.fim = fim;
    }

    public boolean isDentroDoRange (Integer numero) {
        if (inicio == null || fim == null){
            return true;
        }
        return numero >= inicio && numero <= fim;
    }
}
