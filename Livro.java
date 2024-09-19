package Sistema;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


public class Livro extends Item {
    private Usuario bloqueadoPor; 
    private Date dtBloqueio; 
    private Date dtDesbloqueio; 

   
    public Livro(String titulo) {
        super(titulo); 
    }

  
    @Override
    public boolean empresta(Usuario u) {
        GregorianCalendar cal = new GregorianCalendar();

       
        if (this.isDisponivel()) {
            this.retiradoPor = u; 
            this.dtEmprestimo = cal.getTime(); 
            
            cal.add(Calendar.DATE, u.getPrazoMaximo());
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

    
    public boolean isBloqueado() {
        Date hoje = new Date();
        
        return this.bloqueadoPor != null && this.dtDesbloqueio != null && this.dtDesbloqueio.after(hoje);
    }

    
    public boolean isEmAtraso() {
        Date hoje = new Date(); 
        
        return this.isEmprestado() && hoje.after(this.dtDevolucao);
    }

   
    public boolean bloqueia(Usuario u, int prazo) {
        GregorianCalendar cal = new GregorianCalendar();

       
        if (this.isDisponivel() && u.isProfessor()) {
            this.bloqueadoPor = u;
            this.dtBloqueio = cal.getTime(); 

           
            cal.add(Calendar.DATE, Math.min(prazo, 20));
            this.dtDesbloqueio = cal.getTime(); 

            return true; 
        }

        return false; 
    }

    
    public boolean desbloqueia(Usuario u) {
        
        if (u.equals(this.bloqueadoPor)) {
            this.bloqueadoPor = null; 
            return true; 
        }

        return false; 
    }

    
    @Override
    public String toString() {
        
        if (this.isDisponivel()) {
            return super.toString();
        }
       
        else if (this.isBloqueado()) {
            return super.toString() + " bloqueado por " + bloqueadoPor + " em " + dma(dtBloqueio) + " até " + dma(dtDesbloqueio);
        } 
       
        else {
            return super.toString() + " emprestado por " + retiradoPor + " até " + dma(dtDevolucao);
        }
    }
}
