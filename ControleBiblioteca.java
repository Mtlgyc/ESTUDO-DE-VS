package Sistema;

import java.util.ArrayList;

public class ControleBiblioteca {
    private ArrayList<Livro> livros; // Lista de livros disponíveis na biblioteca
    private ArrayList<Usuario> usuarios; // Lista de usuários cadastrados na biblioteca
    private ArrayList<MapaTematico> mapasTematicos; // Lista de mapas temáticos disponíveis na biblioteca

    // Construtor da classe ControleBiblioteca
    public ControleBiblioteca() {
        livros = new ArrayList<Livro>(); // Inicializa a lista de livros
        usuarios = new ArrayList<Usuario>(); // Inicializa a lista de usuários
        mapasTematicos = new ArrayList<MapaTematico>(); // Inicializa a lista de mapas temáticos
    }

    // Adiciona um livro à lista de livros da biblioteca
    public void adicionarLivro(Livro livro) {
        livros.add(livro); // Adiciona o livro à lista
    }

    // Remove um livro da lista de livros da biblioteca
    public void removerLivro(Livro livro) {
        livros.remove(livro); // Remove o livro da lista
    }

    // Adiciona um usuário à lista de usuários da biblioteca
    public void adicionarUsuario(Usuario usuario) {
        usuarios.add(usuario); // Adiciona o usuário à lista
    }

    // Remove um usuário da lista de usuários da biblioteca
    public void removerUsuario(Usuario usuario) {
        usuarios.remove(usuario); // Remove o usuário da lista
    }

    // Adiciona um mapa temático à lista de mapas temáticos da biblioteca
    public void adicionarMapaTematico(MapaTematico mapa) {
        mapasTematicos.add(mapa); // Adiciona o mapa temático à lista
    }

    // Remove um mapa temático da lista de mapas temáticos da biblioteca
    public void removerMapaTematico(MapaTematico mapa) {
        mapasTematicos.remove(mapa); // Remove o mapa temático da lista
    }

    // Tenta emprestar um livro ao usuário com base no título do livro
    public boolean emprestaLivro(String titulo, Usuario usuario) {
        for (Livro livro : livros) { // Percorre a lista de livros
            if (livro.getTitulo().equals(titulo)) { // Verifica se o título do livro corresponde
                return livro.empresta(usuario); // Tenta emprestar o livro e retorna o resultado
            }
        }
        return false; // Retorna false se o livro não for encontrado
    }

    // Tenta devolver um livro pelo título do livro
    public boolean devolveLivro(String titulo, Usuario usuario) {
        for (Livro livro : livros) { // Percorre a lista de livros
            if (livro.getTitulo().equals(titulo)) { // Verifica se o título do livro corresponde
                return livro.retorna(usuario); // Tenta devolver o livro e retorna o resultado
            }
        }
        return false; // Retorna false se o livro não for encontrado
    }

    // Tenta emprestar um mapa temático ao usuário com base no título do mapa
    public boolean emprestaMapaTematico(String titulo, Usuario usuario) {
        for (MapaTematico mapa : mapasTematicos) { // Percorre a lista de mapas temáticos
            if (mapa.getTitulo().equals(titulo)) { // Verifica se o título do mapa corresponde
                return mapa.empresta(usuario); // Tenta emprestar o mapa e retorna o resultado
            }
        }
        return false; // Retorna false se o mapa temático não for encontrado
    }

    // Tenta devolver um mapa temático pelo título do mapa
    public boolean devolveMapaTematico(String titulo, Usuario usuario) {
        for (MapaTematico mapa : mapasTematicos) { // Percorre a lista de mapas temáticos
            if (mapa.getTitulo().equals(titulo)) { // Verifica se o título do mapa corresponde
                return mapa.retorna(usuario); // Tenta devolver o mapa e retorna o resultado
            }
        }
        return false; // Retorna false se o mapa temático não for encontrado
    }

    // Encontra um usuário com base no nome
    public Usuario getUsuario(String nome) {
        for (Usuario usuario : usuarios) { // Percorre a lista de usuários
            if (usuario.getNome().equals(nome)) { // Verifica se o nome do usuário corresponde
                return usuario; // Retorna o usuário encontrado
            }
        }
        return null; // Retorna null se o usuário não for encontrado
    }

    // Encontra um livro com base no título
    public Livro getLivro(String titulo) {
        for (Livro livro : livros) { // Percorre a lista de livros
            if (livro.getTitulo().equals(titulo)) { // Verifica se o título do livro corresponde
                return livro; // Retorna o livro encontrado
            }
        }
        return null; // Retorna null se o livro não for encontrado
    }

    // Encontra um mapa temático com base no título
    public MapaTematico getMapaTematico(String titulo) {
        for (MapaTematico mapa : mapasTematicos) { // Percorre a lista de mapas temáticos
            if (mapa.getTitulo().equals(titulo)) { // Verifica se o título do mapa corresponde
                return mapa; // Retorna o mapa temático encontrado
            }
        }
        return null; // Retorna null se o mapa temático não for encontrado
    }

    // Mostra o status de um item (livro ou mapa temático) com base no título
    public void mostrarStatus(String titulo) {
        Livro livro = getLivro(titulo); // Tenta encontrar o livro
        if (livro != null) { // Se o livro for encontrado
            System.out.println(livro); // Imprime o status do livro
            return; // Sai do método após exibir o status
        }

        MapaTematico mapa = getMapaTematico(titulo); // Tenta encontrar o mapa temático
        if (mapa != null) { // Se o mapa temático for encontrado
            System.out.println(mapa); // Imprime o status do mapa temático
            return; // Sai do método após exibir o status
        }

        System.out.println("Item não encontrado"); // Se o item não for encontrado, exibe uma mensagem de erro
    }
}

