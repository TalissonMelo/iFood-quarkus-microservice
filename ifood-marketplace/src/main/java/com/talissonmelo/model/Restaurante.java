package com.talissonmelo.model;

import com.talissonmelo.dto.PratoDto;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.pgclient.PgPool;
import io.vertx.mutiny.sqlclient.Row;
import io.vertx.mutiny.sqlclient.RowSet;
import io.vertx.mutiny.sqlclient.Tuple;

import java.util.stream.StreamSupport;

public class Restaurante {

    public Long id;
    public String nome;
    public Localizacao localizacao;

    public static Multi<PratoDto> buscarTodos(PgPool pgPool, Long idRestaurante) {
        Uni<RowSet<Row>> preparedQuery = pgPool
                .preparedQuery("SELECT * FROM prato where prato.restaurante_id = $1 ORDER BY nome ASC").execute(
                        Tuple.of(idRestaurante));
        return unitToMulti(preparedQuery);
    }

    private static Multi<PratoDto> unitToMulti(Uni<RowSet<Row>> queryResult) {
        return queryResult.onItem()
                .produceMulti(set -> Multi.createFrom().items(() -> {
                    return StreamSupport.stream(set.spliterator(), false);
                }))
                .onItem().apply(PratoDto::from);
    }

    public void persistirRestaurante(PgPool pgPool) {
        pgPool.preparedQuery("insert into localizacao (id, latitude, longitude) values ($1, $2, $3)").execute(
                Tuple.of(localizacao.id, localizacao.latitude, localizacao.longitude)).await().indefinitely();

        pgPool.preparedQuery("insert into restaurante (id, nome, localizacao_id) values ($1, $2, $3)").execute(
                Tuple.of(id, nome, localizacao.id)).await().indefinitely();

    }
}
