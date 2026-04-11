package com.estoque.item;

import jakarta.validation.constraints.Min;


public class ItemUptadeDto {


    private String nome;
    @Min(0)
    private Integer quantidade;
    @Min(0)
    private Double valor;

    public ItemUptadeDto( String nome, Integer quantidade, Double valor) {
        setNome(nome);
        setQuantidade(quantidade);
        setValor(valor);
    }

    public ItemUptadeDto() {
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
