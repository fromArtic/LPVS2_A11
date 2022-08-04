package exemplo_swing.view;

/**
 *
 * @author Jv Loreti
 */

import exemplo_swing.model.PessoaSituacao;

public interface FormularioListener{
    /**
     * 
     * @param n Nome
     * @param o Ocupação
     * @param i Idade
     * @param s Situação
     * @param estr Estrangeiro
     * @param p Passaporte
     * @param g Gênero
     */
    public void enviarFormulario(String n, String o, String i, PessoaSituacao s, boolean estr, String p, String g);
}
