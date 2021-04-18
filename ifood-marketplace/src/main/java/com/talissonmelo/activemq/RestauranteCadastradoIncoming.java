package com.talissonmelo.activemq;

import com.talissonmelo.model.Restaurante;
import org.eclipse.microprofile.reactive.messaging.Incoming;

import javax.enterprise.context.ApplicationScoped;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;

@ApplicationScoped
public class RestauranteCadastradoIncoming {

    @Incoming("restaurantes")
    public void receberRestaurante(String json) {
        Jsonb create = JsonbBuilder.create();
        Restaurante restaurante = create.fromJson(json, Restaurante.class);
        System.out.println(json);
    }
}
