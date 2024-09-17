package Sistema;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public abstract class Item {
    protected String titulo; // O título do item
    protected Usuario retiradoPor; // O usuário que retirou o item (ou null se não estiver emprestado)
    protected Date dtEmprestimo; // A data em que o item foi emprestado
    protected Date dtDevolucao; // A data em que o item deve ser devolvido

    // Construtor que inicializa o título do item
    public Item(String titulo) {
        this.titulo = titulo;
    }

    // Método abstrato para emprestar o item; deve ser implementado pelas subclasses
    public abstract boolean empresta(Usuario u);

    // Método abstrato para devolver o item; deve ser implementado pelas subclasses
    public abstract boolean retorna(Usuario u);

    // Verifica se o item está disponível para empréstimo
    public boolean isDisponivel() {
        return this.retiradoPor == null; // Disponível se não tiver um usuário que o retirou
    }

    // Retorna o título do item
    public String getTitulo() {
        return titulo;
    }

    // Verifica se o item está emprestado
    public boolean isEmprestado() {
        return this.retiradoPor != null; // Emprestado se tiver um usuário que o retirou
    }

    // Retorna uma representação em string do estado atual do item
    @Override
    public String toString() {
        if (this.isDisponivel()) {
            return titulo + " disponível"; // Se o item estiver disponível
        } else {
            return titulo + " emprestado por " + retiradoPor + " até " + dma(this.dtDevolucao); // Se o item estiver emprestado, exibe o usuário e a data de devolução
        }
    }

    // Formata uma data no formato "dia/mês/ano"
    public String dma(Date dt) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(dt); // Define a data no calendário

        // Retorna a data formatada como "dia/mês/ano"
        return cal.get(Calendar.DATE) + "/" + (cal.get(Calendar.MONTH) + 1) + "/" + cal.get(Calendar.YEAR);
    }
}
