package exemplo_swing.controller;

/**
 *
 * @author Jv Loreti
 */

import exemplo_swing.model.Arquivo;
import exemplo_swing.model.Pessoa;
import exemplo_swing.model.PessoaSituacao;
import java.io.FileNotFoundException;

public class ControllerPessoa{
    Arquivo a;
    
    public ControllerPessoa(){
        a = new Arquivo();
    }
    
    //Adiciona pessoa ao vetor ao enviar formulário (comunicação com classe arquivo)
    public boolean adicionarPessoa(String n, String o, String i, PessoaSituacao s, boolean estr, String p, String g){
        long id = System.currentTimeMillis();
        Pessoa pessoa = new Pessoa(id, n, o, i, s, estr, p, g);
        a.adicionarPessoa(pessoa);
        
        System.out.println("Pessoa adicionada.");
        return true;
    }
    
    //Método para exportar arquivo (comunicação com classe arquivo)
    public boolean salvarArquivo(String nomeArquivo) throws FileNotFoundException{
        a.salvarArquivo(nomeArquivo);
        return true;
    }
    
    //Método para importar arquivo (comunicação com classe arquivo)
    public Pessoa[] carregarArquivo(String nomeArquivo) throws FileNotFoundException{
        return a.carregarArquivo(nomeArquivo);
    }
    
    //Método para excluir pessoa da tabela (comunicação com classe arquivo)
    public boolean excluirPessoa(long id){
        return a.excluirPessoa(id);
    }
    
    //Recupera pessoas através do Getter da classe Arquivo
    public Pessoa[] getPessoas(){
        return a.getPessoas();
    }
}
