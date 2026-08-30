package com.br.fiap.cp4_express_market_web.controller;

import com.br.fiap.cp4_express_market_web.dto.ProdutoRequest;
import com.br.fiap.cp4_express_market_web.service.ProdutoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 */
@Controller
@RequiredArgsConstructor
public class ProdutoViewController {

    private final ProdutoService produtoService;

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/market")
    public String listar(Model model) {
        model.addAttribute("produtos", produtoService.findAll());
        return "market";
    }

    @GetMapping("/market/novo")
    public String formNovo(Model model) {
        model.addAttribute("produtoRequest", new ProdutoRequest(null, null, null, null, null));
        return "produto-form";
    }

    @PostMapping("/market/novo")
    public String salvar(@Valid @ModelAttribute("produtoRequest") ProdutoRequest request,
                         BindingResult result) {
        if (result.hasErrors()) return "produto-form";
        produtoService.save(request);
        return "redirect:/market";
    }

    @GetMapping("/market/editar/{id}")
    public String formEditar(@PathVariable Long id, Model model) {
        var produto = produtoService.findById(id);
        model.addAttribute("produtoId", produto.getId());
        model.addAttribute("produtoRequest", new ProdutoRequest(
                produto.getNome(), produto.getTipo(), produto.getSetor(),
                produto.getTamanho(), produto.getPreco()
        ));
        return "produto-form";
    }

    @PostMapping("/market/editar/{id}")
    public String atualizar(@PathVariable Long id,
                            @Valid @ModelAttribute("produtoRequest") ProdutoRequest request,
                            BindingResult result) {
        if (result.hasErrors()) return "produto-form";
        produtoService.update(id, request);
        return "redirect:/market";
    }

    @PostMapping("/market/excluir/{id}")
    public String excluir(@PathVariable Long id) {
        produtoService.deleteById(id);
        return "redirect:/market";
    }
}
