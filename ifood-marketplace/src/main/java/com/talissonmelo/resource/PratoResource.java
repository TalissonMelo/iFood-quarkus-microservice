package com.talissonmelo.resource;

import com.talissonmelo.dto.PratoDto;
import com.talissonmelo.model.Prato;
import io.smallrye.mutiny.Multi;
import io.vertx.mutiny.pgclient.PgPool;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

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
    @APIResponse(responseCode = "200",content = @Content(schema = @Schema(type = SchemaType.ARRAY, implementation = PratoDto.class)))
    public Multi<PratoDto> buscarPratos(){
        return Prato.buscarTodos(pgPool);
    }
}
