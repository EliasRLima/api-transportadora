package edu.ifma.lpweb.freteapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
public class Frete {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

     private String descricao;
    private Float peso;
    private BigDecimal valor;

    @ManyToOne
    private Cliente cliente;

    @ManyToOne
    private Cidade cidade;

    public BigDecimal calcularFrete() {
        // R$10,00 é o valor fixo para o cálculo
        this.valor = new BigDecimal(this.peso * 10).add(this.cidade.getTaxa() );
        return this.getValor();

    }

}