package Sistema;

import java.util.GregorianCalendar;
import java.util.Calendar;

public class MapaTematico extends Item {
    private String nivelPrivilegio;

    // Construtor da classe MapaTematico
    public MapaTematico(String titulo, String nivelPrivilegio) {
        super(titulo); // Chama o construtor da classe base Item
        this.nivelPrivilegio = nivelPrivilegio; // Inicializa o nível de privilégio
    }

    // Método para emprestar o mapa temático a um usuário
    @Override
    public boolean empresta(Usuario u) {
        // Verifica se o mapa está disponível e se o usuário tem permissão para retirar
        if (this.isDisponivel() && podeRetirar(u)) {
            this.retiradoPor = u; // Define o usuário que retirou o item

            GregorianCalendar cal = new GregorianCalendar();
            cal.add(Calendar.DATE, 2); // Define a data de devolução para 2 dias no futuro

            this.dtDevolucao = cal.getTime(); // Atribui a data de devolução

            return true; // Retorna verdadeiro indicando sucesso no empréstimo
        }

        return false; // Retorna falso indicando falha no empréstimo
    }

    // Método para retornar o mapa temático
    @Override
    public boolean retorna(Usuario u) {
        // Verifica se o usuário que está tentando devolver é o mesmo que retirou
        if (u.equals(this.retiradoPor)) {
            this.retiradoPor = null; // Libera o item

            return true; // Retorna verdadeiro indicando sucesso na devolução
        }

        return false; // Retorna falso indicando falha na devolução
    }

    // Método privado para verificar se o usuário pode retirar o mapa
    private boolean podeRetirar(Usuario u) {
        // Verifica o nível de privilégio e compara com o nome do usuário
        if (nivelPrivilegio.equals("Professor")) {
            return u.getNome().equals("Professor");
        } else if (nivelPrivilegio.equals("Aluno")) {
            return u.getNome().equals("Aluno") || u.getNome().equals("Professor");
        }

        return true; // Permite a retirada se não houver restrição de nível
    }

    // Método para definir o nível de privilégio do mapa temático
    public void setNivelPrivilegio(String nivel) {
        this.nivelPrivilegio = nivel;
    }

    // Método para converter o objeto em uma string
    @Override
    public String toString() {
        if (this.isDisponivel()) {
            return super.toString() + ", nível de privilégio: " + nivelPrivilegio;
        } else {
            return super.toString() + " emprestado até " + dma(dtDevolucao);
        }
    }
}
