package Sistema;

public class Professor extends Usuario {
    // Construtor da classe Professor
    public Professor(String nome) {
        super(nome); // Chama o construtor da classe base Usuario para inicializar o nome
    }

    // Método para bloquear um livro
    public boolean bloqueiaLivro(Livro it, int prazo) {
        // Chama o método bloqueia da classe Livro passando o usuário (this) e o prazo
        return it.bloqueia(this, prazo);
    }

    // Método para desbloquear um livro
    public boolean desbloqueiaLivro(Livro it) {
        // Chama o método desbloqueia da classe Livro passando o usuário (this)
        return it.desbloqueia(this);
    }

    // Reescrita do método getCotaMaxima da classe base Usuario
    @Override
    public int getCotaMaxima() {
        return 5; // Professores têm uma cota máxima de 5 livros
    }

    // Reescrita do método getPrazoMaximo da classe base Usuario
    @Override
    public int getPrazoMaximo() {
        return 14; // Professores têm um prazo máximo de 14 dias para devolver livros
    }

    // Reescrita do método isProfessor da classe base Usuario
    @Override
    public boolean isProfessor() {
        return true; // Este método retorna true para indicar que o usuário é um professor
    }

    // Reescrita do método toString da classe base Usuario
    @Override
    public String toString() {
        return "Prof. " + getNome(); // Retorna uma representação em string do professor com o prefixo "Prof."
    }
}

	