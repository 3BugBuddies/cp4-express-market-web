package com.br.fiap.cp4_express_market_web.dto;

public record ProdutoResponse(
        Long id,
        String nome,
        String tipo,
        String setor,
        String tamanho,
        Double preco
) {
}
