package exemplo_swing.view;

/**
 *
 * @author Jv Loreti
 */

import exemplo_swing.model.Pessoa;
import exemplo_swing.model.PessoaSituacao;
import javax.swing.table.AbstractTableModel;

public class ModeloTabelaPessoa extends AbstractTableModel{
    private Pessoa conteudoPessoas[];
    private String categoriaColunas[] = {"ID", "Nome", "Ocupação", "Faixa etária", "Situação", "Estrangeiro?", "Passaporte", "Gênero"}; //Define a categoria de cada coluna

    //Implementa o cabeçalho da tabela com a categoria de cada coluna
    @Override
    public String getColumnName(int column){
        return categoriaColunas[column];
    }   
    
    //Retorna o número de linhas preenchidas
    @Override
    public int getRowCount(){
        int contador = 0;
        
        for(int i = 0; i < conteudoPessoas.length; i++){
            if(conteudoPessoas[i] != null){
                contador++;
            }
        }
        
        return contador;
    }

    //Retorna o número de colunas
    @Override
    public int getColumnCount(){
        return 8; //8 parâmetros: id, nome, ocupação, idade, situação, estrangeiro, passaporte e gênero
    }

    //Retorna o conteúdo da tabela
    @Override
    public Object getValueAt(int row, int col){
        Pessoa p = conteudoPessoas[row];
        
        if(p != null){
            switch(col){
                case 0: return p.getId();
                case 1: return p.getNome();
                case 2: return p.getOcupacaoEmprego();
                case 3: return p.getIdade();
                case 4: return p.getSituacaoEmprego();
                case 5: return p.isCheckEstrangeiro();
                case 6: return p.getPassaporte();
                case 7: return p.getGenero();
            }    
        }
        return null;
    }

    //Método para alterar informações já inseridas na tabela
    @Override
    public boolean isCellEditable(int row, int col){
        switch(col){
            case 1: return true; //Nome
            case 4: return true; //Situação
            case 5: return true; //Estrangeiro?
        }
        return false;
    }

    //Atribui as informações alteradas pelo método acima
    @Override
    public void setValueAt(Object aValue, int row, int col){
        long id = (long) this.getValueAt(row, 0);
        Pessoa p = null;
        
        for(int i = 0; i < conteudoPessoas.length; i++){
            if(conteudoPessoas[i] != null && conteudoPessoas[i].getId() == id){
                p = conteudoPessoas[i];
            }
        }
        
        switch(col){
            case 1:
                p.setNome((String) aValue);
                break;
            case 4:
                p.setSituacaoEmprego((PessoaSituacao) aValue);
                break;
            case 5:
                p.setCheckEstrangeiro((Boolean) aValue);
                if(!p.isCheckEstrangeiro()){ //Nulifica o passaporte caso altere "Estrangeiro?" para falso
                    p.setPassaporte("");
                }
                break;
            default:
                return;
        }
    }

    //Retorna a classe respectiva de cada coluna da tabela
    @Override
    public Class<?> getColumnClass(int col){
        switch(col){
            case 0: return long.class;
            case 1: return String.class;
            case 2: return String.class;
            case 3: return String.class;
            case 4: return PessoaSituacao.class;
            case 5: return Boolean.class;
            case 6: return String.class;
            case 7: return String.class;
        }
        return null;
    }
    
    //Setter
    public void setConteudoPessoas(Pessoa[] p){
        this.conteudoPessoas = p;
    }
}
