package com.talissonmelo.resource;

import com.talissonmelo.entity.Restaurante;

import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/restaurantes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RestauranteResource {

    @GET
    public List<Restaurante> findAll(){
        return Restaurante.listAll();
    }

    @POST
    @Transactional
    public void adicionar(Restaurante dto){
        dto.persist();
    }
}
