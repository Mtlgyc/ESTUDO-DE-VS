package Sistema;

import java.util.GregorianCalendar;
import java.util.Calendar;

public class MapaTematico extends Item {
    private String nivelPrivilegio;


    public MapaTematico(String titulo, String nivelPrivilegio) {
        super(titulo);
        this.nivelPrivilegio = nivelPrivilegio; 
    }

    
    @Override
    public boolean empresta(Usuario u) {
      
        if (this.isDisponivel() && podeRetirar(u)) {
            this.retiradoPor = u; 
            GregorianCalendar cal = new GregorianCalendar();
            cal.add(Calendar.DATE, 2); 

            this.dtDevolucao = cal.getTime(); 

            return true;
        }

        return false; 
    }

   
    @Override
    public boolean retorna(Usuario u) {
   
        if (u.equals(this.retiradoPor)) {
            this.retiradoPor = null; 

            return true; 
        }

        return false;
    }


    private boolean podeRetirar(Usuario u) {
        if (nivelPrivilegio.equals("Professor")) {
            return u.getNome().equals("Professor");
        } else if (nivelPrivilegio.equals("Aluno")) {
            return u.getNome().equals("Aluno") || u.getNome().equals("Professor");
        }

        return true; 
    }

    
    public void setNivelPrivilegio(String nivel) {
        this.nivelPrivilegio = nivel;
    }

   
    @Override
    public String toString() {
        if (this.isDisponivel()) {
            return super.toString() + ", nível de privilégio: " + nivelPrivilegio;
        } else {
            return super.toString() + " emprestado até " + dma(dtDevolucao);
        }
    }
}
