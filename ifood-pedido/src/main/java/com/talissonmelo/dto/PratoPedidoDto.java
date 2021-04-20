package com.talissonmelo.dto;

import java.math.BigDecimal;

public class PratoPedidoDto {

    public String nome;
    public String descricao;
    public BigDecimal preco;

    public PratoPedidoDto() {
    }

    public PratoPedidoDto(String nome, String descricao, BigDecimal preco) {
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
    }

}
