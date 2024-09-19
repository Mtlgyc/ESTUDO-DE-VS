package Sistema;

public class Periodico extends Livro {

    public Periodico(String tit) {
        super(tit); 
    }


    @Override
    public boolean empresta(Usuario u) {
    
        if (u.isProfessor()) {
  
            return super.empresta(u);
        } else {
   
            return false;
        }
    }
}
