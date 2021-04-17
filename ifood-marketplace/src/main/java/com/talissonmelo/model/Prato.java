package com.talissonmelo.model;

import com.talissonmelo.dto.PratoDto;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.pgclient.PgPool;
import io.vertx.mutiny.sqlclient.Row;
import io.vertx.mutiny.sqlclient.RowSet;
import io.vertx.sqlclient.Tuple;

import java.math.BigDecimal;
import java.util.stream.StreamSupport;

public class Prato {
    public Long id;
    public String nome;
    public String descricao;
    public Restaurante restaurante;
    public BigDecimal preco;

    public static Multi<PratoDto> buscarTodos(PgPool pgPool){
        Uni<RowSet<Row>> preparedQuery = pgPool.query("SELECT * FROM PRATO").execute();
        return preparedQuery.onItem().produceMulti(rowSet -> Multi.createFrom().items(() -> {
            return StreamSupport.stream(rowSet.spliterator(),false);
        })).onItem().apply(PratoDto::from);
    }
}
