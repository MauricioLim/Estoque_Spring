package com.estoque.movimentacoes;

import com.estoque.item.ItemModel;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovimentacoesRepository extends JpaRepository<MovimentacaoModel, Long> {

    @EntityGraph(attributePaths = "item")
    List<MovimentacaoModel> findAll();

    @EntityGraph(attributePaths = "item")
    List<MovimentacaoModel> findByItemId(Long itemId);
}
