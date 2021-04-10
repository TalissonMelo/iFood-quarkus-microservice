package com.talissonmelo.resource;

import com.talissonmelo.entity.Prato;
import com.talissonmelo.entity.Restaurante;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Optional;

@Path("/restaurantes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RestaurantePratoResource {

    @GET
    @Path("{idRestaurante}/pratos")
    public List<Restaurante> buscarPratos(@PathParam("idRestaurante") Long idRestaurante){
        Optional<Restaurante> restaurante = Restaurante.findByIdOptional(idRestaurante);
        if(restaurante.isEmpty()){
            throw  new NotFoundException();
        }
        return Prato.list("restaurante", restaurante.get());
    }
}
