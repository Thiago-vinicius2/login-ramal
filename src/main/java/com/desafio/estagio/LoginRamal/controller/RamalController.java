package com.desafio.estagio.LoginRamal.controller;

import com.desafio.estagio.LoginRamal.dto.LoginRamalDto;
import com.desafio.estagio.LoginRamal.dto.LogoutRamalDto;
import com.desafio.estagio.LoginRamal.dto.ResponseLoginRamalDto;
import com.desafio.estagio.LoginRamal.dto.ResponseLogoutRamalDto;
import com.desafio.estagio.LoginRamal.models.ramal.RamalModel;
import com.desafio.estagio.LoginRamal.service.RamalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/ramais")
public class RamalController {

    @Autowired
    private RamalService ramalService;

    // TODOS OS RAMAIS (DISPONIVEL - OCUPADO)
    @GetMapping("/todos")
    public ResponseEntity<List<ResponseLoginRamalDto>> listarTodos(
            @RequestParam(required = false) Integer inicio,
            @RequestParam(required = false) Integer fim) {
        List<RamalModel> ramais = ramalService.todosRamais();

        if (inicio != null && fim != null ){
            ramais = ramais.stream()
                    .filter(r -> r.getNumeroRamal() >= inicio && r.getNumeroRamal() <= fim)
                    .collect(Collectors.toList());
        }

        List<ResponseLoginRamalDto> resposta =
                ramais.stream() // -> transforma para dto
                .map(ResponseLoginRamalDto::new) // -> → para cada ramal, cria um novo ResponseLoginRamalDto
                .toList(); // -> transforma em lista

        return ResponseEntity.ok(resposta);
    }

    // MOSTRA RAMAIS DISPONIVEIS
    @GetMapping("/disponiveis")
    public ResponseEntity<List<ResponseLoginRamalDto>> listarRamaisDisponiveis(
            @RequestParam(required = false) Integer inicio,
            @RequestParam(required = false) Integer fim) {

        List<RamalModel> disponiveis = ramalService.listarRamaisDisponiveis();

        if (inicio != null && fim != null ){
            disponiveis = disponiveis.stream()
                    .filter(r -> r.getNumeroRamal() >= inicio && r.getNumeroRamal() <= fim)
                    .collect(Collectors.toList());
        }

        List<ResponseLoginRamalDto> resposta =
                disponiveis.stream() // -> transforma para dto
                .map(ResponseLoginRamalDto::new) // -> → para cada ramal, cria um novo ResponseLoginRamalDto
                .toList(); // -> transforma em lista

        return ResponseEntity.ok(resposta);
    }

    // MOSTRA RAMAIS OCUPADOS
    @GetMapping("/ocupados")
    public ResponseEntity<List<ResponseLoginRamalDto>> listarRamaisOcupados(
            @RequestParam(required = false) Integer inicio,
            @RequestParam(required = false) Integer fim) {
        List<RamalModel> ocupados = ramalService.listarRamaisOcupados();

        if (inicio != null && fim != null ){
            ocupados = ocupados.stream()
            .filter(r -> r.getNumeroRamal() >= inicio && r.getNumeroRamal() <= fim)
            .collect(Collectors.toList());
        }

        List<ResponseLoginRamalDto> resposta =
                ocupados.stream() // -> transforma para dto
                .map(ResponseLoginRamalDto::new) // -> → para cada ramal, cria um novo ResponseLoginRamalDto
                .toList(); // -> transforma em lista

        return ResponseEntity.ok(resposta);
    }

    // BUSCAR UM RAMAL ESPECIFICO
    @GetMapping("/buscar")
    public ResponseEntity<ResponseLoginRamalDto> buscarPorNumero (@RequestParam Integer ramal) {
        Optional<RamalModel> numero = ramalService.buscaRamal(ramal);

        return numero
                .map(RamalModel -> ResponseEntity.ok(new ResponseLoginRamalDto(RamalModel)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // FAZER LOGIN NO RAMAL
    @PostMapping("/loginRamal")
    public ResponseEntity<ResponseLoginRamalDto> loginRamal(@RequestBody LoginRamalDto dto){
        ResponseLoginRamalDto mensagem = ramalService.fazerLoginRamal(dto);
        return ResponseEntity.ok(mensagem);
    }

    // FAZER LOGOUT NO RAMAL
    @DeleteMapping("/logoutRamal")
    public ResponseEntity<ResponseLogoutRamalDto> logoutRamal(@RequestBody LogoutRamalDto dto) {
        ResponseLogoutRamalDto mensagem = ramalService.fazerLogoutRamal(dto);
        return  ResponseEntity.ok(mensagem);
    }

}
