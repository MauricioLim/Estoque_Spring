package com.estoque.controller;

import com.estoque.entity.MovimentacaoModel;
import com.estoque.service.MovimentacoesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movimentacoes")
public class MovimentacoesController {

    @Autowired
    private MovimentacoesService service;

    @GetMapping
    public List<MovimentacaoModel> listarTodos() {
        return service.listarTodos();
    }

    @PostMapping("/{itemId}")
    public MovimentacaoModel criar(
            @PathVariable Long itemId,
            @RequestBody MovimentacaoModel mov) {

        return service.criarMovimentacao(itemId, mov);
    }

    @GetMapping("/item/{itemId}")
    public List<MovimentacaoModel> listarPorItem(@PathVariable Long itemId) {
        return service.listarPorItem(itemId);
    }
}