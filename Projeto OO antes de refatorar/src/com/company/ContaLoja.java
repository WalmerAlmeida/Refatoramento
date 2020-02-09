package com.company;

import java.util.ArrayList;

public class ContaLoja{

    private static int sequencia = 0;
    private String nomeDaLoja, CPF, nomeDaConta, email, endereco;
    private int idContaLoja;
    private int administrador;//possui o id da conta que criou a loja
    private ArrayList<Departamento> Departamentos = new ArrayList<Departamento>();

    public ArrayList<Departamento> getDepartamentos() {
        return Departamentos;
    }

    public void setDepartamentos(ArrayList<Departamento> departamentos) {
        Departamentos = departamentos;
    }

    public String getNomeDaLoja() {
        return nomeDaLoja;
    }

    public void setNomeDaLoja(String nomeDaLoja) {
        this.nomeDaLoja = nomeDaLoja;
    }

    public int getIdContaLoja() {
        return idContaLoja;
    }

    public void setIdContaLoja(int idContaLoja) {
        this.idContaLoja = idContaLoja;
    }

    public int getAdministrador() {
        return administrador;
    }

    public void setAdministrador(int administrador) {
        this.administrador = administrador;
    }

    public String getNomeDaConta() {
        return nomeDaConta;
    }

    public void setNomeDaConta(String nomeDaConta) {
        this.nomeDaConta = nomeDaConta;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public void criarLoja(String nomeDaLoja, ContaLogin C){
        this.idContaLoja = sequencia++;
        this.nomeDaLoja = nomeDaLoja;
        this.nomeDaConta = C.getNomeDaConta();
        this.CPF = C.getCPF();
        this.email = C.getEmail();
        this.endereco = C.getEndereco();

        this.administrador = C.getIdContaLogin();
    }

    public void adicionarDepartamento(String nomeDoDepartamento){
        Departamento novoDepartamento = new Departamento();
        novoDepartamento.adicionarDepartamento(nomeDoDepartamento);
        Departamentos.add(novoDepartamento);
    }

    public void removerDepartamento(String nomeDoDepartamento){
        for(Departamento D : Departamentos){
            if(nomeDoDepartamento.equals(D.getNomeDoDepartamento())){
                Departamentos.remove(D);

                System.out.println("Departamento Apagado: \n" + D.toString());

                break;
            }
        }

        for(Departamento D : Departamentos){
            System.out.println("Demais Departamentos: \n" + D.toString());
        }

    }

    public String toString(){
        return  "id da loja: " + idContaLoja
                + "\nNome da Loja: " + nomeDaLoja
                + "\nNome do Administrador: " + nomeDaConta
                + "\nCPF: " + CPF
                + "\nEmail: " + email
                + "\nEndereço: " + endereco
                + "\nId do administrador: " + administrador + "\n";
    }

}
