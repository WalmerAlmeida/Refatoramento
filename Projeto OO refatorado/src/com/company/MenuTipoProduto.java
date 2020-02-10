package com.company;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuTipoProduto {
    Scanner input = new Scanner(System.in);
    public void MenuTipoProduto(ContaLoja lojaAtual) {
        int opcao = 0;
        boolean continua = true;
        AdicionarProduto adicionarProduto = new AdicionarProduto();

        while (true) {
            System.out.println("Selecione :\n"
                    + "1 : Eletrônicos\n"
                    + "2 : Consumíveis\n"
                    + "3 : Outros\n"
                    + "0 : Fechar\n");
            do {
                try {
                    opcao = input.nextInt();
                    continua = false;
                } catch (InputMismatchException erro1) {
                    System.out.println("Não é permitido inserir letras ou símbolos, informe apenas números inteiros");
                    input.nextLine();//descarta entrada errada do usuário
                    continua = true;
                }
            }while(continua);
            switch (opcao) {
                case 1://Eletrônicos
                    adicionarProduto.adicionarProduto(lojaAtual, opcao);
                    break;
                case 2://Consumíveis
                    adicionarProduto.adicionarProduto(lojaAtual, opcao);
                    break;
                case 3://Outros
                    adicionarProduto.adicionarProduto(lojaAtual, opcao);
                    break;
                case 0://Fechar
                    System.out.println("Fechando...");
                    return;
                default:
                    System.out.println("Digite uma opção válida");
            }
        }
    }
}
