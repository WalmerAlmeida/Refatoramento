package com.company;

import com.company.sistema.SistemaMenuLogin;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuLogin {
    Scanner input = new Scanner(System.in);

    public void MenuLogin() {
        int opcao = 0;
        boolean continua = true;
        SistemaMenuLogin novoSistemaMenuLogin = new SistemaMenuLogin();



        while (true) {
            do {
                System.out.println("Selecione :\n"
                        + "1 : Fazer Login\n"
                        + "2 : Criar Conta\n"
                        + "0 : Fechar\n");
                try {
                    opcao = input.nextInt();
                    continua = false;
                } catch (InputMismatchException erro1) {
                    System.out.println("Não é permitido inserir letras ou símbolos, informe apenas números inteiros");
                    input.nextLine();//descarta entrada errada do usuário
                    continua = true;
                }

            } while (continua);

            switch (opcao) {
                case 1://Fazer Login
                    novoSistemaMenuLogin.fazerLogin(DadosLojaseContas.Contas);
                    break;
                case 2://Criar Conta
                    novoSistemaMenuLogin.criarConta(DadosLojaseContas.Contas);
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
