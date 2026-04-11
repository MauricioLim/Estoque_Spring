package com.estoque.item;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class ItemUptadeDto {
    private Long id;

    private String nome;
    @Min(0)
    private Integer quantidade;
    @Min(0)
    private Double valor;

    public ItemUptadeDto(Long id, String nome, Integer quantidade, Double valor) {
        setId(id);
        setNome(nome);
        setQuantidade(quantidade);
        setValor(valor);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }
}
