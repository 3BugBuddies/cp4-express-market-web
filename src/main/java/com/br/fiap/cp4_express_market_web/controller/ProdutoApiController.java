package com.br.fiap.cp4_express_market_web.controller;

import com.br.fiap.cp4_express_market_web.assembler.ProdutoModelAssembler;
import com.br.fiap.cp4_express_market_web.dto.ProdutoResponse;
import com.br.fiap.cp4_express_market_web.service.ProdutoService;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Consulta em JSON no padrão HATEOAS (nível 3 de maturidade de Richardson).
 * A escrita continua pelos formulários da interface web; os links "editar" e
 * "excluir" de cada item apontam para essas rotas.
 */
@RestController
@RequestMapping("/api/mercado")
@RequiredArgsConstructor
public class ProdutoApiController {

    private final ProdutoService produtoService;
    private final ProdutoModelAssembler assembler;

    @GetMapping
    public CollectionModel<EntityModel<ProdutoResponse>> findAll() {
        return assembler.toCollectionModel(produtoService.findAll());
    }

    @GetMapping("/{id}")
    public EntityModel<ProdutoResponse> findById(@PathVariable Long id) {
        return assembler.toModel(produtoService.findById(id));
    }
}
