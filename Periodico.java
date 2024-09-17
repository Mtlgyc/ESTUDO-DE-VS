package Sistema;

public class Periodico extends Livro {
    // Construtor da classe Periodico
    public Periodico(String tit) {
        super(tit); // Chama o construtor da classe base Livro para inicializar o título
    }

    // Método para emprestar o periódico a um usuário
    @Override
    public boolean empresta(Usuario u) {
        // Verifica se o usuário é um professor
        if (u.isProfessor()) {
            // Se o usuário é um professor, chama o método empresta da classe base Livro
            return super.empresta(u);
        } else {
            // Caso contrário, retorna falso, indicando que o empréstimo não é permitido
            return false;
        }
    }
}
