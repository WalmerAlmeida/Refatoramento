package com.company;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuCarrinho {
    Scanner input = new Scanner(System.in);

    public void MenuCarrinho(Carrinho carrinhoAtual, int idContaLogin){
        int opcao = 0;
        boolean continua = true;
        Sistema novoSistema = new Sistema();
        System.out.println(carrinhoAtual.toString());
        System.out.println("Selecione :\n"
                + "1 : Mudar a Quantidade de Algum Item\n"
                + "2 : Finalizar Compra\n"
                + "0 : Sair\n");
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
            case 1://Mudar a Quantidade de Algum Item
                novoSistema.aumentarQuantidade(carrinhoAtual);
                break;
            case 2://Finalizar Compra//lembrar de remover todos os itens do carrinho e inserir os itens nos produtos pagos
                novoSistema.finalizarCompra(carrinhoAtual, idContaLogin);
                break;
            case 0://sair
                System.out.println("Saindo...");
                return;
            default:
                System.out.println("Digite uma opção válida");
        }
    }
}
