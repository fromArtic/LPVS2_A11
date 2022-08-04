package exemplo_swing.model;

/**
 *
 * @author Jv Loreti
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Arquivo{
    private int MAX = 100; //Máximo de registros salvos
    private Pessoa[] pessoasArquivo;
    
    public Arquivo(){
        this.pessoasArquivo = new Pessoa[MAX];
    }
    
    //Adiciona cadastro ao vetor ao enviar formulário
    public boolean adicionarPessoa(Pessoa p){
        for(int i = 0; i < MAX; i++){
            if(pessoasArquivo[i] == null){
                pessoasArquivo[i] = p;
                return true;
            }
        }
        return false;
    }
    
    //Método para exportar arquivo
    public boolean salvarArquivo(String nomeArquivo) throws FileNotFoundException{
        File f = new File(nomeArquivo);
        PrintWriter pw = new PrintWriter(f);
        
        for(int i = 0; i < MAX; i++){
            if(pessoasArquivo[i] != null){
                System.out.println(pessoasArquivo[i].toString());
                pw.println(pessoasArquivo[i].toString());
            }
        }
        
        pw.close();
        return true;
    }
    
    //Método para importar arquivo
    public Pessoa[] carregarArquivo(String nomeArquivo) throws FileNotFoundException{
        File f = new File(nomeArquivo);
        Scanner sc = new Scanner(f);
        
        this.pessoasArquivo = new Pessoa[MAX];
        
        while(sc.hasNextLine()){
            String linha = sc.nextLine();
            String[] pessoaArquivo = linha.split(";");
            
            Pessoa p = new Pessoa(Long.parseLong(pessoaArquivo[0]), 
                    pessoaArquivo[1], 
                    pessoaArquivo[2], 
                    pessoaArquivo[3],
                    PessoaSituacao.valueOf(pessoaArquivo[4]), 
                    Boolean.parseBoolean(pessoaArquivo[5]),
                    pessoaArquivo[6],
                    pessoaArquivo[7]);
            
            this.adicionarPessoa(p);
        }
        
        return pessoasArquivo;
    }
    
    //Método para excluir pessoa da tabela
    public boolean excluirPessoa(long id){
        for(int i = 0; i < MAX; i++){
            if(pessoasArquivo[i] != null){
                if(pessoasArquivo[i].getId() == id){
                    pessoasArquivo[i] = null;
                    return true;
                }
            }
        }
        return false;
    }
    
    //Getter
    public Pessoa[] getPessoas(){
        return this.pessoasArquivo;
    }
}
