package com.estoque.item;

import com.estoque.movimentacoes.MovimentacaoModel;
import com.estoque.movimentacoes.MovimentacoesRepository;
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

    @PostMapping("/cadastro")
    public ItemDto cadastro(@RequestBody ItemRequestDto cadastro){
        
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

    @PutMapping("/{id}")
    public ItemDto alterar(@PathVariable Long id,
                           @RequestBody @Valid ItemUptadeDto item){
        return itemService.alterar(id, item);
    }



}
