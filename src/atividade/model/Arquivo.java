package atividade.model;

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
    private Cadastro[] cadastrosArquivo;
    
    public Arquivo(){
        this.cadastrosArquivo = new Cadastro[MAX];
    }
    
    //Adiciona cadastro ao vetor ao enviar formulário
    public boolean adicionarCadastro(Cadastro c){
        for(int i = 0; i < MAX; i++){
            if(cadastrosArquivo[i] == null){
                cadastrosArquivo[i] = c;
                return true;
            }
        }
        return false;
    }
    
    //Método para exportar arquivo
    public boolean salvarArquivo(String nomeArquivo) throws FileNotFoundException{
        File f = new File(nomeArquivo + ".csv"); //Nome do arquivo + extensão
        PrintWriter pw = new PrintWriter(f);
        
        for(int i = 0; i < MAX; i++){
            if(cadastrosArquivo[i] != null){
                System.out.println(cadastrosArquivo[i].toString());
                pw.println(cadastrosArquivo[i].toString());
            }
        }
        
        pw.close();
        return true;
    }
    
    //Método para importar arquivo
    public Cadastro[] carregarArquivo(String nomeArquivo) throws FileNotFoundException{
        File f = new File(nomeArquivo);
        Scanner sc = new Scanner(f);
        
        this.cadastrosArquivo = new Cadastro[MAX];
        
        while(sc.hasNextLine()){
            String linha = sc.nextLine();
            String[] cadastroArquivo = linha.split(";");
            
            Cadastro c = new Cadastro(cadastroArquivo[0], //Nome
                    cadastroArquivo[1], //Endereço
                    cadastroArquivo[2], //Cidade
                    CadastroEstado.valueOf(cadastroArquivo[3]), //Estado
                    cadastroArquivo[4], //Sexo
                    Boolean.parseBoolean(cadastroArquivo[5]), //LPVS2
                    Boolean.parseBoolean(cadastroArquivo[6]), //POOS3
                    Boolean.parseBoolean(cadastroArquivo[7]), //DSIS4
                    Long.parseLong(cadastroArquivo[8])); //ID
            
            this.adicionarCadastro(c);
        }
        
        return cadastrosArquivo;
    }
    
    //Método para excluir cadastro da tabela
    public boolean excluirCadastro(long id){
        for(int i = 0; i < MAX; i++){
            if(cadastrosArquivo[i] != null){
                if(cadastrosArquivo[i].getId() == id){
                    cadastrosArquivo[i] = null;
                    return true;
                }
            }
        }
        return false;
    }
    
    //Getter
    public Cadastro[] getCadastros(){
        return this.cadastrosArquivo;
    }
}
