package com.company;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuLogin {
    Scanner input = new Scanner(System.in);
    public void MenuLogin() {
        int opcao = 0;
        boolean continua = true;
        Sistema novoSistema = new Sistema();

        while (true) {
            System.out.println("Selecione :\n"
                    + "1 : Fazer Login\n"
                    + "2 : Criar Conta\n"
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
                case 1://Fazer Login
                    novoSistema.fazerLogin();
                    break;
                case 2://Criar Conta
                    novoSistema.criarConta();
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
