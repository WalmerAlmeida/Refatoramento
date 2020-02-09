package com.company;

import java.util.ArrayList;

public class Carrinho {
    private ArrayList<ElementoCarrinho> Elementos = new ArrayList<ElementoCarrinho>();
    private double precoTotal;

    public ArrayList<ElementoCarrinho> getElementos() {
        return Elementos;
    }

    public void setElementos(ArrayList<ElementoCarrinho> elementos) {
        Elementos = elementos;
    }

    public double getPrecoTotal() {
        return precoTotal;
    }

    public void setPrecoTotal(double precoTotal) {
        this.precoTotal = precoTotal;
    }

    public void adicionarAoCarrinho(Produto produtoAtual){
        ElementoCarrinho novoElemento = new ElementoCarrinho();
        novoElemento.adicionarAoCarrinho(produtoAtual);
        for(ElementoCarrinho EC : Elementos){
            if(produtoAtual.getIdProduto() == EC.getProdutoAdicionado().getIdProduto()){
                System.out.println("Esse produto já está no seu carrinho de compras");
                return;
            }
        }
        Elementos.add(novoElemento);
    }

    public String toString(){//arrumar o toString
        String s = "";
        for(ElementoCarrinho EC : Elementos){
            s = (s + EC.toString());
        }
        s = (s + "Preço Total: R$" + precoTotal + "\n");
        return s;
    }
}
