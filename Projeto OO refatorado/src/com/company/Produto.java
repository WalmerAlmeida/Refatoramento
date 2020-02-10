package com.company;

import java.util.ArrayList;

public class Produto {

    private static int sequencia = 0;
    private String nomeDoProduto, descricao, departamento;
    private int idProduto;
    private double preco, altura, comprimento, largura, peso, precoFinal, valorDoFrete, desconto;//medida em centímetros e peso em kilogramas, desconto em %

    private ArrayList<PerfilComentario> Comentarios = new ArrayList<PerfilComentario>();

    public double getPrecoFinal() {
        return precoFinal;
    }

    public void setPrecoFinal(double precoFinal) {
        this.precoFinal = precoFinal;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public String getNomeDoProduto() {
        return nomeDoProduto;
    }

    public void setNomeDoProduto(String nomeDoProduto) {
        this.nomeDoProduto = nomeDoProduto;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public double getDesconto() {
        return desconto;
    }

    public void setDesconto(double desconto) {
        this.desconto = desconto;
    }

    public double getValorDoFrete() {
        return valorDoFrete;
    }

    public void setValorDoFrete(double valorDoFrete) {
        this.valorDoFrete = valorDoFrete;
    }

    public ArrayList<PerfilComentario> getComentarios() {
        return Comentarios;
    }

    public void setComentarios(ArrayList<PerfilComentario> comentarios) {
        Comentarios = comentarios;
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    public double getComprimento() {
        return comprimento;
    }

    public void setComprimento(double comprimento) {
        this.comprimento = comprimento;
    }

    public double getLargura() {
        return largura;
    }

    public void setLargura(double largura) {
        this.largura = largura;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public double calcularFrete(PesosMedidas pesosMedidas){
        double frete, pesoCubico, pesoUsado = 0;

        double altura = pesosMedidas.getAltura(),
        comprimento = pesosMedidas.getComprimento(),
        largura = pesosMedidas.getLargura(),
        peso = pesosMedidas.getPeso();

        pesoCubico= (comprimento*largura*altura)/6000;
        if(pesoCubico <= 5){
            pesoUsado = peso;
        }else if(pesoCubico > 5){
            if(peso > pesoCubico){
                pesoUsado = peso;
            }else{
                pesoUsado = pesoCubico;
            }
        }
        frete = 10*pesoUsado;//coloquei o valor de 10 reais o kilo para envio, o valor relacionado a distância e impostos não são calculados
        return frete;
    }

    public void adicionarProduto(String nomeDoProduto, String descricao, String departamento, double preco, PesosMedidas pesosMedidas){
        this.idProduto = sequencia++;
        this.nomeDoProduto= nomeDoProduto;
        this.descricao = descricao;
        this.departamento = departamento;
        this.preco = preco;
        this.altura = pesosMedidas.getAltura();
        this.comprimento = pesosMedidas.getComprimento();
        this.largura = pesosMedidas.getLargura();
        this.peso = pesosMedidas.getPeso();
        this.valorDoFrete = calcularFrete(pesosMedidas);
        this.precoFinal = this.preco + this.valorDoFrete;
    }

    public void editarProduto(String nomeDoProduto, String descricao, double preco, PesosMedidas pesosMedidas){
        this.nomeDoProduto= nomeDoProduto;
        this.descricao = descricao;
        this.preco = preco;
        this.altura = pesosMedidas.getAltura();
        this.comprimento = pesosMedidas.getComprimento();
        this.largura = pesosMedidas.getLargura();
        this.peso = pesosMedidas.getPeso();
        this.valorDoFrete = calcularFrete(pesosMedidas);
        this.precoFinal = this.preco + this.valorDoFrete;
    }

    public void colocarDesconto(double desconto){
        this.desconto = desconto;
        this.precoFinal = (this.preco*(1 - (this.desconto/100))) + this.valorDoFrete;
    }

    public void deixarComentario(String mensagem, String nomeDaConta){
        PerfilComentario comentario = new PerfilComentario();
        comentario.setMensagem(mensagem);
        comentario.setNomeDaConta(nomeDaConta);
        Comentarios.add(comentario);
        System.out.println(comentario.toString());
    }

    public String toString(){
        return  "Id do Produto: " + idProduto
                + "\nNome do Produto: " + nomeDoProduto
                + "\nDescrição: " + descricao
                + "\nAltura: " + altura + "cm"
                + "\nComprimento: " + comprimento + "cm"
                + "\nLargura: " + largura + "cm"
                + "\nPeso: " + peso + "kg"
                + "\nDepartamento: " + departamento
                + "\nPreço: R$" + preco
                + "\nValor do Frete: R$" + valorDoFrete
                + "\nDesconto: " + desconto + "%"
                + "\nPreço Final: R$" + precoFinal
                + "\nComentários: " + Comentarios + "\n";
    }
}
