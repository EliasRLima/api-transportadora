package com.ifma.api.entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Frete {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer codigoFrete;

    @ManyToOne
    @JoinColumn(name = "codigo_cidade")
    private Cidade cidade;
    @ManyToOne
    @JoinColumn(name = "codigo_cliente")
    private Cliente cliente;
    private String descricao;
    private Double peso;
    private Double valor;
}
