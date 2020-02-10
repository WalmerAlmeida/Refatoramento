package com.company;

import java.util.Scanner;

public class AdicionarProduto {
    Scanner input = new Scanner(System.in);
    public void adicionarProduto(ContaLoja lojaAtual, int tipo){
        Departamento departamentoAtual = new Departamento();

        String nomeDoProduto, descricao, nomeDoDepartamento;
        double altura = 0, comprimento = 0, largura = 0, peso = 0, preco = 0;
        boolean flag, continua;

        if (lojaAtual.getDepartamentos().isEmpty()) {
            System.out.println("Não existem departamentos nessa loja");
            return;
        }

        do {
            flag = true;

            System.out.println("Departamentos :");
            for (Departamento D : lojaAtual.getDepartamentos()) {
                System.out.println(D.getNomeDoDepartamento());
            }

            System.out.println("Digite o departamento que o produto pertence");
            nomeDoDepartamento = input.next();

            for (Departamento D : lojaAtual.getDepartamentos()) {
                if (nomeDoDepartamento.equals(D.getNomeDoDepartamento())) {
                    flag = false;
                    departamentoAtual = D;
                }
            }

            if (flag) {
                System.out.println("Departamento não encontrado");
            }

        } while (flag);

        do {
            flag = false;

            System.out.println("Digite o nome do produto");
            nomeDoProduto = input.next();
            for(Departamento D : lojaAtual.getDepartamentos()) {
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
            if(preco <= 0){
                System.out.println("O preco não pode ser igual a 0 ou negativo");
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
            if(altura <= 0){
                System.out.println("A altura não pode ser igual a 0 ou negativa");
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
            if(comprimento <= 0){
                System.out.println("O comprimento não pode ser igual a 0 ou negativo");
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
            if(largura <= 0){
                System.out.println("A largura não pode ser igual a 0 ou negativa");
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
            if(peso <= 0){
                System.out.println("O peso não pode ser igual a 0 ou negativo");
                continua = true;
            }
        }while(continua);

        departamentoAtual.adicionarProduto(nomeDoProduto, descricao, nomeDoDepartamento, preco, altura, comprimento, largura, peso, tipo);
    }
}
