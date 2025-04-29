package com.desafio.estagio.LoginRamal.service;

import com.desafio.estagio.LoginRamal.dto.LoginRamalDto;
import com.desafio.estagio.LoginRamal.dto.LogoutRamalDto;
import com.desafio.estagio.LoginRamal.dto.ResponseLoginRamalDto;
import com.desafio.estagio.LoginRamal.dto.ResponseLogoutRamalDto;
import com.desafio.estagio.LoginRamal.models.ramal.RamalModel;
import com.desafio.estagio.LoginRamal.models.ramal.StatusRamal;
import com.desafio.estagio.LoginRamal.models.user.UsuarioModel;
import com.desafio.estagio.LoginRamal.repositories.RamalRepository;
import com.desafio.estagio.LoginRamal.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class RamalService {

    @Autowired
    private RamalRepository ramalRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    // MOSTRA TODOS OS RAMAIS
    public List<RamalModel> todosRamais() {
        return ramalRepository.findAll();
    }

    // MOSTRA RAMAIS DISPONIVEIS
    public List<RamalModel> listarRamaisDisponiveis() {
        return ramalRepository.findByStatusRamal(StatusRamal.Disponivel);
    }

    // MOSTRA RAMAIS OCUPADOS
    public List<RamalModel> listarRamaisOcupados() {
        return ramalRepository.findByStatusRamal(StatusRamal.Ocupado);
    }


    // BUSCA RAMAL POR NUMERO
    public Optional<RamalModel> buscaRamal(Integer numeroRamal) {
        return ramalRepository.findByNumeroRamal(numeroRamal);
    }


    // FAZ LOGIN NO RAMAL
    public ResponseLoginRamalDto fazerLoginRamal(LoginRamalDto dto){

        // VERIFICA SE O EMAIL EXISTE
        UsuarioModel usuario = usuarioRepository.findByEmail(dto.email())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED,"Email invalido."));

        // VERIFICA SE O EMAIL JÁ ESTÁ LOGADO EM OUTRO RAMAL
        Optional<RamalModel> usuarioJaLogado = ramalRepository.findByUsuarioAndStatusRamal(usuario, StatusRamal.Ocupado);
        if(usuarioJaLogado.isPresent()) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Usuario já logado em outro ramal");
        }

        // VERIFICA SE O RAMAL EXISTE
        RamalModel ramal = ramalRepository.findByNumeroRamal(dto.numeroRamal())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ramal não encontrado."));

        // VERIFICA SE O RAMAL ESTÁ DISPONIVEL
        if (ramal.getStatusRamal() != StatusRamal.Disponivel ) {
            throw new RuntimeException("Ramal está ocupado");
        }

        // ATUALIZA STATUS E NOME DO USUARIO LOGADO NO RAMAL
        ramal.setStatusRamal(StatusRamal.Ocupado);
        ramal.setUsuario(usuario);

        // SALVA NO BANCO DE DADOS
        ramalRepository.save(ramal);

        return new ResponseLoginRamalDto(ramal);
    }

    // FAZ LOGOUT NO RAMAL
    public ResponseLogoutRamalDto fazerLogoutRamal(LogoutRamalDto dto) {

        // VERIFICA SE O RAMAL EXISTE
        RamalModel ramal = ramalRepository.findByNumeroRamal(dto.numeroRamal())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Ramal não encontrado"));

        UsuarioModel usuarioLogado = ramal.getUsuario();

        if(usuarioLogado == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Nenhum usuario está logado nesse ramal");
        }

        if (!usuarioLogado.getEmail().equals(dto.email())){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "E-mail não corresponde ao ramal informado");
        }

        ramal.setStatusRamal(StatusRamal.Disponivel);
        ramal.setUsuario(null);

        ramalRepository.save(ramal);

        return new ResponseLogoutRamalDto(ramal);
    }
}
