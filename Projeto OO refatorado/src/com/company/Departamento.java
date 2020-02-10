package com.company;

import java.util.ArrayList;

public class Departamento {
    String nomeDoDepartamento;
    private ArrayList<Produto> Produtos = new ArrayList<Produto>();

    public String getNomeDoDepartamento() {
        return nomeDoDepartamento;
    }

    public void setNomeDoDepartamento(String nomeDoDepartamento) {
        this.nomeDoDepartamento = nomeDoDepartamento;
    }

    public ArrayList<Produto> getProdutos() {
        return Produtos;
    }

    public void setProdutos(ArrayList<Produto> produtos) {
        Produtos = produtos;
    }

    public void adicionarProduto(String nomeDoProduto, String descricao, String nomeDoDepartamento, double preco, PesosMedidas pesosMedidas, int tipo){
        Produto novoProduto = null;
        if(tipo == 1){
            novoProduto = new ProdutoEletrônicos();
        }else if(tipo == 2){
            novoProduto = new ProdutoConsumíveis();
        }else if(tipo == 3){
            novoProduto = new Produto();
        }
        novoProduto.adicionarProduto(nomeDoProduto, descricao, nomeDoDepartamento, preco, pesosMedidas);
        Produtos.add(novoProduto);
    }

    public void removerProduto(String nomeDoProduto){
        for(Produto P : Produtos){
            if(nomeDoProduto.equals(P.getNomeDoProduto())){
                Produtos.remove(P);

                System.out.println("Produto Apagado: \n" + P.toString());

                break;
            }
        }

        for(Produto P : Produtos){
            System.out.println("Demais Produtos do Departamentos: \n" + P.toString());
        }

    }

    public void adicionarDepartamento(String nomeDoDepartamento){
        this.nomeDoDepartamento = nomeDoDepartamento;
    }

    public String toString(){
        return  "Nome do Departamento: " + nomeDoDepartamento
                + "\nProdutos: " + Produtos + "\n";
    }
}
