package com.talissonmelo.kafka;

import com.talissonmelo.dto.PedidoRealizadoDto;
import com.talissonmelo.dto.PratoPedidoDto;
import com.talissonmelo.model.Pedido;
import com.talissonmelo.model.Prato;
import com.talissonmelo.model.Restaurante;
import org.bson.types.Decimal128;
import org.eclipse.microprofile.reactive.messaging.Incoming;

import javax.json.bind.JsonbBuilder;
import java.util.ArrayList;

public class PedidoRealizadoIncoming {

    @Incoming("pedidos")
    public void lerPedidos(PedidoRealizadoDto dto) {
        Pedido pedido = new Pedido();
        pedido.cliente = dto.cliente;
        pedido.pratos = new ArrayList<>();
        dto.pratos.forEach(prato -> pedido.pratos.add(from(prato)));
        Restaurante restaurante = new Restaurante();
        restaurante.nome = dto.restaurante.nome;
        pedido.restaurante = restaurante;
        pedido.persist();

    }

    private Prato from(PratoPedidoDto pratoDto) {
        Prato prato = new Prato();
        prato.descricao = pratoDto.descricao;
        prato.nome = pratoDto.nome;
        prato.preco = new Decimal128(pratoDto.preco);
        return prato;
    }
}
