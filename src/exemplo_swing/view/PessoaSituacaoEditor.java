package exemplo_swing.view;

/**
 *
 * @author Jv Loreti
 */

import exemplo_swing.model.PessoaSituacao;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EventObject;
import javax.swing.AbstractCellEditor;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

public class PessoaSituacaoEditor extends AbstractCellEditor implements TableCellEditor{
    private JComboBox combo;
    
    public PessoaSituacaoEditor(){
        combo = new JComboBox(PessoaSituacao.values());
    }

    //Recupera o valor contido na JComboBox
    @Override
    public Object getCellEditorValue(){
        return combo.getSelectedItem();
    }

    //Permite a alteração do valor contido na JComboBox
    @Override
    public boolean isCellEditable(EventObject e){
        return true;
    }
    
    //Retorna o valor contido na JComboBox
    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int col){
        combo.setSelectedItem(value);
        
        //Impede a alteração parcial dos dados
        combo.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                fireEditingStopped();
            }
        });
        
        return combo;
    }
}
