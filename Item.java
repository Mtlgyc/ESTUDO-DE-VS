package Sistema;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public abstract class Item {
    protected String titulo; 
    protected Usuario retiradoPor; 
    protected Date dtEmprestimo; 
    protected Date dtDevolucao;

   
    public Item(String titulo) {
        this.titulo = titulo;
    }

   
    public abstract boolean empresta(Usuario u);

   
    public abstract boolean retorna(Usuario u);

   
    public boolean isDisponivel() {
        return this.retiradoPor == null; 
    }

    
    public String getTitulo() {
        return titulo;
    }

    
    public boolean isEmprestado() {
        return this.retiradoPor != null; 
    }

   
    @Override
    public String toString() {
        if (this.isDisponivel()) {
            return titulo + " disponível";
        } else {
            return titulo + " emprestado por " + retiradoPor + " até " + dma(this.dtDevolucao); 
        }
    }

   
    public String dma(Date dt) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(dt); 

        
        return cal.get(Calendar.DATE) + "/" + (cal.get(Calendar.MONTH) + 1) + "/" + cal.get(Calendar.YEAR);
    }
}
