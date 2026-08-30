package com.br.fiap.cp4_express_market_web.repository;


import com.br.fiap.cp4_express_market_web.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
