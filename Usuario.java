package Sistema;
import java.util.ArrayList;

public class Usuario {
    private String nome; // Nome do usuário
    private ArrayList<Livro> livrosRetirados; // Lista de livros que o usuário retirou

    // Construtor da classe Usuario
    public Usuario(String nome) {
        this.nome = nome; // Inicializa o nome do usuário
        this.livrosRetirados = new ArrayList<Livro>(); // Inicializa a lista de livros retirados
    }

    // Retorna a cota máxima de livros que o usuário pode retirar (fixo para todos os usuários)
    public int getCotaMaxima() {
        return 2; // Define a cota máxima de livros como 2
    }

    // Retorna o prazo máximo em dias para a devolução de livros (fixo para todos os usuários)
    public int getPrazoMaximo() {
        return 4; // Define o prazo máximo de empréstimo como 4 dias
    }

    // Verifica se o usuário está com algum livro a devolver ou com prazo vencido
    public boolean isADevolver() {
        // Retorna true se o número de livros retirados for maior ou igual à cota máxima ou se o usuário tiver livros em atraso
        return (this.livrosRetirados.size() >= this.getCotaMaxima() || this.temPrazoVencido());
    }

    // Verifica se o usuário está apto a retirar mais livros (não está com livros em atraso e não excedeu a cota)
    public boolean isAptoARetirar() {
        return (!this.isADevolver()); // Retorna true se o usuário não estiver com livros a devolver
    }

    // Verifica se algum livro retirado pelo usuário está com o prazo vencido
    public boolean temPrazoVencido() {
        for (Livro livro : this.livrosRetirados) {
            if (livro.isEmAtraso()) { // Verifica se o livro está em atraso
                return true; // Retorna true se algum livro estiver em atraso
            }
        }
        return false; // Retorna false se nenhum livro estiver em atraso
    }

    // Tenta retirar um livro para o usuário
    public boolean retiraLivro(Livro it) {
        if (this.isAptoARetirar()) { // Verifica se o usuário está apto a retirar o livro
            if (it.empresta(this)) { // Tenta emprestar o livro
                this.livrosRetirados.add(it); // Adiciona o livro à lista de livros retirados
                return true; // Retorna true se o livro foi emprestado com sucesso
            } else {
                return false; // Retorna false se não foi possível emprestar o livro
            }
        } else {
            return false; // Retorna false se o usuário não está apto a retirar o livro
        }
    }

    // Tenta devolver um livro
    public boolean devolveLivro(Livro it) {
        if (it.retorna(this)) { // Tenta devolver o livro
            this.livrosRetirados.remove(it); // Remove o livro da lista de livros retirados
            return true; // Retorna true se o livro foi devolvido com sucesso
        } else {
            return false; // Retorna false se não foi possível devolver o livro
        }
    }

    // Método para verificar se o usuário é um professor (por padrão, retorna false para usuários normais)
    public boolean isProfessor() {
        return false; // Retorna false, pois este método é sobrescrito em subclasses de Usuário
    }

    // Retorna o nome do usuário
    public String getNome() {
        return this.nome;
    }

    // Retorna uma representação em string do usuário
    public String toString() {
        return "Usuario " + nome; // Retorna uma string com o nome do usuário
    }

    // Retorna uma lista detalhada da carga atual de livros do usuário
    public String listaCarga() {
        StringBuilder carga = new StringBuilder(); // Usa StringBuilder para criar a string de carga

        carga.append(this.toString()) // Adiciona o nome do usuário
                .append(" Limite: ")
                .append(this.getCotaMaxima()) // Adiciona a cota máxima
                .append(" Carga atual: ")
                .append(this.livrosRetirados.size()); // Adiciona a quantidade de livros retirados

        for (Livro livro : this.livrosRetirados) {
            carga.append(livro.toString()) // Adiciona a descrição de cada livro retirado
                    .append("\n");
        }

        return carga.toString(); // Retorna a string completa com a carga de livros
    }
}