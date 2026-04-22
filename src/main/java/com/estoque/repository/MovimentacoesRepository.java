package com.estoque.repository;

import com.estoque.entity.ItemModel;
import com.estoque.entity.MovimentacaoModel;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovimentacoesRepository extends JpaRepository<MovimentacaoModel, Long> {

    @EntityGraph(attributePaths = "item")
    List<MovimentacaoModel> findAll();

    @EntityGraph(attributePaths = "item")
    List<MovimentacaoModel> findAllByItemId(Long itemId);

    List<MovimentacaoModel> findByItemId(Long itemId);

    void deleteByItem(ItemModel itemModel);


}
