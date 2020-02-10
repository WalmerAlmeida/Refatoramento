# Refatoramento


1º Duplicated Code: 
  Observei que nas classes ContaLoja e ContaLogin, haviam dados relacionados ao usuário que estavam duplicados nas duas classes. Então, apliquei o padrão de projeto extract class, criei uma classe chamada DadosDoUsuário, que guarda o CPF, email, endereço e nome.
  
2º Large Class:
  Observei que havia uma classe chamada Sistema, com muitos métodos provenientes de muitas classes diferentes chamadas, MenuLogin, MenuLoja, MenuPrincipal, MenuProduto e MenuCarrinho. Então, decidi dividir o conteúdo dessa classe em outras classes, cada um correspondente ao seu Menu, classes essas chamadas SistemaMenuLogin, SistemaMenuLoja, SistemaMenuPrincipal, SistemaMenuProduto e SistemaMenuCarrinho.
  
3º Long Parameter List:
  Observei que em muitos métodos pertencentes a Produto, suas subclasses e o Departamento, os parâmetros usados são muito longos, então, criei uma classe chamada PesosMedidas, para armazenar parte desses valores(peso, altura, comprimento e largura) e evitar essa sobrecarga no parâmetro.
  
