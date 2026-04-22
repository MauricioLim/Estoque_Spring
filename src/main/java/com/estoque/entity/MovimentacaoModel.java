package com.estoque.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table
public class MovimentacaoModel {

    @Id
    @GeneratedValue
    private Long id;

    private int quantidade;

    private String tipo;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private ItemModel item;

    private LocalDate data;

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public ItemModel getItem() {
        return item;
    }

    public void setItem(ItemModel item) {
        this.item = item;
    }
}
