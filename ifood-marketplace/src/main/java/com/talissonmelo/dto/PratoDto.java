package com.talissonmelo.dto;


import io.vertx.mutiny.sqlclient.Row;

import java.math.BigDecimal;

public class PratoDto {
    public Long id;
    public String nome;
    public String descricao;
    public BigDecimal preco;

    public static PratoDto from(Row row){
        PratoDto prato = new PratoDto();
        prato.descricao = row.getString("descricao");
        prato.nome = row.getString("nome");
        prato.id = row.getLong("id");
        prato.preco = row.getBigDecimal("preco");
        return prato;
    }
}
