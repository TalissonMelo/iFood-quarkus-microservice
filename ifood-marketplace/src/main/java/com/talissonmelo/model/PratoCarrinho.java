package com.talissonmelo.model;

import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.pgclient.PgPool;
import io.vertx.mutiny.sqlclient.Row;
import io.vertx.mutiny.sqlclient.Tuple;

import java.util.ArrayList;
import java.util.List;

public class PratoCarrinho {
    private String usuario;
    private Long prato;

    public static Uni<List<PratoCarrinho>> buscarCarrinho(PgPool pgPool, String cliente) {
        return pgPool.preparedQuery("select * from prato_cliente where cliente = $1 " ).execute(Tuple.of(cliente))
            .map(pgRowSet -> {
                List<PratoCarrinho> pratoCarrinhos = new ArrayList<>(pgRowSet.size());
                    for(Row row: pgRowSet) {
                        pratoCarrinhos.add(toPratoCarrinho(row));
                    }
                    return pratoCarrinhos;
            });
    }

    private static PratoCarrinho toPratoCarrinho(Row row) {
        PratoCarrinho pc = new PratoCarrinho();
        pc.usuario = row.getString("cliente");
        pc.prato = row.getLong("prato");
        return pc;
    }
}
