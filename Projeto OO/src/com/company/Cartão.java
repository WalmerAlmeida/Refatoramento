package com.company;

public class Cartão {
    private String nomeDoTitular, CVV, numeroDoCartão, dataDeVencimento;
    private boolean statusPago;

    public boolean isStatusPago() {
        return statusPago;
    }

    public void setStatusPago(boolean statusPago) {
        this.statusPago = statusPago;
    }

    public void inserirDados(String nomeDoTitular, String dataDeVencimento, String CVV, String numeroDoCartão){
        this.nomeDoTitular = nomeDoTitular;
        this.dataDeVencimento = dataDeVencimento;
        this.CVV = CVV;
        this.numeroDoCartão = numeroDoCartão;
        this.statusPago = true;
    }
    public String toString(){
        return  "Nome do Titular: " + nomeDoTitular
                + "\nData de Vencimento: " + dataDeVencimento
                + "\nCVV: " + CVV
                + "\nNúmero do Cartão: " + numeroDoCartão
                + "\nStatus: " + statusPago + "\n";
    }
}
