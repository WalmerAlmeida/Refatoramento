package com.company;

import java.util.Scanner;

public class ProdutoConsumíveis extends Produto{
    Scanner input = new Scanner(System.in);
    private String dataDeVencimento;//DDMMAAAA

    @Override
    public void adicionarProduto(String nomeDoProduto, String descricao, String departamento, double preco, PesosMedidas pesosMedidas) {
        boolean continua;
        int dia, mês, ano, data = 0;
        super.adicionarProduto(nomeDoProduto, descricao, departamento, preco, pesosMedidas);
        System.out.println("Digite a data de vencimento");
        do {
            try {
                this.dataDeVencimento = input.next();
                data = Integer.parseInt(dataDeVencimento);
                continua = false;
            } catch (NumberFormatException erro1) {
                System.out.println("Não é permitido inserir letras ou símbolos, informe apenas números inteiros");
                input.nextLine();
                continua = true;
            }
            if(this.dataDeVencimento.length() != 8){
                continua = true;
                System.out.println("O numero do cartão inserido é inválido");
            }
            dia = data/1000000;
            mês = (data/10000)-(dia*100);
            ano = (data - mês*10000) - dia*1000000;
            if(dia > 31 || dia < 1){
                System.out.println("Data inválida");
                continua = true;
            }else if(mês > 12 || mês < 1){
                System.out.println("Data inválida");
                continua = true;
            }else if(ano < 2019){
                System.out.println("Data inválida");
                continua = true;
            }
        }while(continua);
    }

    public void editarProduto(String nomeDoProduto, String descricao, double preco, PesosMedidas pesosMedidas){
        boolean continua;
        int dia, mês, ano, data = 0;
        super.editarProduto(nomeDoProduto, descricao, preco, pesosMedidas);
        System.out.println("Digite a data de vencimento");
        do {
            try {
                this.dataDeVencimento = input.next();
                data = Integer.parseInt(dataDeVencimento);
                continua = false;
            } catch (NumberFormatException erro1) {
                System.out.println("Não é permitido inserir letras ou símbolos, informe apenas números inteiros");
                input.nextLine();
                continua = true;
            }
            if(this.dataDeVencimento.length() != 8){
                continua = true;
                System.out.println("O numero do cartão inserido é inválido");
            }
            dia = data/1000000;
            mês = (data/10000)-(dia*100);
            ano = (data - mês*10000) - dia*1000000;
            if(dia > 31 || dia < 1){
                System.out.println("Data inválida");
                continua = true;
            }else if(mês > 12 || mês < 1){
                System.out.println("Data inválida");
                continua = true;
            }else if(ano < 2019){
                System.out.println("Data inválida");
                continua = true;
            }
        }while(continua);
    }

    public String toString(){
        return  "Id do Produto: " + super.getIdProduto()
                + "\nNome do Produto: " + super.getNomeDoProduto()
                + "\nData de Vencimento: " + this.dataDeVencimento
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
