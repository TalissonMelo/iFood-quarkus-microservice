package com.talissonmelo.resource;

import com.talissonmelo.entity.Prato;
import com.talissonmelo.entity.Restaurante;

import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
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

    @POST
    @Path("{idRestaurante}/pratos")
    @Transactional
    public Response adicionarPrato(@PathParam("idRestaurante") Long idRestaurante, Prato dto){
        Optional<Restaurante> restaurante = Restaurante.findByIdOptional(idRestaurante);
        if(restaurante.isEmpty()){
            throw  new NotFoundException();
        }
        dto.restaurante = restaurante.get();
        dto.persist();
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("{idRestaurante}/pratos/{idPrato}")
    @Transactional
    public Response editarPrato(@PathParam("idRestaurante") Long idRestaurante, @PathParam("idPrato") Long idPrato , Prato dto){
        Optional<Restaurante> restaurante = Restaurante.findByIdOptional(idRestaurante);
        if(restaurante.isEmpty()){
            throw  new NotFoundException();
        }
        Optional<Prato> prato = Prato.findByIdOptional(idPrato);
        if(prato.isEmpty()){
            throw  new NotFoundException();
        }
        Prato pratoAtualiza = prato.get();
        pratoAtualiza.price = dto.price;
        pratoAtualiza.nome = dto.nome;
        pratoAtualiza.persist();
        return Response.status(Response.Status.CREATED).build();
    }
}
