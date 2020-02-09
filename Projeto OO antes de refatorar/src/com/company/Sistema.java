package com.company;
import javax.swing.*;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.ArrayList;

public class Sistema {
    Scanner input = new Scanner(System.in);

    Auxiliar aux = new Auxiliar();

    private static ArrayList<ContaLoja> Lojas = new ArrayList<ContaLoja>();
    private static ArrayList<ContaLogin> Contas = new ArrayList<ContaLogin>();

    public void fazerLogin(){
        String email, senha;
        int opcao = 0;
        boolean flagLogin, continua, flagOpcao = false;

        MenuLoja novoMenu = new MenuLoja();

        if(Contas.isEmpty()){
            System.out.println("Não existem contas registradas, crie a sua conta antes de fazer o login");
            return;
        }

        do {
            flagLogin= false;
            System.out.println("Insira os dados de Login: ");
            System.out.println("Digite seu email");
            email = input.next();
            System.out.println("Digite sua senha");
            senha = input.next();
            for(ContaLogin C : Contas) {
                if (email.equals(C.getEmail()) && senha.equals(C.getSenha())) {
                    flagLogin = true;

                    novoMenu.MenuLoja(C.getIdContaLogin());

                    break;
                }
            }
            if(!flagLogin){
                System.out.println("O email ou senha está incorreto, digite novamente");
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
        }while(!flagLogin);
    }

    public void criarConta() {
        ContaLogin novaConta = new ContaLogin();

        String nomeDaConta, email, endereco, senha;
        String CPF = "";
        boolean flag, continua;

        System.out.println("Insira os dados de Criação da sua Conta: ");
        do {
            flag = false;
            System.out.println("Digite seu email");
            email = input.next();
            for (ContaLogin C : Contas) {
                if (email.equals(C.getEmail())) {
                    System.out.println("Esse email já está sendo usado");
                    flag = true;
                    break;
                }
            }
        } while (flag);
        do{
            System.out.println("Digite seu nome");
            nomeDaConta = input.next();
            flag = false;
            for (int i = 0; i < nomeDaConta.length(); i++) {
                Character caractere = nomeDaConta.charAt(i);
                if (Character.isDigit(caractere)) {
                    flag = true;
                    break;
                }
            }
            if(flag){
                System.out.println("Não é permitido inserir números, informe apenas letras");
            }
        }while(flag);
        System.out.println("Digite o CPF");
        do {
            try {
                CPF = input.next();
                Long.parseLong(CPF);
                continua = false;
            } catch (NumberFormatException erro1) {
                System.out.println("Não é permitido inserir letras ou símbolos, informe apenas números inteiros");
                input.nextLine();
                continua = true;
            }
            if(CPF.length() != 11){
                continua = true;
                System.out.println("O CPF inserido é inválido");
            }
        }while(continua);
        System.out.println("Digite o endereço");
        endereco = input.next();
        System.out.println("Digite a senha");
        senha = input.next();

        novaConta.criarConta(nomeDaConta, CPF, email, endereco, senha);
        Contas.add(novaConta);

        System.out.println(Contas.toString());
    }

    public void criarLoja(int idContaLogin){
        ContaLoja novaLoja = new ContaLoja();

        String nomeDaLoja;
        boolean flag;

        System.out.println("Insira os dados de Criação da sua Loja: ");
        do {
            flag = false;
            System.out.println("Digite o nome da sua loja");
            nomeDaLoja = input.next();
            for(ContaLoja L : Lojas) {
                if (nomeDaLoja.equals(L.getNomeDaLoja())) {
                    System.out.println("Esse nome já está sendo usado");
                    flag = true;
                    break;
                }
            }
        }while(flag);
        for(ContaLogin C : Contas){
            if(idContaLogin == C.getIdContaLogin()){
                novaLoja.criarLoja(nomeDaLoja, C);
            }
        }

        Lojas.add(novaLoja);

        for(ContaLogin C : Contas){
            if(C.getIdContaLogin() == idContaLogin) {
                C.adicionarLoja(novaLoja.getIdContaLoja());
            }
        }

        System.out.println(Lojas.toString());

    }

    public void selecionarLoja(int idContaLogin) {
        boolean flagSelecionar;
        String nomeDaLoja;

        MenuPrincipal novoMenuPrincipal = new MenuPrincipal();

        if (Lojas.isEmpty()) {
            System.out.println("Não existem lojas para serem acessadas");
            return;
        }
        do {
            flagSelecionar = true;
            System.out.println("Lojas :");
            for (ContaLoja L : Lojas) {
                System.out.println(L.getNomeDaLoja());
            }

            System.out.println("digite o nome da loja que deseja entrar");
            nomeDaLoja = input.next();
            for (ContaLoja L : Lojas) {
                if (nomeDaLoja.equals(L.getNomeDaLoja())) {
                    flagSelecionar = false;

                    System.out.println(L.toString());

                    novoMenuPrincipal.MenuPrincipal(L, idContaLogin);
                    break;
                }
            }

            if (flagSelecionar == true) {
                System.out.println("\nO nome da loja selecionada não existe\n");
            }

        } while (flagSelecionar);
    }

    public void editarPerfil(int idContaLogin){
        MenuEditarPerfil novoMenuEditarPerfil = new MenuEditarPerfil();
        ContaLogin contaAtual;
        ContaLoja lojaAtual = null;
        contaAtual = aux.acessarConta(idContaLogin, Contas);

        for(ContaLoja L : Lojas){
            if(L.getAdministrador() == idContaLogin){
                lojaAtual = L;
            }
        }

        novoMenuEditarPerfil.MenuEditarPerfil(Contas, lojaAtual, contaAtual);

        System.out.println(Contas.toString());
    }

    public void adicionarProduto(int idContaLoja){
        ContaLoja lojaAtual;
        MenuTipoProduto tipo = new MenuTipoProduto();
        lojaAtual = aux.acessarLoja(idContaLoja, Lojas);

        tipo.MenuTipoProduto(lojaAtual);

    }

    public void removerProduto(int idContaLoja){
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

    public void adicionarDepartamento(int idContaLoja){
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

    public void removerDepartamento(int idContaLoja){
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

    public void buscarProduto(int idContaLoja, int idContaLogin){
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

    public void listarProdutos(int idContaLoja, int idContaLogin){
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

    public void editarProduto(ContaLoja lojaAtual, Produto produtoAtual){
        String nomeDoProduto, descricao;
        double altura = 0, comprimento = 0, largura = 0, peso = 0, preco = 0;
        boolean flag, continua;

        do {
            flag = false;

            System.out.println("Digite o nome do produto");
            nomeDoProduto = input.next();
            for (Departamento D : lojaAtual.getDepartamentos()){
                for (Produto P : D.getProdutos()) {
                    if (nomeDoProduto.equals(P.getNomeDoProduto())) {
                        System.out.println("O nome digitado já está em uso");
                        flag = true;
                    }
                }
            }
        } while (flag);

        System.out.println("Faça uma descrição do produto");
        descricao = input.next();

        System.out.println("Digite o preço");
        do {
            try {
                preco = Double.parseDouble(input.next());
                continua = false;
            } catch (NumberFormatException e) {
                System.out.println("Não é permitido inserir letras ou símbolos, informe apenas números");
                input.nextLine();
                continua = true;
            }
        }while(continua);

        System.out.println("Digite as dimensões do produto");
        System.out.println("Altura :");
        do {
            try {
                altura = Double.parseDouble(input.next());
                continua = false;
            } catch (NumberFormatException e) {
                System.out.println("Não é permitido inserir letras ou símbolos, informe apenas números");
                input.nextLine();
                continua = true;
            }
        }while(continua);
        System.out.println("Comprimento :");
        do {
            try {
                comprimento = Double.parseDouble(input.next());
                continua = false;
            } catch (NumberFormatException e) {
                System.out.println("Não é permitido inserir letras ou símbolos, informe apenas números");
                input.nextLine();
                continua = true;
            }
        }while(continua);
        System.out.println("Largura :");
        do {
            try {
                largura = Double.parseDouble(input.next());
                continua = false;
            } catch (NumberFormatException e) {
                System.out.println("Não é permitido inserir letras ou símbolos, informe apenas números");
                input.nextLine();
                continua = true;
            }
        }while(continua);

        System.out.println("Digite o peso do produto");
        do {
            try {
                peso = Double.parseDouble(input.next());
                continua = false;
            } catch (NumberFormatException e) {
                System.out.println("Não é permitido inserir letras ou símbolos, informe apenas números");
                input.nextLine();
                continua = true;
            }
        }while(continua);

        produtoAtual.editarProduto(nomeDoProduto, descricao, preco, altura, comprimento, largura, peso);
    }

    public void colocarDesconto(Produto produtoAtual){
        double desconto = 0;
        boolean continua;
        do {
            System.out.println("Digite o desconto desse produto em porcentagem(%)");
            do {
                try {
                    desconto = Double.parseDouble(input.next());
                    continua = false;
                } catch (NumberFormatException e) {
                    System.out.println("Não é permitido inserir letras ou símbolos, informe apenas números");
                    input.nextLine();
                    continua = true;
                }
            }while(continua);
            if(desconto > 100 || desconto < 0){
                System.out.println("Não é possível colocar um desconto maior que 100, nem menor que 0");
            }
        }while(desconto > 100 || desconto < 0);
        produtoAtual.colocarDesconto(desconto);
    }

    public void adicionarAoCarrinho(Produto produtoAtual, int idContaLogin){
        for(ContaLogin C : Contas){
            if(idContaLogin == C.getIdContaLogin()){
                C.adicionarAoCarrinho(produtoAtual);
            }
        }
    }

    public void deixarComentário(Produto produtoAtual, int idContaLogin){
        String mensagem;
        boolean flag;
        flag = true;
        for(ContaLogin C : Contas){
            if(idContaLogin == C.getIdContaLogin()){
                for(int idP : C.getProdutosComprados()){
                    if(idP == produtoAtual.getIdProduto()){
                        flag = false;
                        System.out.println("Escreva o seu comentário");
                        mensagem = input.nextLine();
                        produtoAtual.deixarComentario(mensagem, C.getNomeDaConta());
                    }
                }
            }
        }
        if(flag){
            System.out.println("você não comprou esse produto");
        }
    }

    public void aumentarQuantidade(Carrinho carrinhoAtual){
        int quantidade = 0, indice = 0;
        double precoTotal = 0;
        String nomeDoProduto;
        boolean flag, continua;

        do {
            flag = true;
            System.out.println("Digite o nome do produto que deseja mudar a quantidade");
            nomeDoProduto = input.next();
            for (ElementoCarrinho EC : carrinhoAtual.getElementos()) {
                if (nomeDoProduto.equals(EC.getProdutoAdicionado().getNomeDoProduto())) {
                    flag = false;
                    while (true) {
                        System.out.println("Digite a quantidade do produto selecionado");
                        do {
                            try {
                                quantidade = input.nextInt();
                                continua = false;
                            } catch (InputMismatchException erro1) {
                                System.out.println("Não é permitido inserir letras, informe apenas números inteiros");
                                input.nextLine();
                                continua = true;
                            }
                        }while(continua);
                        if (quantidade > 0) {
                            EC.setQuantidade(quantidade);
                            break;
                        } else if (quantidade == 0) {
                            indice = carrinhoAtual.getElementos().indexOf(EC);
                            break;
                        }else{
                            System.out.println("O valor da quantidade não pode ser negativo");
                        }
                    }
                }
            }
            if(flag){
                System.out.println("Item não encontrado");
            }
        }while(flag);
        if(quantidade == 0){
            carrinhoAtual.getElementos().remove(indice);
        }
        precoTotal = 0;
        for(ElementoCarrinho EC : carrinhoAtual.getElementos()){
            precoTotal = (EC.getProdutoAdicionado().getPrecoFinal()*EC.getQuantidade()) + precoTotal;
        }
        carrinhoAtual.setPrecoTotal(precoTotal);
    }

    public void acessarCarrinho(int idContaLogin){
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

    public void finalizarCompra(Carrinho carrinhoAtual, int idContaLogin){
        Cartão novocartao = new Cartão();
        String nomeDoTitular, CVV = "", numeroDoCartão = "", dataDeVencimento = "";
        boolean flag, continua;
        int data = 0, dia, mês, ano;

        ContaLogin contaAtual;
        contaAtual = aux.acessarConta(idContaLogin, Contas);
        System.out.println("Insira os dados do cartão: ");
        do{
            System.out.println("Digite o nome do titular do cartão");
            nomeDoTitular = input.nextLine();
            flag = false;
            for (int i = 0; i < nomeDoTitular.length(); i++) {
                Character caractere = nomeDoTitular.charAt(i);
                if (Character.isDigit(caractere)) {
                    flag = true;
                    break;
                }
            }
            if(flag){
                System.out.println("Não é permitido inserir números, informe apenas letras");
            }
        }while(flag);
        System.out.println("Digite o número do cartão");
        do {
            try {
                numeroDoCartão = input.next();
                Long.parseLong(numeroDoCartão);
                continua = false;
            } catch (NumberFormatException erro1) {
                System.out.println("Não é permitido inserir letras ou símbolos, informe apenas números inteiros");
                input.nextLine();
                continua = true;
            }
            if(numeroDoCartão.length() != 16){
                continua = true;
                System.out.println("O numero do cartão inserido é inválido");
            }
        }while(continua);
        System.out.println("Digite o CVV do cartão");
        do {
            try {
                CVV = input.next();
                Integer.parseInt(CVV);
                continua = false;
            } catch (NumberFormatException erro1) {
                System.out.println("Não é permitido inserir letras ou símbolos, informe apenas números inteiros");
                input.nextLine();
                continua = true;
            }
            if(CVV.length() != 3){
                continua = true;
                System.out.println("O numero do cartão inserido é inválido");
            }
        }while(continua);
        System.out.println("Digite a data de vencimento no fomato DDMMAAAA");
        do {
            try {
                dataDeVencimento = input.next();
                data = Integer.parseInt(dataDeVencimento);
                continua = false;
            } catch (NumberFormatException erro1) {
                System.out.println("Não é permitido inserir letras ou símbolos, informe apenas números inteiros");
                input.nextLine();
                continua = true;
            }
            if(dataDeVencimento.length() != 8){
                continua = true;
                System.out.println("O numero do cartão inserido é inválido");
            }
            dia = data/1000000;
            mês = (data/10000)-(dia*100);
            ano = (data - mês*10000) - dia*1000000;
            if(dia > 31 || dia < 1){
                System.out.println("Data inválida");
                continua = true;
            }else if(mês > 12 || mês < 1){
                System.out.println("Data inválida");
                continua = true;
            }else if(ano < 2019){
                System.out.println("Data inválida");
                continua = true;
            }
        }while(continua);
        novocartao.inserirDados(nomeDoTitular, dataDeVencimento, CVV, numeroDoCartão);//o criação da classe Cartão tem o objetivo de simular a plataforma que verificaria os dados do cartão e repassaria o pagamento, como
                                                                                        //não possuo as informações para verificar os dados do cartão, considero que para qualquer dado inserido, está correto.
        System.out.println(novocartao.toString());
        if(novocartao.isStatusPago() == true){
            for (ElementoCarrinho EC : carrinhoAtual.getElementos()) {
                contaAtual.inserirProdutosComprados(EC.getProdutoAdicionado().getIdProduto());
            }
            carrinhoAtual.getElementos().clear();
            System.out.println("Compra concluída com sucesso");
        }else{
            System.out.println("Dados inválidos");
        }
    }

    public void exibirProdutosComprados(int idContaLoja, int idContaLogin){
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

    public void removerConta(int idContaLogin){

        int i = 0;
        ContaLogin contaAtual;
        String resposta;
        boolean flag, flagRemove;

        contaAtual = aux.acessarConta(idContaLogin, Contas);
        do {
            flag = false;
            System.out.println("Você tem certeza que deseja remover sua conta?");
            System.out.println("Todas as lojas criadas por essa conta serão apagadas, digite 'sim' para continuar e 'não' para retornar ao menu");
            resposta = input.next();
            if (resposta.equals("sim")) {
                do {
                    flagRemove =false;
                    for (int LP : contaAtual.getLojasPertencentes()) {
                        for (ContaLoja L : Lojas) {
                            if (LP == L.getIdContaLoja()) {
                                flagRemove = true;
                                i = Lojas.indexOf(L);
                                break;
                            }
                        }
                    }
                    if(flagRemove){
                        Lojas.remove(i);
                    }
                }while(flagRemove);
                for (ContaLogin C : Contas) {
                    if (C.getIdContaLogin() == idContaLogin) {
                        i = Contas.indexOf(C);
                    }
                }
                Contas.remove(i);
            } else if (resposta.equals("não")) {
                return;
            } else {
                flag = true;
                System.out.println("Resposta inválida");
            }
        }while(flag);
    }

    public void removerLoja(int idContaLoja){
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

}
