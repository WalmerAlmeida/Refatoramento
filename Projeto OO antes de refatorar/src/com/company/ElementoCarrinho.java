package com.company;

import java.util.ArrayList;

public class ElementoCarrinho {
    private Produto produtoAdicionado = new Produto();
    private int quantidade;

    public Produto getProdutoAdicionado() {
        return produtoAdicionado;
    }

    public void setProdutoAdicionado(Produto produtoAdicionado) {
        this.produtoAdicionado = produtoAdicionado;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public void adicionarAoCarrinho(Produto produtoAtual){
        this.produtoAdicionado = produtoAtual;
        this.quantidade = 1;
    }

    public String toString(){
        return "Produto: " + produtoAdicionado
                + "Quantidade: " + quantidade + "\n";
    }
}
