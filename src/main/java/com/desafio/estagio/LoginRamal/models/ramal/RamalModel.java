package com.desafio.estagio.LoginRamal.models.ramal;

import com.desafio.estagio.LoginRamal.models.user.UsuarioModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="ramal")
public class RamalModel {

    @Id
    @Column(name="id")
    private Integer id;

    @Column(name="numero_ramal", unique = true, nullable = false)
    private Integer numeroRamal;

    @Enumerated(EnumType.STRING)
    @Column(name="status_ramal", nullable = false)
    private StatusRamal statusRamal;

    @OneToOne
    @JoinColumn(name="usuario_id")
    private UsuarioModel usuario;

}

