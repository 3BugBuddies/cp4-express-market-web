package com.br.fiap.cp4_express_market_web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record ProdutoRequest(

        @NotBlank(message = "O nome é obrigatório")
        @Size(min = 3, max = 50, message = "O nome deve ter entre 3 e 50 caracteres")
        String nome,

        @NotBlank(message = "O tipo é obrigatório")
        @Size(min = 3, max = 50, message = "O tipo deve ter entre 3 e 50 caracteres")
        String tipo,

        @NotBlank(message = "O setor é obrigatório")
        @Size(min = 3, max = 50, message = "O setor deve ter entre 3 e 50 caracteres")
        String setor,

        @NotBlank(message = "O tamanho é obrigatório")
        @Size(min = 2, max = 50, message = "O tamanho deve ter entre 2 e 50 caracteres")
        String tamanho,

        @NotNull(message = "O preço é obrigatório")
        @Positive(message = "O preço deve ser um valor positivo")
        Double preco

) {
}
