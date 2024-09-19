package Sistema;

import java.util.Scanner;
import java.util.Date;

public class Terminal {
  private ControleBiblioteca controle; 

 
  public Terminal() {
    controle = new ControleBiblioteca();
  }


  public void ini() {
    Scanner sc = new Scanner(System.in); 
    int op;


    do {
      op = this.getInt("1. Modo Administrador\n2. Modo Atendimento\n0. Sair");

      switch (op) {
        case 0:
          System.out.println("\nFeito por Josué e Carlos Eduardo"); 
          sc.close(); 
          return; 

        case 1:
          modoAdministrador(sc); 
          break;

        case 2:
          modoAtendimento(sc); 
          break;

        default:
          System.out.println("\nOpção inválida"); 
      }
    } while (op != 0);
  }

  
  private void modoAdministrador(Scanner sc) {
    int op;

   
    do {
      op = getInt(
          "1. Adicionar Livro\n2. Remover Livro\n3. Adicionar Mapa Temático\n4. Remover Mapa Temático\n5. Adicionar Usuário\n6. Remover Usuário\n0. Voltar");

      switch (op) {
        case 1:
          System.out.print("\nTítulo do livro: ");
          String tituloLivro = sc.nextLine(); 

          controle.adicionarLivro(new Livro(tituloLivro));

          System.out.println("\nLivro adicionado"); 
          break;

        case 2:
          System.out.print("\nTítulo do livro: ");
          tituloLivro = sc.nextLine(); 

          controle.removerLivro(controle.getLivro(tituloLivro));

          System.out.println("\nLivro removido"); 
          break;

        case 3:
          System.out.print("\nTítulo do mapa temático: ");
          String tituloMapa = sc.nextLine(); 

          System.out.print("Nível de privilégio: ");
          String nivelPrivilegio = sc.nextLine(); 

          controle.adicionarMapaTematico(new MapaTematico(tituloMapa, nivelPrivilegio));
          System.out.println("\nMapa temático adicionado."); 
          break;

        case 4:
          System.out.print("\nTítulo do mapa temático: ");
          tituloMapa = sc.nextLine(); 

          controle.removerMapaTematico(controle.getMapaTematico(tituloMapa)); 
          System.out.println("\nMapa temático removido.");
          break;

        case 5:
          System.out.print("\nNome do usuário: ");
          String nomeUsuario = sc.nextLine(); 

          System.out.print("Tipo (Aluno/Professor): ");
          String tipo = sc.nextLine(); 

          if (tipo.equalsIgnoreCase("Aluno")) {
            System.out.print("Data de validade (dia/mês/ano): ");
            String data = sc.nextLine();

       
            controle.adicionarUsuario(new Aluno(nomeUsuario, new Date(data)));
          } else {
         
            controle.adicionarUsuario(new Professor(nomeUsuario));
          }

          System.out.println("\nUsuário adicionado.");
          break;

        case 6:
          System.out.print("\nNome do usuário: ");
          nomeUsuario = sc.nextLine(); 

          controle.removerUsuario(controle.getUsuario(nomeUsuario)); 

          System.out.println("\nUsuário removido"); 
          break;

        case 0:
          return;
        default:
          System.out.println("Opção inválida");
      }
    } while (op != 0); 
  }

  // Método para lidar com as operações de atendimento
  private void modoAtendimento(Scanner scanner) {
    int op;

    System.out.print("Nome do usuário: ");
    String nomeUsuario = scanner.nextLine();

    Usuario usuario = controle.getUsuario(nomeUsuario);
    if (usuario == null) {
      System.out.println("Usuário não encontrado"); 
      return;
    }

   
    do {
      op = getInt(
          "1. Retirar Livro\n2. Devolver Livro\n3. Retirar Mapa Temático\n4. Devolver Mapa Temático\n5. Consultar Status\n0. Voltar");

      switch (op) {
        case 1:
          System.out.print("Título do livro: ");
          String tituloLivro = scanner.nextLine(); 

          if (controle.emprestaLivro(tituloLivro, usuario)) {
            System.out.println("Livro retirado com sucesso"); 
          } else {
            System.out.println("Não foi possível retirar o livro"); 
          }
          break;

        case 2:
          System.out.print("Título do livro: ");
          tituloLivro = scanner.nextLine(); 

          if (controle.devolveLivro(tituloLivro, usuario)) {
            System.out.println("Livro devolvido com sucesso"); 
          } else {
            System.out.println("Não foi possível devolver o livro");
          }
          break;

        case 3:
          System.out.print("Título do mapa temático: ");
          String tituloMapa = scanner.nextLine(); 

          if (controle.emprestaMapaTematico(tituloMapa, usuario)) {
            System.out.println("Mapa temático retirado com sucesso"); 
          } else {
            System.out.println("Não foi possível retirar o mapa temático"); 
          }
          break;

        case 4:
          System.out.print("Título do mapa temático: ");
          tituloMapa = scanner.nextLine(); 

          if (controle.devolveMapaTematico(tituloMapa, usuario)) {
            System.out.println("Mapa temático devolvido com sucesso"); 
          } else {
            System.out.println("Não foi possível devolver o mapa temático"); 
          }
          break;

        case 5:
          System.out.print("Título do item para status: ");
          String tituloItem = scanner.nextLine();

          controle.mostrarStatus(tituloItem); 

          break;

        case 0:
          return;

        default:
          System.out.println("Opção inválida"); 
      }
    } while (op != 0); 
  }

  
  private int getInt(String st) {
    Scanner sc = new Scanner(System.in); 

    System.out.print("\nInsira uma opção:\n" + st + "\nR: ");
    int op = sc.nextInt(); 

    return op;
  }
}
