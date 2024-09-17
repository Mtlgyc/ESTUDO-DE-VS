package Sistema;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

// Classe Livro que herda de Item e adiciona funcionalidades específicas para livros
public class Livro extends Item {
    private Usuario bloqueadoPor; // Usuário que bloqueou o livro (ou null se não estiver bloqueado)
    private Date dtBloqueio; // Data em que o livro foi bloqueado
    private Date dtDesbloqueio; // Data em que o livro pode ser desbloqueado

    // Construtor que inicializa o livro com um título
    public Livro(String titulo) {
        super(titulo); // Chama o construtor da classe base Item para definir o título
    }

    // Implementa o método para emprestar o livro a um usuário
    @Override
    public boolean empresta(Usuario u) {
        GregorianCalendar cal = new GregorianCalendar();

        // Verifica se o livro está disponível
        if (this.isDisponivel()) {
            this.retiradoPor = u; // Define o usuário que retirou o livro
            this.dtEmprestimo = cal.getTime(); // Registra a data do empréstimo

            // Adiciona o prazo máximo do usuário ao calendário
            cal.add(Calendar.DATE, u.getPrazoMaximo());
            this.dtDevolucao = cal.getTime(); // Define a data de devolução

            return true; // Empréstimo realizado com sucesso
        }

        return false; // Livro não disponível para empréstimo
    }

    // Implementa o método para devolver o livro
    @Override
    public boolean retorna(Usuario u) {
        // Verifica se o usuário que está devolvendo o livro é o mesmo que o retirou
        if (u.equals(this.retiradoPor)) {
            this.retiradoPor = null; // Libera o livro para novos empréstimos
            return true; // Devolução realizada com sucesso
        }

        return false; // O livro não pode ser devolvido por este usuário
    }

    // Verifica se o livro está bloqueado
    public boolean isBloqueado() {
        Date hoje = new Date(); // Obtém a data atual
        // O livro está bloqueado se tiver um bloqueador e a data de desbloqueio for depois da data atual
        return this.bloqueadoPor != null && this.dtDesbloqueio != null && this.dtDesbloqueio.after(hoje);
    }

    // Verifica se o livro está em atraso
    public boolean isEmAtraso() {
        Date hoje = new Date(); // Obtém a data atual
        // O livro está em atraso se estiver emprestado e a data atual for após a data de devolução
        return this.isEmprestado() && hoje.after(this.dtDevolucao);
    }

    // Bloqueia o livro por um determinado prazo, se disponível e o usuário for um professor
    public boolean bloqueia(Usuario u, int prazo) {
        GregorianCalendar cal = new GregorianCalendar();

        // Verifica se o livro está disponível e se o usuário é um professor
        if (this.isDisponivel() && u.isProfessor()) {
            this.bloqueadoPor = u; // Define o usuário que bloqueou o livro
            this.dtBloqueio = cal.getTime(); // Registra a data do bloqueio

            // Adiciona o prazo ao calendário (máximo de 20 dias)
            cal.add(Calendar.DATE, Math.min(prazo, 20));
            this.dtDesbloqueio = cal.getTime(); // Define a data de desbloqueio

            return true; // Bloqueio realizado com sucesso
        }

        return false; // Livro não pode ser bloqueado
    }

    // Desbloqueia o livro se o usuário que está desbloqueando for o mesmo que bloqueou
    public boolean desbloqueia(Usuario u) {
        // Verifica se o usuário que está tentando desbloquear é o mesmo que bloqueou o livro
        if (u.equals(this.bloqueadoPor)) {
            this.bloqueadoPor = null; // Remove o bloqueador
            return true; // Desbloqueio realizado com sucesso
        }

        return false; // O livro não pode ser desbloqueado por este usuário
    }

    // Retorna uma representação em string do estado atual do livro
    @Override
    public String toString() {
        // Se o livro está disponível, retorna o status de disponibilidade da classe base Item
        if (this.isDisponivel()) {
            return super.toString();
        }
        // Se o livro está bloqueado, retorna o status do bloqueio
        else if (this.isBloqueado()) {
            return super.toString() + " bloqueado por " + bloqueadoPor + " em " + dma(dtBloqueio) + " até " + dma(dtDesbloqueio);
        } 
        // Se o livro está emprestado, retorna o status do empréstimo
        else {
            return super.toString() + " emprestado por " + retiradoPor + " até " + dma(dtDevolucao);
        }
    }
}
