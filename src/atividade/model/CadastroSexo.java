package atividade.model;

/**
 *
 * @author Jv Loreti
 */

public enum CadastroSexo{
    MASCULINO("Masculino"),
    FEMININO("Feminino");
    
    private String texto;
    
    private CadastroSexo(String t){
        this.texto = t;
    }
    
    public String toString(){
        return this.texto;
    }
}