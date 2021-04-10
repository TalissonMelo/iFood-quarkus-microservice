package com.talissonmelo.resource;

import com.talissonmelo.dto.AdicionarRestauranteDto;
import com.talissonmelo.entity.Localizacao;
import com.talissonmelo.entity.Restaurante;

import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
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

    @GET
    @Path("{idRestaurante}")
    public Restaurante buscarRestauranteId(@PathParam("idRestaurante") Long idRestuarante){
        Optional<Restaurante> restaurante = Restaurante.findByIdOptional(idRestuarante);
        if (restaurante.isEmpty()){
            throw new NotFoundException();
        }
        return restaurante.get();
    }

    @POST
    @Transactional
    public Response adicionar(@Valid AdicionarRestauranteDto dto){
        Localizacao localizacao = new Localizacao();
        localizacao.latitude = dto.localizacao.latitude;
        localizacao.longitude = dto.localizacao.longitude;
        Restaurante restaurante = new Restaurante();
        restaurante.proprietario = dto.proprietario;
        restaurante.cnpj = dto.cnpj;
        restaurante.nome = dto.nome;
        restaurante.localizacao = localizacao;
        restaurante.persist();
        return Response.status(Response.Status.CREATED).build();
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
