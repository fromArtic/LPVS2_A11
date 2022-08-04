package atividade.view;

/**
 *
 * @author Jv Loreti
 */

import atividade.model.Cadastro;
import atividade.model.CadastroDisciplina;
import atividade.model.CadastroEstado;
import javax.swing.table.AbstractTableModel;

public class ModeloTabelaCadastro extends AbstractTableModel{
    private Cadastro conteudoCadastros[];
    private String categoriaColunas[] = {"Nome", "Endereço", "Cidade", "Estado", "Sexo", "LPVS2", "POOS3", "DSIS4", "ID"}; //Define a categoria de cada coluna

    //Implementa o cabeçalho da tabela com a categoria de cada coluna
    @Override
    public String getColumnName(int column){
        return categoriaColunas[column];
    }
    
    //Retorna o número de linhas preenchidas
    @Override
    public int getRowCount(){
        int contador = 0;
        
        for(int i = 0; i < conteudoCadastros.length; i++){
            if(conteudoCadastros[i] != null){
                contador++;
            }
        }
        
        return contador;
    }

    //Retorna o número de colunas
    @Override
    public int getColumnCount(){
        return 9; //9 parâmetros: nome, endereço, cidade, estado, sexo, LPVS2, POOS3, DSIS4, ID
    }

    //Retorna o conteúdo da tabela
    @Override
    public Object getValueAt(int row, int col){
        Cadastro c = conteudoCadastros[row];
        
        if(c != null){
            switch(col){
                case 0: return c.getNome();
                case 1: return c.getEndereco();
                case 2: return c.getCidade();
                case 3: return c.getEstado();
                case 4: return c.getSexo();
                case 5: return c.isLPVS2();
                case 6: return c.isPOOS3();
                case 7: return c.isDSIS4();
                case 8: return c.getId();
            }
        }
        return null;
    }
    
    //Método para alterar informações já inseridas na tabela
    @Override
    public boolean isCellEditable(int row, int col){
        switch(col){
            case 0: return true; //Nome
            case 1: return true; //Endereço
            case 2: return true; //Cidade
            case 3: return true; //Estado
        }
        return false;
    }

    //Atribui as informações alteradas pelo método acima
    @Override
    public void setValueAt(Object aValue, int row, int col){
        long id = (long) this.getValueAt(row, 8);
        Cadastro c = null;
        
        for(int i = 0; i < conteudoCadastros.length; i++){
            if(conteudoCadastros[i] != null && conteudoCadastros[i].getId() == id){
                c = conteudoCadastros[i];
            }
        }
        
        switch(col){
             case 0:
                c.setNome((String) aValue);
                break;
            case 1:
                c.setEndereco((String) aValue);
                break;
             case 2:
                c.setCidade((String) aValue);
                break;
             case 3:
                c.setEstado((CadastroEstado) aValue);
                break;
            default:
                return;
        }
    }

    //Retorna a classe respectiva de cada coluna da tabela
    @Override
    public Class<?> getColumnClass(int col){
        switch(col){
            case 0: return String.class;
            case 1: return String.class;
            case 2: return String.class;
            case 3: return CadastroEstado.class;
            case 4: return String.class;
            case 5: return CadastroDisciplina.class;
            case 6: return CadastroDisciplina.class;
            case 7: return CadastroDisciplina.class;
            case 8: return long.class;
        }
        return null;
    }
    
    //Setter
    public void setConteudoCadastros(Cadastro[] c){
        this.conteudoCadastros = c;
    }
}
