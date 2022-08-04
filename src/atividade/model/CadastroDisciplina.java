package atividade.model;

/**
 *
 * @author Jv Loreti
 */

public enum CadastroDisciplina{
    LPVS2("LPVS2"),
    POOS3("POOS3"),
    DSIS4("DSIS4");
    
    private String texto;
    
    private CadastroDisciplina(String t){
        this.texto = t;
    }
    
    public String toString(){
        return this.texto;
    }
}
