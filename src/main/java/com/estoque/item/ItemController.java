package com.estoque.item;

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

    @PostMapping("/cadastro")
    public ItemDto cadastro(@RequestBody ItemRequestDto cadastro){
        
        return itemService.cadastrarItem(cadastro);
    }

    @DeleteMapping("/deletar/{id}")
    public String deletar(@PathVariable Long id){
        ItemModel item = itemRepository.findById(id).orElseThrow(() -> new RuntimeException("ID não encontrado"));

        itemRepository.delete(item);

        return "Item excluido com sucesso";
    }


    @GetMapping("/listagem")
    public List<ItemModel> listar(){
        return itemService.listar();
    }

    @PutMapping("/alterar")
    public ItemDto alterar(@RequestBody ItemUptadeDto item){
        return itemService.alterar(item);
    }



}
