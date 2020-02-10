package com.company.sistema;

import com.company.*;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.ArrayList;

public class SistemaMenuLoja {
    Scanner input = new Scanner(System.in);

    Auxiliar aux = new Auxiliar();

    public void criarLoja(int idContaLogin, ArrayList<ContaLogin> Contas, ArrayList<ContaLoja> Lojas){
        ContaLoja novaLoja = new ContaLoja();

        String nomeDaLoja;
        boolean flag;

        System.out.println("Insira os dados de Criação da sua Loja: ");
        do {
            flag = false;
            System.out.println("Digite o nome da sua loja");
            nomeDaLoja = input.next();
            for(ContaLoja L : Lojas) {
                if (nomeDaLoja.equals(L.getNomeDaLoja())) {
                    System.out.println("Esse nome já está sendo usado");
                    flag = true;
                    break;
                }
            }
        }while(flag);
        for(ContaLogin C : Contas){
            if(idContaLogin == C.getIdContaLogin()){
                novaLoja.criarLoja(nomeDaLoja, C);
            }
        }

        Lojas.add(novaLoja);

        for(ContaLogin C : Contas){
            if(C.getIdContaLogin() == idContaLogin) {
                C.adicionarLoja(novaLoja.getIdContaLoja());
            }
        }

        System.out.println(Lojas.toString());

    }

    public void selecionarLoja(int idContaLogin, ArrayList<ContaLoja> Lojas) {
        boolean flagSelecionar;
        String nomeDaLoja;

        MenuPrincipal novoMenuPrincipal = new MenuPrincipal();

        if (Lojas.isEmpty()) {
            System.out.println("Não existem lojas para serem acessadas");
            return;
        }
        do {
            flagSelecionar = true;
            System.out.println("Lojas :");
            for (ContaLoja L : Lojas) {
                System.out.println(L.getNomeDaLoja());
            }

            System.out.println("digite o nome da loja que deseja entrar");
            nomeDaLoja = input.next();
            for (ContaLoja L : Lojas) {
                if (nomeDaLoja.equals(L.getNomeDaLoja())) {
                    flagSelecionar = false;

                    System.out.println(L.toString());

                    novoMenuPrincipal.MenuPrincipal(L, idContaLogin);
                    break;
                }
            }

            if (flagSelecionar == true) {
                System.out.println("\nO nome da loja selecionada não existe\n");
            }

        } while (flagSelecionar);
    }

    public void editarPerfil(int idContaLogin, ArrayList<ContaLogin> Contas, ArrayList<ContaLoja> Lojas){
        MenuEditarPerfil novoMenuEditarPerfil = new MenuEditarPerfil();
        ContaLogin contaAtual;
        ContaLoja lojaAtual = null;
        contaAtual = aux.acessarConta(idContaLogin, Contas);

        for(ContaLoja L : Lojas){
            if(L.getAdministrador() == idContaLogin){
                lojaAtual = L;
            }
        }

        novoMenuEditarPerfil.MenuEditarPerfil(Contas, lojaAtual, contaAtual);

        System.out.println(Contas.toString());
    }

    public void removerConta(int idContaLogin, ArrayList<ContaLogin> Contas, ArrayList<ContaLoja> Lojas){

        int i = 0;
        ContaLogin contaAtual;
        String resposta;
        boolean flag, flagRemove;

        contaAtual = aux.acessarConta(idContaLogin, Contas);
        do {
            flag = false;
            System.out.println("Você tem certeza que deseja remover sua conta?");
            System.out.println("Todas as lojas criadas por essa conta serão apagadas, digite 'sim' para continuar e 'não' para retornar ao menu");
            resposta = input.next();
            if (resposta.equals("sim")) {
                do {
                    flagRemove =false;
                    for (int LP : contaAtual.getLojasPertencentes()) {
                        for (ContaLoja L : Lojas) {
                            if (LP == L.getIdContaLoja()) {
                                flagRemove = true;
                                i = Lojas.indexOf(L);
                                break;
                            }
                        }
                    }
                    if(flagRemove){
                        Lojas.remove(i);
                    }
                }while(flagRemove);
                for (ContaLogin C : Contas) {
                    if (C.getIdContaLogin() == idContaLogin) {
                        i = Contas.indexOf(C);
                    }
                }
                Contas.remove(i);
            } else if (resposta.equals("não")) {
                return;
            } else {
                flag = true;
                System.out.println("Resposta inválida");
            }
        }while(flag);
    }
}
