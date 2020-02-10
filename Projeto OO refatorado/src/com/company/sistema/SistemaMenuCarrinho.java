package com.company.sistema;

import com.company.*;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.ArrayList;

public class SistemaMenuCarrinho {
    Scanner input = new Scanner(System.in);

    Auxiliar aux = new Auxiliar();
    public void aumentarQuantidade(Carrinho carrinhoAtual){
        int quantidade = 0, indice = 0;
        double precoTotal = 0;
        String nomeDoProduto;
        boolean flag, continua;

        do {
            flag = true;
            System.out.println("Digite o nome do produto que deseja mudar a quantidade");
            nomeDoProduto = input.next();
            for (ElementoCarrinho EC : carrinhoAtual.getElementos()) {
                if (nomeDoProduto.equals(EC.getProdutoAdicionado().getNomeDoProduto())) {
                    flag = false;
                    while (true) {
                        System.out.println("Digite a quantidade do produto selecionado");
                        do {
                            try {
                                quantidade = input.nextInt();
                                continua = false;
                            } catch (InputMismatchException erro1) {
                                System.out.println("Não é permitido inserir letras, informe apenas números inteiros");
                                input.nextLine();
                                continua = true;
                            }
                        }while(continua);
                        if (quantidade > 0) {
                            EC.setQuantidade(quantidade);
                            break;
                        } else if (quantidade == 0) {
                            indice = carrinhoAtual.getElementos().indexOf(EC);
                            break;
                        }else{
                            System.out.println("O valor da quantidade não pode ser negativo");
                        }
                    }
                }
            }
            if(flag){
                System.out.println("Item não encontrado");
            }
        }while(flag);
        if(quantidade == 0){
            carrinhoAtual.getElementos().remove(indice);
        }
        precoTotal = 0;
        for(ElementoCarrinho EC : carrinhoAtual.getElementos()){
            precoTotal = (EC.getProdutoAdicionado().getPrecoFinal()*EC.getQuantidade()) + precoTotal;
        }
        carrinhoAtual.setPrecoTotal(precoTotal);
    }

    public void finalizarCompra(Carrinho carrinhoAtual, int idContaLogin, ArrayList<ContaLogin> Contas){
        Cartão novocartao = new Cartão();
        String nomeDoTitular, CVV = "", numeroDoCartão = "", dataDeVencimento = "";
        boolean flag, continua;
        int data = 0, dia, mês, ano;

        ContaLogin contaAtual;
        contaAtual = aux.acessarConta(idContaLogin, Contas);
        System.out.println("Insira os dados do cartão: ");
        do{
            System.out.println("Digite o nome do titular do cartão");
            nomeDoTitular = input.nextLine();
            flag = false;
            for (int i = 0; i < nomeDoTitular.length(); i++) {
                Character caractere = nomeDoTitular.charAt(i);
                if (Character.isDigit(caractere)) {
                    flag = true;
                    break;
                }
            }
            if(flag){
                System.out.println("Não é permitido inserir números, informe apenas letras");
            }
        }while(flag);
        System.out.println("Digite o número do cartão");
        do {
            try {
                numeroDoCartão = input.next();
                Long.parseLong(numeroDoCartão);
                continua = false;
            } catch (NumberFormatException erro1) {
                System.out.println("Não é permitido inserir letras ou símbolos, informe apenas números inteiros");
                input.nextLine();
                continua = true;
            }
            if(numeroDoCartão.length() != 16){
                continua = true;
                System.out.println("O numero do cartão inserido é inválido");
            }
        }while(continua);
        System.out.println("Digite o CVV do cartão");
        do {
            try {
                CVV = input.next();
                Integer.parseInt(CVV);
                continua = false;
            } catch (NumberFormatException erro1) {
                System.out.println("Não é permitido inserir letras ou símbolos, informe apenas números inteiros");
                input.nextLine();
                continua = true;
            }
            if(CVV.length() != 3){
                continua = true;
                System.out.println("O numero do cartão inserido é inválido");
            }
        }while(continua);
        System.out.println("Digite a data de vencimento no fomato DDMMAAAA");
        do {
            try {
                dataDeVencimento = input.next();
                data = Integer.parseInt(dataDeVencimento);
                continua = false;
            } catch (NumberFormatException erro1) {
                System.out.println("Não é permitido inserir letras ou símbolos, informe apenas números inteiros");
                input.nextLine();
                continua = true;
            }
            if(dataDeVencimento.length() != 8){
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
        novocartao.inserirDados(nomeDoTitular, dataDeVencimento, CVV, numeroDoCartão);//o criação da classe Cartão tem o objetivo de simular a plataforma que verificaria os dados do cartão e repassaria o pagamento, como
        //não possuo as informações para verificar os dados do cartão, considero que para qualquer dado inserido, está correto.
        System.out.println(novocartao.toString());
        if(novocartao.isStatusPago() == true){
            for (ElementoCarrinho EC : carrinhoAtual.getElementos()) {
                contaAtual.inserirProdutosComprados(EC.getProdutoAdicionado().getIdProduto());
            }
            carrinhoAtual.getElementos().clear();
            System.out.println("Compra concluída com sucesso");
        }else{
            System.out.println("Dados inválidos");
        }
    }
}
