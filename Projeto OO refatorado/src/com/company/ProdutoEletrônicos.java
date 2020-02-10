package com.company;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ProdutoEletrônicos extends Produto{
    Scanner input = new Scanner(System.in);
    private int consumo;//em kWh

    @Override
    public void adicionarProduto(String nomeDoProduto, String descricao, String departamento, double preco, PesosMedidas pesosMedidas) {
        boolean continua;
        super.adicionarProduto(nomeDoProduto, descricao, departamento, preco, pesosMedidas);
        System.out.println("Digite o consumo do aparelho eletrônico adicionado");
        do {
            try {
                this.consumo = input.nextInt();
                continua = false;
            } catch (InputMismatchException erro1) {
                System.out.println("Não é permitido inserir letras ou símbolos, informe apenas números inteiros");
                input.nextLine();
                continua = true;
            }
        }while(continua);
    }

    public void editarProduto(String nomeDoProduto, String descricao, double preco, PesosMedidas pesosMedidas){
        boolean continua;
        super.editarProduto(nomeDoProduto, descricao, preco, pesosMedidas);
        System.out.println("Digite o consumo do aparelho eletrônico adicionado");
        do {
            try {
                this.consumo = input.nextInt();
                continua = false;
            } catch (InputMismatchException erro1) {
                System.out.println("Não é permitido inserir letras ou símbolos, informe apenas números inteiros");
                input.nextLine();
                continua = true;
            }
        }while(continua);
    }

    public String toString(){
        return  "Id do Produto: " + super.getIdProduto()
                + "\nNome do Produto: " + super.getNomeDoProduto()
                + "\nConsumo: " + this.consumo + "kWh"
                + "\nDescrição: " + super.getDescricao()
                + "\nAltura: " + super.getAltura() + "cm"
                + "\nComprimento: " + super.getComprimento() + "cm"
                + "\nLargura: " + super.getLargura() + "cm"
                + "\nPeso: " + super.getPeso() + "kg"
                + "\nDepartamento: " + super.getDepartamento()
                + "\nPreço: R$" + super.getPreco()
                + "\nValor do Frete: R$" + super.getValorDoFrete()
                + "\nDesconto: " + super.getDesconto() + "%"
                + "\nPreço Final: R$" + super.getPrecoFinal()
                + "\nComentários: " + super.getComentarios() + "\n";
    }
}
