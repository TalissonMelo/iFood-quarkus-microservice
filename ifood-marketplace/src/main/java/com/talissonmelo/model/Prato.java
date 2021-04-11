package com.talissonmelo.model;

import java.math.BigDecimal;

public class Prato {
    public Long id;
    public String nome;
    public String descricao;
    public Restaurante restaurante;
    public BigDecimal price;
}
