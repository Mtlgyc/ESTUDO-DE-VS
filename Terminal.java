package Sistema;

import java.util.Scanner;
import java.util.Date;

public class Terminal {
  private ControleBiblioteca controle; // Instância do controle da biblioteca

  // Construtor da classe Terminal
  public Terminal() {
    controle = new ControleBiblioteca(); // Inicializa a instância do ControleBiblioteca
  }

  // Método inicial que exibe o menu principal e lida com as opções
  public void ini() {
    Scanner sc = new Scanner(System.in); // Scanner para entrada de dados
    int op;

    // Loop para exibir o menu principal até que o usuário escolha sair (op = 0)
    do {
      op = this.getInt("1. Modo Administrador\n2. Modo Atendimento\n0. Sair");

      switch (op) {
        case 0:
          System.out.println("\nFeito por Josué e Carlos Eduardo"); // Mensagem de encerramento
          sc.close(); // Fecha o scanner
          return; // Sai do método ini

        case 1:
          modoAdministrador(sc); // Chama o modo administrador
          break;

        case 2:
          modoAtendimento(sc); // Chama o modo atendimento
          break;

        default:
          System.out.println("\nOpção inválida"); // Mensagem de opção inválida
      }
    } while (op != 0); // Continua até que o usuário escolha sair
  }

  // Método para lidar com as operações administrativas
  private void modoAdministrador(Scanner sc) {
    int op;

    // Loop para exibir o menu administrativo até que o usuário escolha voltar (op = 0)
    do {
      op = getInt(
          "1. Adicionar Livro\n2. Remover Livro\n3. Adicionar Mapa Temático\n4. Remover Mapa Temático\n5. Adicionar Usuário\n6. Remover Usuário\n0. Voltar");

      switch (op) {
        case 1:
          System.out.print("\nTítulo do livro: ");
          String tituloLivro = sc.nextLine(); // Obtém o título do livro

          controle.adicionarLivro(new Livro(tituloLivro)); // Adiciona o livro à biblioteca

          System.out.println("\nLivro adicionado"); // Mensagem de sucesso
          break;

        case 2:
          System.out.print("\nTítulo do livro: ");
          tituloLivro = sc.nextLine(); // Obtém o título do livro

          controle.removerLivro(controle.getLivro(tituloLivro)); // Remove o livro da biblioteca

          System.out.println("\nLivro removido"); // Mensagem de sucesso
          break;

        case 3:
          System.out.print("\nTítulo do mapa temático: ");
          String tituloMapa = sc.nextLine(); // Obtém o título do mapa temático

          System.out.print("Nível de privilégio: ");
          String nivelPrivilegio = sc.nextLine(); // Obtém o nível de privilégio

          controle.adicionarMapaTematico(new MapaTematico(tituloMapa, nivelPrivilegio)); // Adiciona o mapa temático à biblioteca

          System.out.println("\nMapa temático adicionado."); // Mensagem de sucesso
          break;

        case 4:
          System.out.print("\nTítulo do mapa temático: ");
          tituloMapa = sc.nextLine(); // Obtém o título do mapa temático

          controle.removerMapaTematico(controle.getMapaTematico(tituloMapa)); // Remove o mapa temático da biblioteca

          System.out.println("\nMapa temático removido."); // Mensagem de sucesso
          break;

        case 5:
          System.out.print("\nNome do usuário: ");
          String nomeUsuario = sc.nextLine(); // Obtém o nome do usuário

          System.out.print("Tipo (Aluno/Professor): ");
          String tipo = sc.nextLine(); // Obtém o tipo de usuário (Aluno ou Professor)

          if (tipo.equalsIgnoreCase("Aluno")) {
            System.out.print("Data de validade (dia/mês/ano): ");
            String data = sc.nextLine(); // Obtém a data de validade

            // Cria um novo aluno com a data de validade e adiciona à biblioteca
            controle.adicionarUsuario(new Aluno(nomeUsuario, new Date(data)));
          } else {
            // Cria um novo professor e adiciona à biblioteca
            controle.adicionarUsuario(new Professor(nomeUsuario));
          }

          System.out.println("\nUsuário adicionado."); // Mensagem de sucesso
          break;

        case 6:
          System.out.print("\nNome do usuário: ");
          nomeUsuario = sc.nextLine(); // Obtém o nome do usuário

          controle.removerUsuario(controle.getUsuario(nomeUsuario)); // Remove o usuário da biblioteca

          System.out.println("\nUsuário removido"); // Mensagem de sucesso
          break;

        case 0:
          return; // Volta ao menu principal

        default:
          System.out.println("Opção inválida"); // Mensagem de opção inválida
      }
    } while (op != 0); // Continua até que o usuário escolha voltar
  }

