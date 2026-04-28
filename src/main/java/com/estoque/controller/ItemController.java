package com.estoque.controller;

import com.estoque.dto.response.ItemDto;
import com.estoque.dto.request.ItemRequestDto;
import com.estoque.dto.ItemUptadeDto;
import com.estoque.entity.ItemModel;
import com.estoque.service.ItemService;
import com.estoque.repository.ItemRepository;
import com.estoque.repository.MovimentacoesRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/itens")
public class ItemController {

    @Autowired
    ItemRepository itemRepository;
    @Autowired
    private ItemService itemService;

    @Autowired
    MovimentacoesRepository movimentacoesRepository;

    @PostMapping(value = "/cadastro", consumes = "multipart/form-data")
    public ItemDto cadastro( @ModelAttribute @Valid ItemRequestDto cadastro){
        
        return itemService.cadastrarItem(cadastro);
    }

    @DeleteMapping("/deletar/{id}")
    public String deletar(@PathVariable Long id){
        itemService.deletar(id);

        return "Item excluido com sucesso";
    }


    @GetMapping("/listagem")
    public List<ItemModel> listar(){
        return itemService.listar();
    }

    @PutMapping(value = "/{id}", consumes = "multipart/form-data")
    public ItemDto alterar(@PathVariable Long id,
                           @ModelAttribute  @Valid ItemUptadeDto item){
        return itemService.alterar(id, item);
    }

    @GetMapping("/buscaid")
    public List<ItemModel> buscaId(Long id){
        return itemService.buscaId(id);
    }




}
