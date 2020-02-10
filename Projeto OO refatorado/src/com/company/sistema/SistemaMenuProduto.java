package com.company.sistema;

import com.company.*;

import java.util.ArrayList;
import java.util.Scanner;

public class SistemaMenuProduto {
    Scanner input = new Scanner(System.in);

    Auxiliar aux = new Auxiliar();
    public void editarProduto(ContaLoja lojaAtual, Produto produtoAtual){
        String nomeDoProduto, descricao;
        double altura = 0, comprimento = 0, largura = 0, peso = 0, preco = 0;
        boolean flag, continua;

        do {
            flag = false;

            System.out.println("Digite o nome do produto");
            nomeDoProduto = input.next();
            for (Departamento D : lojaAtual.getDepartamentos()){
                for (Produto P : D.getProdutos()) {
                    if (nomeDoProduto.equals(P.getNomeDoProduto())) {
                        System.out.println("O nome digitado já está em uso");
                        flag = true;
                    }
                }
            }
        } while (flag);

        System.out.println("Faça uma descrição do produto");
        descricao = input.next();

        System.out.println("Digite o preço");
        do {
            try {
                preco = Double.parseDouble(input.next());
                continua = false;
            } catch (NumberFormatException e) {
                System.out.println("Não é permitido inserir letras ou símbolos, informe apenas números");
                input.nextLine();
                continua = true;
            }
        }while(continua);

        System.out.println("Digite as dimensões do produto");
        System.out.println("Altura :");
        do {
            try {
                altura = Double.parseDouble(input.next());
                continua = false;
            } catch (NumberFormatException e) {
                System.out.println("Não é permitido inserir letras ou símbolos, informe apenas números");
                input.nextLine();
                continua = true;
            }
        }while(continua);
        System.out.println("Comprimento :");
        do {
            try {
                comprimento = Double.parseDouble(input.next());
                continua = false;
            } catch (NumberFormatException e) {
                System.out.println("Não é permitido inserir letras ou símbolos, informe apenas números");
                input.nextLine();
                continua = true;
            }
        }while(continua);
        System.out.println("Largura :");
        do {
            try {
                largura = Double.parseDouble(input.next());
                continua = false;
            } catch (NumberFormatException e) {
                System.out.println("Não é permitido inserir letras ou símbolos, informe apenas números");
                input.nextLine();
                continua = true;
            }
        }while(continua);

        System.out.println("Digite o peso do produto");
        do {
            try {
                peso = Double.parseDouble(input.next());
                continua = false;
            } catch (NumberFormatException e) {
                System.out.println("Não é permitido inserir letras ou símbolos, informe apenas números");
                input.nextLine();
                continua = true;
            }
        }while(continua);

        PesosMedidas pesosMedidas = new PesosMedidas();

        pesosMedidas.setAltura(altura);
        pesosMedidas.setComprimento(comprimento);
        pesosMedidas.setLargura(largura);
        pesosMedidas.setPeso(peso);

        produtoAtual.editarProduto(nomeDoProduto, descricao, preco, pesosMedidas);
    }

    public void colocarDesconto(Produto produtoAtual){
        double desconto = 0;
        boolean continua;
        do {
            System.out.println("Digite o desconto desse produto em porcentagem(%)");
            do {
                try {
                    desconto = Double.parseDouble(input.next());
                    continua = false;
                } catch (NumberFormatException e) {
                    System.out.println("Não é permitido inserir letras ou símbolos, informe apenas números");
                    input.nextLine();
                    continua = true;
                }
            }while(continua);
            if(desconto > 100 || desconto < 0){
                System.out.println("Não é possível colocar um desconto maior que 100, nem menor que 0");
            }
        }while(desconto > 100 || desconto < 0);
        produtoAtual.colocarDesconto(desconto);
    }

    public void adicionarAoCarrinho(Produto produtoAtual, int idContaLogin, ArrayList<ContaLogin> Contas){
        for(ContaLogin C : Contas){
            if(idContaLogin == C.getIdContaLogin()){
                C.adicionarAoCarrinho(produtoAtual);
            }
        }
    }

    public void deixarComentário(Produto produtoAtual, int idContaLogin, ArrayList<ContaLogin> Contas){
        String mensagem;
        boolean flag;
        flag = true;
        for(ContaLogin C : Contas){
            if(idContaLogin == C.getIdContaLogin()){
                for(int idP : C.getProdutosComprados()){
                    if(idP == produtoAtual.getIdProduto()){
                        flag = false;
                        System.out.println("Escreva o seu comentário");
                        mensagem = input.nextLine();
                        produtoAtual.deixarComentario(mensagem, C.getDados().getNomeDaConta());
                    }
                }
            }
        }
        if(flag){
            System.out.println("você não comprou esse produto");
        }
    }

}
