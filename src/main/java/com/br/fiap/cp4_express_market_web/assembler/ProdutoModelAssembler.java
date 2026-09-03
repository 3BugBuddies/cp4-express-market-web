package com.br.fiap.cp4_express_market_web.assembler;

import com.br.fiap.cp4_express_market_web.controller.ProdutoApiController;
import com.br.fiap.cp4_express_market_web.controller.ProdutoViewController;
import com.br.fiap.cp4_express_market_web.dto.ProdutoResponse;
import com.br.fiap.cp4_express_market_web.entity.Produto;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * Converte a entidade em uma representação com links HATEOAS.
 * Os mesmos links alimentam o endpoint JSON (/api/mercado) e os botões das views:
 * a view não monta URL na mão, ela segue o que o servidor devolve.
 *
 * Para o controller REST usamos linkTo(methodOn(...)), como na Parte I. Para o
 * controller MVC isso não funciona: methodOn precisa criar um proxy do tipo de
 * retorno, e os métodos dele devolvem String (nome da view), que é final. Por isso
 * os links das rotas web são montados a partir do Method, sem invocar nada.
 * Nessa forma, linkTo exige um valor por parâmetro do método, na ordem da
 * assinatura; o Model, que não participa da URL, recebe null.
 */
@Component
public class ProdutoModelAssembler implements RepresentationModelAssembler<Produto, EntityModel<ProdutoResponse>> {

    private static final Method LISTAR = rota("listar", Model.class);
    private static final Method FORM_EDITAR = rota("formEditar", Long.class, Model.class);
    private static final Method EXCLUIR = rota("excluir", Long.class);

    @Override
    public EntityModel<ProdutoResponse> toModel(Produto produto) {
        ProdutoResponse response = new ProdutoResponse(
                produto.getId(),
                produto.getNome(),
                produto.getTipo(),
                produto.getSetor(),
                produto.getTamanho(),
                produto.getPreco());

        return EntityModel.of(response,
                linkTo(methodOn(ProdutoApiController.class).findById(produto.getId())).withSelfRel(),
                linkTo(methodOn(ProdutoApiController.class).findAll()).withRel("mercado"),
                linkTo(ProdutoViewController.class, FORM_EDITAR, produto.getId(), null).withRel("editar"),
                linkTo(ProdutoViewController.class, EXCLUIR, produto.getId()).withRel("excluir"));
    }

    @Override
    public CollectionModel<EntityModel<ProdutoResponse>> toCollectionModel(Iterable<? extends Produto> produtos) {
        List<EntityModel<ProdutoResponse>> itens = new ArrayList<>();
        produtos.forEach(produto -> itens.add(toModel(produto)));

        return CollectionModel.of(itens,
                linkTo(methodOn(ProdutoApiController.class).findAll()).withSelfRel(),
                linkTo(ProdutoViewController.class, LISTAR, (Object) null).withRel("market"));
    }

    private static Method rota(String nome, Class<?>... parametros) {
        return Objects.requireNonNull(
                ReflectionUtils.findMethod(ProdutoViewController.class, nome, parametros),
                () -> "Rota não encontrada em ProdutoViewController: " + nome);
    }
}
