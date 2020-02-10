package com.company;

public class PerfilComentario {
    private String mensagem, nomeDaConta;

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getNomeDaConta() {
        return nomeDaConta;
    }

    public void setNomeDaConta(String nomeDaConta) {
        this.nomeDaConta = nomeDaConta;
    }

    public String toString(){
        return  "Nome do Usuário: " + nomeDaConta
                + "\nMensagem: " + mensagem + "\n";
    }
}
