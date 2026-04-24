package com.estoque.dto;

import jakarta.validation.constraints.Min;
import org.springframework.web.multipart.MultipartFile;


public class ItemUptadeDto {


    private String nome;
    @Min(0)
    private Integer quantidade;
    @Min(0)
    private Double valor;

    private MultipartFile imagem;

    public ItemUptadeDto( String nome, Integer quantidade, Double valor, MultipartFile imagem) {
        setNome(nome);
        setQuantidade(quantidade);
        setValor(valor);
        setImagem(imagem);
    }

    public ItemUptadeDto() {
    }

    public MultipartFile getImagem() {
        return imagem;
    }

    public void setImagem(MultipartFile imagem) {
        this.imagem = imagem;
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
