package Sistema;

import java.util.Date;

public class Aluno extends Usuario {
    private Date dataLimite; 

    
    public Aluno(String nome, Date dt) {
        super(nome); 
        this.dataLimite = dt; 
    }

  
    public void renovaCartao(Date dt) {
        this.dataLimite = dt;
    }

   
    public boolean isRegular() {
        Date hoje = new Date(); 
        return dataLimite.after(hoje);
    }

   
    public boolean isARenovar() {
        return !isRegular(); 
    }

   
    @Override
    public int getCotaMaxima() {
        if (isRegular()) { 
            return 3;
        }
        return super.getCotaMaxima(); 
    }

    
    @Override
    public int getPrazoMaximo() {
        if (isRegular()) { 
            return 7;
        }
        return super.getPrazoMaximo();
    }

    
    @Override
    public String toString() {
        return "Aluno " + getNome(); 
    }
}
    
