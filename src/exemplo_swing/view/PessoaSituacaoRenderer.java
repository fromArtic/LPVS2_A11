package exemplo_swing.view;

/**
 *
 * @author Jv Loreti
 */

import exemplo_swing.model.PessoaSituacao;
import java.awt.Component;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class PessoaSituacaoRenderer implements TableCellRenderer{
    private JComboBox combo;
    
    public PessoaSituacaoRenderer(){
        combo = new JComboBox(PessoaSituacao.values());
    }

    //Retorna o componente JComboBox junto ao respectivo valor carregado na tabela
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int col){
        combo.setSelectedItem(value);
        return combo;
    }
}
