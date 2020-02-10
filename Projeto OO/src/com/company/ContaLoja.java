package com.company;

import java.util.ArrayList;

public class ContaLoja{

    private static int sequencia = 0;
    private String nomeDaLoja;
    private int idContaLoja;
    private int administrador;//possui o id da conta que criou a loja
    private DadosDoUsuário dados = new DadosDoUsuário();

    private ArrayList<Departamento> Departamentos = new ArrayList<Departamento>();

    public DadosDoUsuário getDados() {
        return dados;
    }

    public void setDados(DadosDoUsuário dados) {
        this.dados = dados;
    }

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

    public void criarLoja(String nomeDaLoja, ContaLogin C){
        this.idContaLoja = sequencia++;
        this.nomeDaLoja = nomeDaLoja;
        this.dados.setNomeDaConta(C.getDados().getNomeDaConta());
        this.dados.setCPF(C.getDados().getCPF());
        this.dados.setEmail(C.getDados().getEmail());
        this.dados.setEndereco(C.getDados().getEndereco());

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
                + "\nNome do Administrador: " + dados.getNomeDaConta()
                + "\nCPF: " + dados.getCPF()
                + "\nEmail: " + dados.getEmail()
                + "\nEndereço: " + dados.getEndereco()
                + "\nId do administrador: " + administrador + "\n";
    }

}
