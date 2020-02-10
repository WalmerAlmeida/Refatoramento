package com.company;

import java.util.ArrayList;

public class ContaLogin {

    private static int sequencia = 0;
    private String senha;
    private int idContaLogin;
    private Carrinho carrinhoDeCompras = new Carrinho();
    private DadosDoUsuário dados = new DadosDoUsuário();

    private ArrayList<Integer> ProdutosComprados = new ArrayList<Integer>();//classe com id de produtos que já foram comprados
    private ArrayList<Integer> LojasPertencentes = new ArrayList<Integer>();//id das lojas que essa conta possui

    public DadosDoUsuário getDados() {
        return dados;
    }

    public void setDados(DadosDoUsuário dados) {
        this.dados = dados;
    }

    public Carrinho getCarrinhoDeCompras() {
        return carrinhoDeCompras;
    }

    public void setCarrinhoDeCompras(Carrinho carrinhoDeCompras) {
        this.carrinhoDeCompras = carrinhoDeCompras;
    }

    public ArrayList<Integer> getLojasPertencentes() {
        return LojasPertencentes;
    }

    public void setLojasPertencentes(ArrayList<Integer> lojasPertencentes) {
        LojasPertencentes = lojasPertencentes;
    }

    public ArrayList<Integer> getProdutosComprados() {
        return ProdutosComprados;
    }

    public void setProdutosComprados(ArrayList<Integer> produtosComprados) {
        ProdutosComprados = produtosComprados;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getIdContaLogin() {
        return idContaLogin;
    }

    public void setIdContaLogin(int idContaLogin) {
        this.idContaLogin = idContaLogin;
    }

    public void criarConta(String nomeDaConta, String CPF, String email, String endereco, String senha){
        this.idContaLogin = sequencia++;
        this.dados.setNomeDaConta(nomeDaConta);
        this.dados.setCPF(CPF);
        this.dados.setEmail(email);
        this.dados.setEndereco(endereco);
        this.senha = senha;
    }

    public void adicionarLoja(int idContaLoja){
        LojasPertencentes.add(idContaLoja);
    }

    public void adicionarAoCarrinho(Produto produtoAtual){
        this.carrinhoDeCompras.adicionarAoCarrinho(produtoAtual);
    }

    public void inserirProdutosComprados(int idProduto){
        for(int idP : ProdutosComprados){
            if(idP == idProduto){
                return;
            }
        }
        ProdutosComprados.add(idProduto);
    }

    public String toString(){
        return  "id da Conta de Login: " + idContaLogin
                + "\nNome da Conta: " + dados.getNomeDaConta()
                + "\nCPF: " + dados.getCPF()
                + "\nEmail: " + dados.getEmail()
                + "\nEndereço: " + dados.getEndereco()
                + "\nSenha: " + senha
                + "\nLojas Pertencentes: " + LojasPertencentes
                + "\nProdutos Comprados: " + ProdutosComprados + "\n";
    }

}
