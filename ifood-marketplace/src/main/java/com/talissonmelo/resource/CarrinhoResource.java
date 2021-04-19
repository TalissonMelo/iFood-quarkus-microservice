package com.talissonmelo.resource;

import com.talissonmelo.model.PratoCarrinho;
import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.pgclient.PgPool;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/carrinho")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CarrinhoResource {

    private static final String CLIENTE = "a";

    @Inject
    PgPool pgPool;

    @GET
    public Uni<List<PratoCarrinho>> buscarPrato() {
        return PratoCarrinho.buscarCarrinho(pgPool, CLIENTE);
    }
}
