package com.estoque.service;

import com.estoque.TipoMovimentacao;
import com.estoque.entity.ItemModel;
import com.estoque.entity.MovimentacaoModel;
import com.estoque.repository.ItemRepository;
import com.estoque.repository.MovimentacoesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

@Service
public class MovimentacoesService {

    @Autowired
    private MovimentacoesRepository movimentacoesRepository;

    @Autowired
    private ItemRepository itemRepository;

    public MovimentacaoModel criarMovimentacao(Long itemId, MovimentacaoModel mov) {

        ItemModel item = itemRepository.findById(itemId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Item não encontrado"));

        if (mov.getTipo() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Tipo de movimentação obrigatório");
        }

        if (mov.getQuantidade() <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Quantidade deve ser maior que zero");
        }

        mov.setTipo(mov.getTipo());
        if (mov.getData() == null) {
            mov.setData(LocalDate.now());
        }

        if (mov.getTipo() == TipoMovimentacao.SAIDA) {
            if (item.getQuantidade() < mov.getQuantidade()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Estoque insuficiente");
            }
            item.setQuantidade(item.getQuantidade() - mov.getQuantidade());
        } else {
            item.setQuantidade(item.getQuantidade() + mov.getQuantidade());
        }

        mov.setItem(item);

        itemRepository.save(item);
        return movimentacoesRepository.save(mov);
    }

    public List<MovimentacaoModel> listarPorItem(Long itemId) {
        return movimentacoesRepository.findByItemId(itemId);
    }

    public List<MovimentacaoModel> listarTodos() {
        return movimentacoesRepository.findAll();
    }
}

