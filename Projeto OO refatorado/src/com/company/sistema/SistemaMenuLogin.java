package com.company.sistema;

import com.company.ContaLogin;
import com.company.ContaLoja;
import com.company.MenuLoja;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.ArrayList;

public class SistemaMenuLogin {
    Scanner input = new Scanner(System.in);

    public void fazerLogin(ArrayList<ContaLogin> Contas){
        String email, senha;
        int opcao = 0;
        boolean flagLogin, continua, flagOpcao = false;

        MenuLoja novoMenu = new MenuLoja();

        if(Contas.isEmpty()){
            System.out.println("Não existem contas registradas, crie a sua conta antes de fazer o login");
            return;
        }

        do {
            flagLogin= false;
            System.out.println("Insira os dados de Login: ");
            System.out.println("Digite seu email");
            email = input.next();
            System.out.println("Digite sua senha");
            senha = input.next();
            for(ContaLogin C : Contas) {
                if (email.equals(C.getDados().getEmail()) && senha.equals(C.getSenha())) {
                    flagLogin = true;

                    novoMenu.MenuLoja(C.getIdContaLogin());

                    break;
                }
            }
            if(!flagLogin){
                System.out.println("O email ou senha está incorreto, digite novamente");
                do{
                    System.out.println("Deseja retornar ao menu?, digite 0 para sair, senão digite 1");
                    do {
                        try {
                            opcao = input.nextInt();
                            continua = false;
                        } catch (InputMismatchException erro1) {
                            System.out.println("Não é permitido inserir letras ou símbolos, informe apenas números inteiros");
                            input.nextLine();
                            continua = true;
                        }
                    }while(continua);

                    if (opcao == 0) {
                        return;
                    } else if (opcao == 1) {
                        flagOpcao = false;
                    } else if (opcao != 1 && opcao != 0) {
                        flagOpcao = true;
                        System.out.println("Opção inválida");
                    }
                }while(flagOpcao);
            }
        }while(!flagLogin);
    }

    public void criarConta(ArrayList<ContaLogin> Contas) {
        ContaLogin novaConta = new ContaLogin();

        String nomeDaConta, email, endereco, senha;
        String CPF = "";
        boolean flag, continua;

        System.out.println("Insira os dados de Criação da sua Conta: ");
        do {
            flag = false;
            System.out.println("Digite seu email");
            email = input.next();
            for (ContaLogin C : Contas) {
                if (email.equals(C.getDados().getEmail())) {
                    System.out.println("Esse email já está sendo usado");
                    flag = true;
                    break;
                }
            }
        } while (flag);
        do{
            System.out.println("Digite seu nome");
            nomeDaConta = input.next();
            flag = false;
            for (int i = 0; i < nomeDaConta.length(); i++) {
                Character caractere = nomeDaConta.charAt(i);
                if (Character.isDigit(caractere)) {
                    flag = true;
                    break;
                }
            }
            if(flag){
                System.out.println("Não é permitido inserir números, informe apenas letras");
            }
        }while(flag);
        System.out.println("Digite o CPF");
        do {
            try {
                CPF = input.next();
                Long.parseLong(CPF);
                continua = false;
            } catch (NumberFormatException erro1) {
                System.out.println("Não é permitido inserir letras ou símbolos, informe apenas números inteiros");
                input.nextLine();
                continua = true;
            }
            if(CPF.length() != 11){
                continua = true;
                System.out.println("O CPF inserido é inválido");
            }
        }while(continua);
        System.out.println("Digite o endereço");
        endereco = input.next();
        System.out.println("Digite a senha");
        senha = input.next();

        novaConta.criarConta(nomeDaConta, CPF, email, endereco, senha);
        Contas.add(novaConta);

        System.out.println(Contas.toString());
    }
}
