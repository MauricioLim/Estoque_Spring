package com.estoque.item;

import com.estoque.dto.response.ItemDto;
import com.estoque.dto.request.ItemRequestDto;
import com.estoque.dto.ItemUptadeDto;
import com.estoque.entity.ItemModel;
import com.estoque.entity.MovimentacaoModel;
import com.estoque.repository.ItemRepository;
import com.estoque.repository.MovimentacoesRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ItemService {
    @Autowired
    private MovimentacoesRepository movimentacoesRepository;

    @Autowired
    private ItemRepository itemRepository;

    public ItemDto cadastrarItem(ItemRequestDto item){
        ItemModel novoItem = new ItemModel();
        novoItem.setNome(item.getNome());
        novoItem.setQuantidade(item.getQuantidade());
        novoItem.setValor(item.getValor());

        novoItem = itemRepository.save(novoItem);

        if (novoItem.getQuantidade() > 0) {
            MovimentacaoModel mov = new MovimentacaoModel();
            mov.setItem(novoItem);
            mov.setQuantidade(novoItem.getQuantidade());
            mov.setTipo("ENTRADA");
            mov.setData(LocalDate.now());

            movimentacoesRepository.save(mov);
        }

        return new ItemDto(novoItem.getId(),novoItem.getNome(), novoItem.getQuantidade(), novoItem.getValor());
    }

    public List<ItemModel> listar(){
        return itemRepository.findAll();
    }

    @Transactional
    public void deletar(Long id){
        ItemModel item = itemRepository.findById(id).orElseThrow(() -> new RuntimeException("item não encontrado"));

        movimentacoesRepository.deleteByItem(item);
        itemRepository.delete(item);
    }

    public ItemDto alterar(Long id, ItemUptadeDto item){

        ItemModel itemAlterado = itemRepository.findById(id).orElseThrow(() -> new RuntimeException("item não encontrado"));
        int quantidadeAntiga = itemAlterado.getQuantidade();
        int quantidadeNova = item.getQuantidade() != null ? item.getQuantidade(): quantidadeAntiga;
        int diferenca = quantidadeNova - quantidadeAntiga;

        if (item.getNome() != null) {
            itemAlterado.setNome(item.getNome());
        }

        itemAlterado.setQuantidade(quantidadeNova);

        if (item.getValor() != null) {
            itemAlterado.setValor(item.getValor());
        }

        itemRepository.save(itemAlterado);

        if (diferenca != 0) {
            MovimentacaoModel mov = new MovimentacaoModel();
            mov.setItem(itemAlterado);
            mov.setQuantidade(Math.abs(diferenca));
            mov.setTipo(diferenca > 0 ? "ENTRADA" : "SAIDA");
            mov.setData(LocalDate.now());

            movimentacoesRepository.save(mov);
        }

        return new ItemDto(
                itemAlterado.getId(), itemAlterado.getNome(), itemAlterado.getQuantidade(), itemAlterado.getValor()
        );
    }


}
