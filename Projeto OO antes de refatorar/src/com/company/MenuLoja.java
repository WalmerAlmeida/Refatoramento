package com.company;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuLoja {
    Scanner input = new Scanner(System.in);
    public void MenuLoja(int idContaLogin){
        int opcao = 0;
        boolean continua = true;
        Sistema novoSistema = new Sistema();

        while(true) {
            System.out.println("Selecione :\n"
                    + "1 : Criar uma Loja\n"
                    + "2 : Selecinar Loja\n"
                    + "3 : Editar Perfil\n"
                    + "4 : Remover Conta\n"
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
                case 1://Criar Loja
                    novoSistema.criarLoja(idContaLogin);
                    break;
                case 2://Selecionar Loja
                    novoSistema.selecionarLoja(idContaLogin);
                    break;
                case 3://Editar Perfil
                    novoSistema.editarPerfil(idContaLogin);
                    break;
                case 4://Remover Conta//quando remover conta, lembrar de só permitir se quiser excluir as lojas em que é administrador
                    novoSistema.removerConta(idContaLogin);
                    return;
                case 0://Sair
                    System.out.println("Saindo...");
                    return;
                default:
                    System.out.println("Digite uma opção válida");
            }
        }
    }
}