  // Método para lidar com as operações de atendimento
  private void modoAtendimento(Scanner scanner) {
    int op;

    System.out.print("Nome do usuário: ");
    String nomeUsuario = scanner.nextLine(); // Obtém o nome do usuário

    Usuario usuario = controle.getUsuario(nomeUsuario); // Obtém o usuário da biblioteca

    if (usuario == null) {
      System.out.println("Usuário não encontrado"); // Mensagem se o usuário não for encontrado
      return;
    }

    // Loop para exibir o menu de atendimento até que o usuário escolha voltar (op = 0)
    do {
      op = getInt(
          "1. Retirar Livro\n2. Devolver Livro\n3. Retirar Mapa Temático\n4. Devolver Mapa Temático\n5. Consultar Status\n0. Voltar");

      switch (op) {
        case 1:
          System.out.print("Título do livro: ");
          String tituloLivro = scanner.nextLine(); // Obtém o título do livro

          if (controle.emprestaLivro(tituloLivro, usuario)) {
            System.out.println("Livro retirado com sucesso"); // Mensagem de sucesso
          } else {
            System.out.println("Não foi possível retirar o livro"); // Mensagem de falha
          }
          break;

        case 2:
          System.out.print("Título do livro: ");
          tituloLivro = scanner.nextLine(); // Obtém o título do livro

          if (controle.devolveLivro(tituloLivro, usuario)) {
            System.out.println("Livro devolvido com sucesso"); // Mensagem de sucesso
          } else {
            System.out.println("Não foi possível devolver o livro"); // Mensagem de falha
          }
          break;

        case 3:
          System.out.print("Título do mapa temático: ");
          String tituloMapa = scanner.nextLine(); // Obtém o título do mapa temático

          if (controle.emprestaMapaTematico(tituloMapa, usuario)) {
            System.out.println("Mapa temático retirado com sucesso"); // Mensagem de sucesso
          } else {
            System.out.println("Não foi possível retirar o mapa temático"); // Mensagem de falha
          }
          break;

        case 4:
          System.out.print("Título do mapa temático: ");
          tituloMapa = scanner.nextLine(); // Obtém o título do mapa temático

          if (controle.devolveMapaTematico(tituloMapa, usuario)) {
            System.out.println("Mapa temático devolvido com sucesso"); // Mensagem de sucesso
          } else {
            System.out.println("Não foi possível devolver o mapa temático"); // Mensagem de falha
          }
          break;

        case 5:
          System.out.print("Título do item para status: ");
          String tituloItem = scanner.nextLine(); // Obtém o título do item

          controle.mostrarStatus(tituloItem); // Mostra o status do item

          break;

        case 0:
          return; // Volta ao menu principal

        default:
          System.out.println("Opção inválida"); // Mensagem de opção inválida
      }
    } while (op != 0); // Continua até que o usuário escolha voltar
  }

  // Método auxiliar para obter um inteiro do usuário
  private int getInt(String st) {
    Scanner sc = new Scanner(System.in); // Novo Scanner para entrada de dados

    System.out.print("\nInsira uma opção:\n" + st + "\nR: ");
    int op = sc.nextInt(); // Lê o inteiro

    return op; // Retorna o inteiro lido
  }
}
