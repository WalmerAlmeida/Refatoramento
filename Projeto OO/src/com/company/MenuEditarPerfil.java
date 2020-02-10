package com.company;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuEditarPerfil {
    Scanner input = new Scanner(System.in);
    public void MenuEditarPerfil(ArrayList<ContaLogin> Contas, ContaLoja lojaAtual, ContaLogin contaAtual) {
        String nomeDaConta, email, endereco, senha;
        String CPF = "";
        boolean flag, continua;
        int opcao = 0;

        while (true) {
            System.out.println("Selecione os dados que deseja mudar :\n"
                    + "1 : Email\n"
                    + "2 : Nome da Conta\n"
                    + "3 : CPF\n"
                    + "4 : Endereço\n"
                    + "5 : Senha\n"
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
                case 1://Email
                    do {
                        flag = false;
                        System.out.println("Digite seu email");
                        email = input.next();
                        for(ContaLogin C : Contas) {
                            if (email.equals(C.getDados().getEmail())) {
                                System.out.println("Esse email já está sendo usado");
                                flag = true;
                                break;
                            }
                        }
                    }while(flag);
                    contaAtual.getDados().setEmail(email);
                    lojaAtual.getDados().setEmail(email);
                    break;
                case 2://Nome da Conta
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
                    contaAtual.getDados().setNomeDaConta(nomeDaConta);
                    lojaAtual.getDados().setNomeDaConta(nomeDaConta);
                    break;
                case 3://CPF
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
                    contaAtual.getDados().setCPF(CPF);
                    lojaAtual.getDados().setCPF(CPF);
                    break;
                case 4://Endereço
                    System.out.println("Digite o endereço");
                    endereco = input.next();
                    contaAtual.getDados().setEndereco(endereco);
                    lojaAtual.getDados().setEndereco(endereco);
                    break;
                case 5://Senha
                    System.out.println("Digite a senha");
                    senha = input.next();
                    contaAtual.setSenha(senha);
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
