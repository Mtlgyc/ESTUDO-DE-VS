package Sistema;

import java.util.Date;

public class Aluno extends Usuario {
    private Date dataLimite; // Data limite para a validade do cartão do aluno

    // Construtor da classe Aluno
    public Aluno(String nome, Date dt) {
        super(nome); // Inicializa o nome do aluno chamando o construtor da classe pai (Usuario)
        this.dataLimite = dt; // Define a data limite do cartão do aluno
    }

    // Atualiza a data limite do cartão do aluno
    public void renovaCartao(Date dt) {
        this.dataLimite = dt; // Define uma nova data limite para o cartão
    }

    // Verifica se o cartão do aluno está regular (não expirado)
    public boolean isRegular() {
        Date hoje = new Date(); // Obtém a data atual
        return dataLimite.after(hoje); // Retorna true se a data limite for após a data atual
    }

    // Verifica se o cartão do aluno está prestes a expirar e precisa ser renovado
    public boolean isARenovar() {
        return !isRegular(); // Retorna true se o cartão não estiver regular
    }

    // Sobrescreve o método da classe pai para definir a cota máxima de livros
    @Override
    public int getCotaMaxima() {
        if (isRegular()) { // Se o cartão estiver regular
            return 3; // O aluno pode retirar até 3 livros
        }
        return super.getCotaMaxima(); // Caso contrário, retorna o valor padrão da classe pai
    }

    // Sobrescreve o método da classe pai para definir o prazo máximo para devolução de livros
    @Override
    public int getPrazoMaximo() {
        if (isRegular()) { // Se o cartão estiver regular
            return 7; // O aluno tem um prazo máximo de 7 dias para devolver livros
        }
        return super.getPrazoMaximo(); // Caso contrário, retorna o valor padrão da classe pai
    }

    // Sobrescreve o método toString da classe pai para representar o aluno como uma string
    @Override
    public String toString() {
        return "Aluno " + getNome(); // Retorna uma string formatada com a palavra "Aluno" seguida do nome do aluno
    }
}
    