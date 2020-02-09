package com.company;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuPrincipal {
    Scanner input = new Scanner(System.in);
    public void MenuPrincipal(ContaLoja L, int idContaLogin){
        int opcao = 0;
        boolean continua = true;
        Sistema novoSistema = new Sistema();
        int idContaLoja = L.getIdContaLoja();

        if(L.getAdministrador() == idContaLogin){//menu do administrador
            while(true) {
                System.out.println("Selecione :\n"
                        + "1 : Buscar Produto\n"
                        + "2 : Listar Produtos\n"
                        + "3 : Adicionar Produto\n"
                        + "4 : Remover Produto\n"
                        + "5 : Adicionar Departamento\n"
                        + "6 : Remover Departamento\n"
                        + "7 : Remover Loja\n"
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
                    case 1://Buscar Produto
                        novoSistema.buscarProduto(idContaLoja, idContaLogin);
                        break;
                    case 2://Listar Produtos
                        novoSistema.listarProdutos(idContaLoja, idContaLogin);
                        break;
                    case 3://Adicionar Produto
                        novoSistema.adicionarProduto(idContaLoja);
                        break;
                    case 4://Remover Produto
                        novoSistema.removerProduto(idContaLoja);
                        break;
                    case 5://Adicionar Departamento
                        novoSistema.adicionarDepartamento(idContaLoja);
                        break;
                    case 6://Remover Departamento
                        novoSistema.removerDepartamento(idContaLoja);
                        break;
                    case 7://Remover Loja
                        novoSistema.removerLoja(idContaLoja);
                        return;
                    case 0://Sair
                        System.out.println("Saindo...");
                        return;
                    default:
                        System.out.println("Digite uma opção válida");
                }
            }
        }else{//menu do usuário comum
            while (true) {
                System.out.println("Selecione :\n"
                        + "1 : Buscar Produto\n"
                        + "2 : Listar Produtos\n"
                        + "3 : Exibir Produtos Comprados\n"
                        + "4 : Acessar Carrinho\n"
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
                    case 1://Buscar Produto
                        novoSistema.buscarProduto(idContaLoja, idContaLogin);
                        break;
                    case 2://Listar Produtos
                        novoSistema.listarProdutos(idContaLoja, idContaLogin);
                        break;
                    case 3://Exibir Produtos Comprados
                        novoSistema.exibirProdutosComprados(idContaLoja, idContaLogin);
                        break;
                    case 4://Acessar Carrinho
                        novoSistema.acessarCarrinho(idContaLogin);
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
