package exemplo_swing.view;

/**
 *
 * @author Jv Loreti
 */

import exemplo_swing.model.Pessoa;
import exemplo_swing.model.PessoaSituacao;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class AreaDeTabela extends JPanel{
    JTable tabela;
    ModeloTabelaPessoa mtp;
    private JPopupMenu popup;
    private TabelaListener tl;
    
    public AreaDeTabela(){
        mtp = new ModeloTabelaPessoa();
        
        //Inicializa a área de tabela
        tabela = new JTable(mtp);
        //Implementa a área de tabela
        setLayout(new BorderLayout());
        add(new JScrollPane(tabela));
        //Determina a altura dos campos exibidos na tabela
        tabela.setRowHeight(25);
        
        //Renderiza o componente JComboBox na tabela
        tabela.setDefaultRenderer(PessoaSituacao.class, new PessoaSituacaoRenderer());
        //Permite a alteração do valor contido na JComboBox da tabela
        tabela.setDefaultEditor(PessoaSituacao.class, new PessoaSituacaoEditor());
        
        //Implementa o menu pop up
        popup = new JPopupMenu();
        JMenuItem itemExcluir = new JMenuItem("Excluir");
        popup.add(itemExcluir);
        
        //Reconhecimento do ponteiro do mouse
        tabela.addMouseListener(new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent e){
                int row = tabela.rowAtPoint(e.getPoint());
                tabela.getSelectionModel().addSelectionInterval(row, row);
                
                //Identifica qual botão do mouse foi pressionado
                if(e.getButton() == MouseEvent.BUTTON3){ //Botão direito
                    popup.show(tabela, e.getX(), e.getY()); //Exibe o menu pop up
                }
            }
        });
        
        //Implementa o método de exclusão do item "Excluir" do menu pop up
        itemExcluir.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                int row = tabela.getSelectedRow(); //Identifica a linha selecionada
                long id = (long) tabela.getValueAt(row, 0); //Recupera o respectivo ID da linha selecionada 
                tl.excluirPessoa(id); //Retorna o ID da pessoa a ser excluída
            }
        });
    }
    
    //Manipulação do conteúdo a ser exibido na tabela
    public void setConteudoPessoas(Pessoa p[]){
        mtp.setConteudoPessoas(p);
    }
    
    //Atualiza os parâmetros de impressão de cada coluna da tabela
    public void atualizarTabela(){
        mtp.fireTableDataChanged();
    }
    
    //Comunicação com a interface TabelaListener
    public void setTabelaListener(TabelaListener t){
        this.tl = t;
    }
}
