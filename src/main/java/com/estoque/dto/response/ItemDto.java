package com.estoque.dto.response;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class ItemDto {
    private Long id;
    @NotBlank
    private String nome;
    @Min(0)
    private Integer quantidade;
    @Min(0)
    private Double valor;

    private String imagem;


    public ItemDto(Long id, String nome, int quantidade, double valor, String imagem) {
        setId(id);
        setNome(nome);
        setQuantidade(quantidade);
        setValor(valor);
        setImagem(imagem);
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
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

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
}
