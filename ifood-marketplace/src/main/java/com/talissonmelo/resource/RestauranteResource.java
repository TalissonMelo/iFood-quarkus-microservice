package com.talissonmelo.resource;

import com.talissonmelo.dto.PratoDto;
import com.talissonmelo.model.Prato;
import com.talissonmelo.model.Restaurante;
import io.smallrye.mutiny.Multi;
import io.vertx.mutiny.pgclient.PgPool;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/restaurantes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RestauranteResource {

    @Inject
    PgPool pgPool;

    @GET
    @Path("{idRestaurante}/pratos")
    public Multi<PratoDto> buscarPratos(@PathParam("idRestaurante") Long idRestaurante){
        return Restaurante.buscarTodos(pgPool, idRestaurante);
    }
}
