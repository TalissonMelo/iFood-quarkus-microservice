package com.talissonmelo.resource;

import com.talissonmelo.dto.PedidoRealizadoDto;
import com.talissonmelo.dto.PratoDto;
import com.talissonmelo.dto.PratoPedidoDto;
import com.talissonmelo.dto.RestauranteDto;
import com.talissonmelo.model.Prato;
import com.talissonmelo.model.PratoCarrinho;
import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.pgclient.PgPool;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.stream.Collectors;

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

    @POST
    @Path("/realizar-pedido")
    public Uni<Boolean> finalizar() {
        PedidoRealizadoDto pedido = new PedidoRealizadoDto();
        String cliente = CLIENTE;
        pedido.cliente = cliente;
        List<PratoCarrinho> pratoCarrinho = PratoCarrinho.buscarCarrinho(pgPool, cliente).await().indefinitely();
        List<PratoPedidoDto> pratos = pratoCarrinho.stream().map(pc -> from(pc)).collect(Collectors.toList());
        pedido.pratos = pratos;

        RestauranteDto restaurante = new RestauranteDto();
        restaurante.nome = "nome restaurante";
        pedido.restaurante = restaurante;
        return PratoCarrinho.deletar(pgPool, cliente);
    }

    private PratoPedidoDto from(PratoCarrinho pratoCarrinho) {
        PratoDto dto = Prato.buscarPratoId(pgPool, pratoCarrinho.prato).await().indefinitely();
        return new PratoPedidoDto(dto.nome, dto.descricao, dto.preco);
    }

}
