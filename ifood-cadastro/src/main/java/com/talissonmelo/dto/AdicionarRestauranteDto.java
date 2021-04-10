package com.talissonmelo.dto;

import org.hibernate.validator.constraints.br.CNPJ;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class AdicionarRestauranteDto {

    @NotBlank
    public String proprietario;

//    @CNPJ
    public String cnpj;

    @NotEmpty
    @Size(min = 5, max = 50)
    public String nome;

    public LocalizacaoDto localizacao;

}
