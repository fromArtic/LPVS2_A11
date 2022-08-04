package atividade.controller;

/**
 *
 * @author Jv Loreti
 */

import atividade.model.Arquivo;
import atividade.model.Cadastro;
import atividade.model.CadastroEstado;
import java.io.FileNotFoundException;

public class ControllerCadastro{
    Arquivo a;
    
    public ControllerCadastro(){
        a = new Arquivo();
    }
    
    //Adiciona cadastro ao vetor ao enviar formulário (comunicação com classe arquivo)
    public boolean adicionarCadastro(String nome, String endereco, String cidade, CadastroEstado estado, 
                                    String sexo, boolean LPVS2, boolean POOS3, boolean DSIS4){
        
        long id = System.currentTimeMillis();
        Cadastro cadastro = new Cadastro(nome, endereco, cidade, estado, sexo, LPVS2, POOS3, DSIS4, id);
        a.adicionarCadastro(cadastro);
        
        System.out.println("Cadastro efetuado.");
        return true;
    }
    
    //Método para exportar arquivo (comunicação com classe arquivo)
    public boolean salvarArquivo(String nomeArquivo) throws FileNotFoundException{
        a.salvarArquivo(nomeArquivo);
        return true;
    }
    
    //Método para importar arquivo (comunicação com classe arquivo)
    public Cadastro[] carregarArquivo(String nomeArquivo) throws FileNotFoundException{
        return a.carregarArquivo(nomeArquivo);
    }
    
    //Método para excluir cadastro da tabela (comunicação com classe arquivo)
    public boolean excluirCadastro(long id){
        return a.excluirCadastro(id);
    }
    
    //Recupera cadastros através do Getter da classe Arquivo
    public Cadastro[] getCadastros(){
        return a.getCadastros();
    }
}
