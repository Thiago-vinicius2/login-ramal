package com.desafio.estagio.LoginRamal.controller;

import com.desafio.estagio.LoginRamal.dto.LoginRamalDto;
import com.desafio.estagio.LoginRamal.dto.LogoutRamalDto;
import com.desafio.estagio.LoginRamal.dto.ResponseLoginRamalDto;
import com.desafio.estagio.LoginRamal.dto.ResponseLogoutRamalDto;
import com.desafio.estagio.LoginRamal.models.ramal.RamalModel;
import com.desafio.estagio.LoginRamal.service.RamalService;
import com.desafio.estagio.LoginRamal.service.RangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/ramais")
public class RamalController {

    @Autowired
    private RamalService ramalService;

    @Autowired
    private RangeService rangeService;

    // FILTRA LISTA DO RAMALMODEL DEFINIDA PELA INTERVALO
    private List<RamalModel> filtrarPorRange(List<RamalModel> lista) {
        return lista.stream()
                .filter(r -> rangeService.isDentroDoRange(r.getNumeroRamal()))
                .collect(Collectors.toList());
    }

    // TODOS OS RAMAIS (DISPONIVEL - OCUPADO)
    @GetMapping("/todos")
    public ResponseEntity<List<ResponseLoginRamalDto>> listarTodos() {
        List<RamalModel> ramais = filtrarPorRange(ramalService.todosRamais());

        List<ResponseLoginRamalDto> resposta = ramais.stream() // -> transforma para dto
                .map(ResponseLoginRamalDto::new) // -> → para cada ramal, cria um novo ResponseLoginRamalDto
                .toList(); // -> transforma em lista

        return ResponseEntity.ok(resposta);
    }

    // RAMAIS DEFINIDOS POR RANGE
    @GetMapping("/range")
    public ResponseEntity<String> definirRange(
            @RequestParam(required = false) Integer inicio,
            @RequestParam(required = false) Integer fim) {

        int ramalMin = 3601;
        int ramalMax = 3625;

        if(inicio >= fim) {
            return ResponseEntity.badRequest().body("Intervalo Invalido: Inicio deve ser menor que o fim.");
        }

        if (inicio < ramalMin || fim > ramalMax)
            return ResponseEntity.badRequest().body(
                    "Numeros invalidos para ramais, tente novamente: " + ramalMin + " até " + ramalMax);

        rangeService.definirIntervalo(inicio, fim);
        return ResponseEntity.ok("Intervalo definido: " + inicio + " a " + fim);
    }

    // OBTER O RANGE ATUAL
    @GetMapping("/range/atual")
    public ResponseEntity<Map<String, Integer>> obterRangeAtual() {
        Map<String, Integer> response = new HashMap<>();
        response.put("inicio", rangeService.getInicio());
        response.put("fim", rangeService.getFim());
        return ResponseEntity.ok(response);
    }

    // MOSTRA RAMAIS DISPONIVEIS
    @GetMapping("/disponiveis")
    public ResponseEntity<List<ResponseLoginRamalDto>> listarRamaisDisponiveis(){
        List<RamalModel> disponiveis = filtrarPorRange(ramalService.listarRamaisDisponiveis());

        List<ResponseLoginRamalDto> resposta = disponiveis.stream()
                .map(ResponseLoginRamalDto::new)
                .toList();

        return ResponseEntity.ok(resposta);
    }

    // MOSTRA RAMAIS OCUPADOS
    @GetMapping("/ocupados")
    public ResponseEntity<List<ResponseLoginRamalDto>> listarRamaisOcupados() {
        List<RamalModel> ocupados = filtrarPorRange(ramalService.listarRamaisOcupados());

        List<ResponseLoginRamalDto> resposta = ocupados.stream()
                .map(ResponseLoginRamalDto::new) //
                .toList();

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
    public ResponseEntity<ResponseLoginRamalDto> loginRamal(
                @RequestBody LoginRamalDto dto,
                @RequestParam(required = false) Integer inicio,
                @RequestParam(required = false) Integer fim
    ){
        // ATUALIZA O RANGE NO LOGIN
        if (inicio!= null && fim != null) {
            rangeService.definirIntervalo(inicio, fim);
    }

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
