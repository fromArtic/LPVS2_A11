package atividade.model;

/**
 *
 * @author Jv Loreti
 */

public enum CadastroEstado{
    AL("AL"),
    CE("CE"),
    MG("MG"),
    RJ("RJ"),
    SP("SP"),
    DF("DF");
    
    private String texto;
    
    private CadastroEstado(String t){
        this.texto = t;
    }
    
    public String toString(){
        return this.texto;
    }
}
