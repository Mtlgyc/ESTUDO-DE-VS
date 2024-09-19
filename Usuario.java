package Sistema;
import java.util.ArrayList;

public class Usuario {
    private String nome;
    private ArrayList<Livro> livrosRetirados; 
  
    public Usuario(String nome) {
        this.nome = nome; 
        this.livrosRetirados = new ArrayList<Livro>(); 
    }

  
    public int getCotaMaxima() {
        return 2; // Define a cota máxima de livros como 2
    }

    
    public int getPrazoMaximo() {
        return 4; 
    }

   
    public boolean isADevolver() {

        return (this.livrosRetirados.size() >= this.getCotaMaxima() || this.temPrazoVencido());
    }

  
    public boolean isAptoARetirar() {
        return (!this.isADevolver()); 
    }

   
    public boolean temPrazoVencido() {
        for (Livro livro : this.livrosRetirados) {
            if (livro.isEmAtraso()) {
                return true; 
            }
        }
        return false; 
    }

    
    public boolean retiraLivro(Livro it) {
        if (this.isAptoARetirar()) { 
            if (it.empresta(this)) { 
                this.livrosRetirados.add(it); 
                return true; 
            } else {
                return false;
            }
        } else {
            return false; 
        }
    }

  
    public boolean devolveLivro(Livro it) {
        if (it.retorna(this)) { 
            this.livrosRetirados.remove(it); 
            return true;
        } else {
            return false; 
        }
    }

 
    public boolean isProfessor() {
        return false; 
    }

    
    public String getNome() {
        return this.nome;
    }

    
    public String toString() {
        return "Usuario " + nome; 
    }

    
    public String listaCarga() {
        StringBuilder carga = new StringBuilder();
        carga.append(this.toString()) 
                .append(" Limite: ")
                .append(this.getCotaMaxima()) 
                .append(" Carga atual: ")
                .append(this.livrosRetirados.size()); 
        for (Livro livro : this.livrosRetirados) {
            carga.append(livro.toString()) 
                    .append("\n");
        }

        return carga.toString();
    }
}
