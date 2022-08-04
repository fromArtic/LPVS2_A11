package atividade.view;

/**
 *
 * @author Jv Loreti
 */

import atividade.model.CadastroEstado;

public interface FormularioListener{
    public void enviarFormulario(String nome, String endereco, String cidade, CadastroEstado estado, 
                                 String sexo, boolean LPVS2, boolean POOS3, boolean DSIS4);
}
