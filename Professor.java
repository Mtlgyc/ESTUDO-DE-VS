package Sistema;

public class Professor extends Usuario {

    public Professor(String nome) {
        super(nome); 
    }

   
    public boolean bloqueiaLivro(Livro it, int prazo) {
        return it.bloqueia(this, prazo);
    }

   
    public boolean desbloqueiaLivro(Livro it) {
        return it.desbloqueia(this);
    }

  
    @Override
    public int getCotaMaxima() {
        return 5; 
    }

 
    @Override
    public int getPrazoMaximo() {
        return 14; 
    }

    @Override
    public boolean isProfessor() {
        return true;
    }

 
    @Override
    public String toString() {
        return "Prof. " + getNome(); 
    }
}

	
