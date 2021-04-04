package com.talissonmelo.resource;

import com.talissonmelo.entity.Restaurante;

import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Optional;

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

    @PUT
    @Path("{idRestaurante}")
    @Transactional
    public void atualizar(@PathParam("idRestaurante") Long idRestaurante, Restaurante dto){
        Optional<Restaurante> restauranteAtualizar = Restaurante.findByIdOptional(idRestaurante);
       if (restauranteAtualizar.isEmpty()){
           throw new NotFoundException();
       }
       Restaurante restaurante = restauranteAtualizar.get();
       restaurante.nome = dto.nome;
       restaurante.persist();
    }

    @DELETE
    @Path("{idRestaurante}")
    @Transactional
    public void deletar(@PathParam("idRestaurante") Long idRestaurante) {
        Optional<Restaurante> restaurante = Restaurante.findByIdOptional(idRestaurante);
        if(restaurante.isEmpty()) {
            throw  new NotFoundException();
        }
        Restaurante.deleteById(restaurante.get().id);

    }
}
