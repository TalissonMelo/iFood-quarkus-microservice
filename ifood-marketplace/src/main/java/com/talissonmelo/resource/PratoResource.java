package com.talissonmelo.resource;

import com.talissonmelo.dto.PratoDto;
import com.talissonmelo.model.Prato;
import io.smallrye.mutiny.Multi;
import io.vertx.mutiny.pgclient.PgPool;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/pratos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PratoResource {

    @Inject
    PgPool pgPool;

    @GET
    public Multi<PratoDto> buscarPratos(){
        return Prato.buscarTodos(pgPool);
    }
}
