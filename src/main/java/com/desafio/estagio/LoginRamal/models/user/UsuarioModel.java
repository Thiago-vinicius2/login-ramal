package com.desafio.estagio.LoginRamal.models.user;

import com.desafio.estagio.LoginRamal.models.ramal.RamalModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "usuario")
public class UsuarioModel {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="nome", nullable = false)
    private String nome;

    @Column(name="sobrenome", nullable = false)
    private String sobrenome;

    @Email @Column(name="email",unique = true, nullable = false)
    private String email;

    @Column(name="senha", nullable = false)
    private String senha;

    @OneToOne(mappedBy = "usuario")
    @JsonIgnore
    private RamalModel ramal;
}
