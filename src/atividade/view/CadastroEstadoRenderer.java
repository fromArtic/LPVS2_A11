package atividade.view;

/**
 *
 * @author Jv Loreti
 */

import atividade.model.CadastroEstado;
import java.awt.Component;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class CadastroEstadoRenderer implements TableCellRenderer{
    private JComboBox combo;
    
    public CadastroEstadoRenderer(){
        combo = new JComboBox(CadastroEstado.values());
    }

    //Retorna o componente JComboBox junto ao respectivo valor carregado na tabela
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int col){
        combo.setSelectedItem(value);
        return combo;
    }
}
