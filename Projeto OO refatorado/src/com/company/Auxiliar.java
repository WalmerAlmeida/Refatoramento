package com.company;

import java.util.ArrayList;

public class Auxiliar {

    public ContaLoja acessarLoja(int idContaLoja, ArrayList<ContaLoja> Lojas){
        ContaLoja lojaAtual = new ContaLoja();
        for (ContaLoja L : Lojas) {
            if (L.getIdContaLoja() == idContaLoja) {
                lojaAtual = L;
            }
        }
        return lojaAtual;
    }

    public ContaLogin acessarConta(int idContaLogin, ArrayList<ContaLogin> Contas){
        ContaLogin contaAtual = new ContaLogin();
        for (ContaLogin C : Contas) {
            if (C.getIdContaLogin() == idContaLogin) {
                contaAtual = C;
            }
        }
        return contaAtual;
    }

}
