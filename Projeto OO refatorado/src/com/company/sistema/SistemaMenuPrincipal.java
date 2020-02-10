package com.company.sistema;

import com.company.*;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class SistemaMenuPrincipal {
    Scanner input = new Scanner(System.in);

    Auxiliar aux = new Auxiliar();
    public void adicionarProduto(int idContaLoja, ArrayList<ContaLoja> Lojas){
        ContaLoja lojaAtual;
        MenuTipoProduto tipo = new MenuTipoProduto();
        lojaAtual = aux.acessarLoja(idContaLoja, Lojas);

        tipo.MenuTipoProduto(lojaAtual);

    }

    public void removerProduto(int idContaLoja, ArrayList<ContaLoja> Lojas){
        ContaLoja lojaAtual;
        Departamento departamentoAtual = new Departamento();

        String nomeDoDepartamento, nomeDoProduto;
        boolean flag;

        lojaAtual = aux.acessarLoja(idContaLoja, Lojas);
        if (lojaAtual.getDepartamentos().isEmpty()) {
            System.out.println("Não existem departamentos nem produtos nessa loja");
            return;
        }

        do {
            flag = true;

            System.out.println("Departamentos :");
            for (Departamento D : lojaAtual.getDepartamentos()) {
                System.out.println(D.getNomeDoDepartamento());
            }

            System.out.println("Digite o departamento que o produto pertence");
            nomeDoDepartamento = input.next();

            for (Departamento D : lojaAtual.getDepartamentos()) {
                if (nomeDoDepartamento.equals(D.getNomeDoDepartamento())) {
                    flag = false;
                    departamentoAtual = D;
                }
            }

            if (flag) {
                System.out.println("Departamento não encontrado");
            }

        } while (flag);

        if (departamentoAtual.getProdutos().isEmpty()) {
            System.out.println("Não existem produtos nesse departamento");
            return;
        }

        do {
            flag = true;

            System.out.println("Produtos do Departamento Selecionado :");
            for (Produto P : departamentoAtual.getProdutos()) {
                System.out.println(P.getNomeDoProduto());
            }
            System.out.println("Digite o nome do produto");
            nomeDoProduto = input.next();
            for (Produto P : departamentoAtual.getProdutos()) {
                if (nomeDoProduto.equals(P.getNomeDoProduto())) {
                    flag = false;
                }
            }
            if(flag){
                System.out.println("Produto não encontrado");
            }
        } while (flag);

        departamentoAtual.removerProduto(nomeDoProduto);
    }

    public void adicionarDepartamento(int idContaLoja, ArrayList<ContaLoja> Lojas){
        ContaLoja lojaAtual;

        String departamento;
        boolean flag;

        lojaAtual = aux.acessarLoja(idContaLoja, Lojas);
        do {
            flag = false;
            System.out.println("Digite o nome do departamento que deseja adicionar");
            departamento = input.next();

            for (Departamento D : lojaAtual.getDepartamentos()) {
                if (departamento.equals(D.getNomeDoDepartamento())) {
                    flag = true;
                    System.out.println("O departamento digitado já existe");
                    break;
                }
            }
        } while (flag);

        lojaAtual.adicionarDepartamento(departamento);

    }

    public void removerDepartamento(int idContaLoja, ArrayList<ContaLoja> Lojas){
        ContaLoja lojaAtual;

        String departamento;
        boolean flag;

        lojaAtual = aux.acessarLoja(idContaLoja, Lojas);
        if (lojaAtual.getDepartamentos().isEmpty()) {
            System.out.println("Não existem departamentos nessa loja");
            return;
        }
        System.out.println("Departamentos :");
        for (Departamento D : lojaAtual.getDepartamentos()) {
            System.out.println(D.getNomeDoDepartamento());
        }
        do {
            flag = true;
            System.out.println("Digite o nome do departamento que deseja remover");
            departamento = input.next();

            for (Departamento D : lojaAtual.getDepartamentos()) {
                if (departamento.equals(D.getNomeDoDepartamento())) {
                    flag = false;
                    break;
                }
            }
            if(flag){
                System.out.println("Departamento não encontrado");
            }
        } while (flag);

        lojaAtual.removerDepartamento(departamento);
    }

    public void buscarProduto(int idContaLoja, int idContaLogin, ArrayList<ContaLoja> Lojas){
        ContaLoja lojaAtual;
        Produto produtoAtual = null;

        String produto;
        int opcao = 0;
        boolean flag, flagOpcao = false, continua;

        MenuProduto acessarProduto = new MenuProduto();
        lojaAtual = aux.acessarLoja(idContaLoja, Lojas);

        if (lojaAtual.getDepartamentos().isEmpty()) {
            System.out.println("Não existem departamentos nem produtos nessa loja");
            return;
        }

        do{
            flag = true;
            System.out.println("Digite o nome do produto");
            produto = input.next();
            for(Departamento D : lojaAtual.getDepartamentos()){
                for(Produto P : D.getProdutos()){
                    if(produto.equals(P.getNomeDoProduto())){
                        flag = false;
                        produtoAtual = P;
                        break;
                    }
                }
            }
            if(flag){
                System.out.println("Produto não encontrado");
                do{
                    System.out.println("Deseja retornar ao menu?, digite 0 para sair, senão digite 1");
                    do {
                        try {
                            opcao = input.nextInt();
                            continua = false;
                        } catch (InputMismatchException erro1) {
                            System.out.println("Não é permitido inserir letras ou símbolos, informe apenas números inteiros");
                            input.nextLine();
                            continua = true;
                        }
                    }while(continua);

                    if (opcao == 0) {
                        return;
                    } else if (opcao == 1) {
                        flagOpcao = false;
                    } else if (opcao != 1 && opcao != 0) {
                        flagOpcao = true;
                        System.out.println("Opção inválida");
                    }
                }while(flagOpcao);
            }
        }while(flag);
        acessarProduto.MenuProduto(lojaAtual, produtoAtual, idContaLogin);
    }

    public void listarProdutos(int idContaLoja, int idContaLogin, ArrayList<ContaLoja> Lojas){
        ContaLoja lojaAtual;
        Departamento departamentoAtual = null;
        Produto produtoAtual = null;

        String produto, departamento;
        boolean flag;

        MenuProduto acessarProduto = new MenuProduto();
        lojaAtual = aux.acessarLoja(idContaLoja, Lojas);

        if (lojaAtual.getDepartamentos().isEmpty()) {
            System.out.println("Não existem departamentos nem produtos nessa loja");
            return;
        }

        do {
            System.out.println("Departamentos :");
            for (Departamento D : lojaAtual.getDepartamentos()) {
                System.out.println(D.getNomeDoDepartamento());
            }
            flag = true;
            System.out.println("Digite o nome do departamento");
            departamento = input.next();
            for(Departamento D : lojaAtual.getDepartamentos()){
                if(departamento.equals(D.getNomeDoDepartamento())){
                    flag = false;
                    departamentoAtual = D;
                    if (D.getProdutos().isEmpty()) {
                        System.out.println("Não existem produtos nesse departamento");
                        return;
                    }
                    break;
                }
            }
            if(flag){
                System.out.println("Departamento não encontrado");
            }
        }while (flag);

        do{
            System.out.println("Produtos :");
            for (Produto P : departamentoAtual.getProdutos()) {
                System.out.println(P.getNomeDoProduto());
            }
            flag = true;
            System.out.println("Digite o nome do produto");
            produto = input.next();
            for(Produto P : departamentoAtual.getProdutos()){
                if(produto.equals(P.getNomeDoProduto())){
                    flag = false;
                    produtoAtual = P;
                    break;
                }
            }
            if(flag){
                System.out.println("Produto não encontrado");
            }
        }while(flag);
        acessarProduto.MenuProduto(lojaAtual, produtoAtual, idContaLogin);
    }

    public void exibirProdutosComprados(int idContaLoja, int idContaLogin, ArrayList<ContaLogin> Contas, ArrayList<ContaLoja> Lojas){
        ContaLogin contaAtual;
        ContaLoja lojaAtual;
        contaAtual = aux.acessarConta(idContaLogin, Contas);
        lojaAtual = aux.acessarLoja(idContaLoja, Lojas);
        System.out.println("Produtos: ");
        for(int idP : contaAtual.getProdutosComprados()){
            for(Departamento D : lojaAtual.getDepartamentos()) {
                for (Produto P : D.getProdutos()) {
                    if (idP == P.getIdProduto()) {
                        System.out.println(P.toString());
                    }
                }
            }
        }
    }

    public void removerLoja(int idContaLoja, ArrayList<ContaLogin> Contas, ArrayList<ContaLoja> Lojas){
        int indice = 0;
        ContaLogin contaAtual;
        ContaLoja lojaAtual;
        lojaAtual = aux.acessarLoja(idContaLoja, Lojas);
        contaAtual = aux.acessarConta(lojaAtual.getAdministrador(), Contas);

        for(int LP : contaAtual.getLojasPertencentes()){
            if(LP == lojaAtual.getIdContaLoja()){
                indice = contaAtual.getLojasPertencentes().indexOf(LP);
            }
        }
        contaAtual.getLojasPertencentes().remove(indice);
        for(ContaLoja L : Lojas){
            if(lojaAtual.getIdContaLoja() == L.getIdContaLoja()){
                indice = Lojas.indexOf(L);
            }
        }
        Lojas.remove(indice);
        System.out.println(contaAtual.toString());
    }

    public void acessarCarrinho(int idContaLogin, ArrayList<ContaLogin> Contas){
        double precoTotal = 0;
        MenuCarrinho novoMenuCarrinho = new MenuCarrinho();
        Carrinho carrinhoAtual = null;
        for(ContaLogin C : Contas){
            if(C.getIdContaLogin() == idContaLogin){
                carrinhoAtual = C.getCarrinhoDeCompras();
            }
        }

        for(ElementoCarrinho EC : carrinhoAtual.getElementos()){
            precoTotal = (EC.getProdutoAdicionado().getPrecoFinal()*EC.getQuantidade()) + precoTotal;
        }
        carrinhoAtual.setPrecoTotal(precoTotal);

        if(carrinhoAtual.getElementos().isEmpty()){
            System.out.println("O carrinho está vazio");
            return;
        }

        novoMenuCarrinho.MenuCarrinho(carrinhoAtual, idContaLogin);
    }

}
