package com.br.fiap.cp4_express_market_web.service;

import com.br.fiap.cp4_express_market_web.dto.ProdutoRequest;
import com.br.fiap.cp4_express_market_web.entity.Produto;
import com.br.fiap.cp4_express_market_web.exception.NotFoundException;
import com.br.fiap.cp4_express_market_web.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public Produto save(ProdutoRequest request) {
        Produto produto = Produto.builder()
                .nome(request.nome())
                .tipo(request.tipo())
                .setor(request.setor())
                .tamanho(request.tamanho())
                .preco(request.preco())
                .build();

        return produtoRepository.save(produto);
    }

    public List<Produto> findAll() {
        return produtoRepository.findAll();
    }

    public Produto findById(Long id) {
        return produtoRepository.findById(id)
                .orElseThrow(() -> NotFoundException.of("Produto", id));
    }

    public Produto update(Long id, ProdutoRequest request) {
        Produto produtoExistente = findById(id);
        produtoExistente.setNome(request.nome());
        produtoExistente.setTipo(request.tipo());
        produtoExistente.setSetor(request.setor());
        produtoExistente.setTamanho(request.tamanho());
        produtoExistente.setPreco(request.preco());
        return produtoRepository.save(produtoExistente);
    }

    public void deleteById(Long id) {
        produtoRepository.delete(findById(id));
    }
}
