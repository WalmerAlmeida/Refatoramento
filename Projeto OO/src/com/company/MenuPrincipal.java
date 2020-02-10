package com.company;

import com.company.sistema.SistemaMenuPrincipal;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuPrincipal {
    Scanner input = new Scanner(System.in);
    public void MenuPrincipal(ContaLoja L, int idContaLogin){
        int opcao = 0;
        boolean continua = true;
        SistemaMenuPrincipal novoSistemaMenuPrincipal = new SistemaMenuPrincipal();
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
                        novoSistemaMenuPrincipal.buscarProduto(idContaLoja, idContaLogin, DadosLojaseContas.Lojas);
                        break;
                    case 2://Listar Produtos
                        novoSistemaMenuPrincipal.listarProdutos(idContaLoja, idContaLogin, DadosLojaseContas.Lojas);
                        break;
                    case 3://Adicionar Produto
                        novoSistemaMenuPrincipal.adicionarProduto(idContaLoja, DadosLojaseContas.Lojas);
                        break;
                    case 4://Remover Produto
                        novoSistemaMenuPrincipal.removerProduto(idContaLoja, DadosLojaseContas.Lojas);
                        break;
                    case 5://Adicionar Departamento
                        novoSistemaMenuPrincipal.adicionarDepartamento(idContaLoja, DadosLojaseContas.Lojas);
                        break;
                    case 6://Remover Departamento
                        novoSistemaMenuPrincipal.removerDepartamento(idContaLoja, DadosLojaseContas.Lojas);
                        break;
                    case 7://Remover Loja
                        novoSistemaMenuPrincipal.removerLoja(idContaLoja, DadosLojaseContas.Contas, DadosLojaseContas.Lojas);
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
                        novoSistemaMenuPrincipal.buscarProduto(idContaLoja, idContaLogin, DadosLojaseContas.Lojas);
                        break;
                    case 2://Listar Produtos
                        novoSistemaMenuPrincipal.listarProdutos(idContaLoja, idContaLogin, DadosLojaseContas.Lojas);
                        break;
                    case 3://Exibir Produtos Comprados
                        novoSistemaMenuPrincipal.exibirProdutosComprados(idContaLoja, idContaLogin, DadosLojaseContas.Contas, DadosLojaseContas.Lojas);
                        break;
                    case 4://Acessar Carrinho
                        novoSistemaMenuPrincipal.acessarCarrinho(idContaLogin, DadosLojaseContas.Contas);
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
