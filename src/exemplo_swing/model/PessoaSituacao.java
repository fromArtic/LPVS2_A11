package exemplo_swing.model;

/**
 *
 * @author Jv Loreti
 */

public enum PessoaSituacao{
    EMPREGADO("Empregado"),
    AUTONOMO("Autônomo"),
    DESEMPREGADO("Desempregado"),
    OUTROS("Outros");
    
    private String texto;
    
    private PessoaSituacao(String t){
        this.texto = t;
    }
    
    public String toString(){
        return this.texto;
    }
}
