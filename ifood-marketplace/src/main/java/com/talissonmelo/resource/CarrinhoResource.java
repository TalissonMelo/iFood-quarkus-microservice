package com.talissonmelo.resource;

import com.talissonmelo.model.PratoCarrinho;
import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.pgclient.PgPool;

import javax.inject.Inject;
import javax.ws.rs.*;
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

    @POST
    @Path("/prato/{idPrato}")
    public Uni<Long> adicionarPrato(@PathParam("idPrato") Long idPrato) {
        PratoCarrinho pratoCarrinho = new PratoCarrinho();
        pratoCarrinho.usuario = CLIENTE;
        pratoCarrinho.prato = idPrato;
        return PratoCarrinho.salvar(pgPool,CLIENTE,idPrato);
    }
}
