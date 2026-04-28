package com.estoque.repository;

import com.estoque.entity.ItemModel;
import com.estoque.entity.MovimentacaoModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<ItemModel, Long> {
    List<ItemModel> findByItemId(Long itemId);
}
