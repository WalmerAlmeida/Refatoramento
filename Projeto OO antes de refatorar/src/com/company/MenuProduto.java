package com.company;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuProduto {
    Scanner input = new Scanner(System.in);
    Sistema novoSistema = new Sistema();
    public void MenuProduto(ContaLoja lojaAtual, Produto produtoAtual, int idContaLogin){
        int opcao = 0;
        boolean continua = true;
        if(lojaAtual.getAdministrador() == idContaLogin){//ver se aqui removo o produto
            while(true) {
                System.out.println(produtoAtual.toString());
                System.out.println("Selecione :\n"
                        + "1 : Editar Produto\n"
                        + "2 : Colocar Desconto\n"
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
                    case 1://Editar Produto
                        novoSistema.editarProduto(lojaAtual, produtoAtual);
                        break;
                    case 2://Colocar Desconto
                        novoSistema.colocarDesconto(produtoAtual);
                        break;
                    case 0://Sair
                        System.out.println("Saindo...");
                        return;
                    default:
                        System.out.println("Digite uma opção válida");
                }
            }
        }else{
            while(true) {
                System.out.println(produtoAtual.toString());
                System.out.println("Selecione :\n"
                        + "1 : Adicionar ao Carrinho\n"
                        + "2 : Deixar Comentário\n"
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
                    case 1://Adicionar ao Carrinho
                        novoSistema.adicionarAoCarrinho(produtoAtual, idContaLogin);
                        return;//irá voltar ao Menu em que poderá acessar o carrinho ou buscar por outro item
                    case 2://Deixar Comentário//permitir se o status estiver como pago
                        novoSistema.deixarComentário(produtoAtual, idContaLogin);
                        break;
                    case 0://Sair
                        System.out.println("Saindo...");
                        return;
                    default:
                        System.out.println("Digite uma opção válida");
                }
            }
        }
    }
}
