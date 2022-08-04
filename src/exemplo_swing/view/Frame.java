package exemplo_swing.view;

/**
 *
 * @author Jv Loreti
 */

import exemplo_swing.controller.ControllerPessoa;
import exemplo_swing.model.Pessoa;
import exemplo_swing.model.PessoaSituacao;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.WindowConstants;

public class Frame extends JFrame{
    MenuArquivo menu;
    AreaDeFormulario areaDeFormulario;
    AreaDeTabela areaDeTabela;
    ControllerPessoa cp;
    JFileChooser escolhaArquivo;
    
    public Frame(){
        //Define as dimensões
        super.setSize(1200, 500);
        
        //Define o layout
        super.setLayout(new BorderLayout());
        
        //Chama o método criarMenu
        super.setJMenuBar(criarMenu());
        
        //Inicializa o menu arquivo
        menu = new MenuArquivo();
        //Implementa e define a posição do menu arquivo
        super.add(menu, BorderLayout.PAGE_START);
        
        //Inicializa a área de formulário
        areaDeFormulario = new AreaDeFormulario();
        //Implementa e define a posição da área de formulário
        super.add(areaDeFormulario, BorderLayout.WEST);
        
        //Inicializa a área de tabela
        areaDeTabela = new AreaDeTabela();
        //Implementa e define a posição da área de tabela
        super.add(areaDeTabela, BorderLayout.CENTER);
        //Comunicação entre a tabela e a interface TabelaListener
        areaDeTabela.setTabelaListener(new TabelaListener(){
            @Override
            public void excluirPessoa(long id){ //Método para excluir pessoa da tabela
                boolean retorno = cp.excluirPessoa(id);
                if(retorno){ //Remoção sucedeu
                    JOptionPane.showMessageDialog(Frame.this, "Pessoa removida com sucesso.", "Excluir pessoa", JOptionPane.INFORMATION_MESSAGE);
                    areaDeTabela.atualizarTabela();
                }else{ //Remoção fracassou
                    JOptionPane.showMessageDialog(Frame.this, "Ocorreu um erro durante a remoção.", "Excluir pessoa", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
        //Inicializa ControllerPessoa (serve p/ comunicação com a classe Pessoa)
        cp = new ControllerPessoa();
        //Inicializa escolhaArquivo (serve p/ se utilizar o arquivo importado)
        escolhaArquivo = new JFileChooser();
        
        //Exportar (Frame -> ControllerPessoa -> Arquivo) e imprimir cadastro ao salvar
        areaDeFormulario.setFormularioListener(new FormularioListener(){
            @Override
            public void enviarFormulario(String n, String o, String i, PessoaSituacao s, boolean estr, String p, String g){
                cp.adicionarPessoa(n, o, i, s, estr, p, g); //Envia os parâmetros para ControllerPessoa
                areaDeTabela.atualizarTabela(); //Atualiza os parâmetros de impressão de cada coluna da tabela
            }
        });
        
        //Interação do menu arquivo com a área de tabela
        menu.setTextoListener(new TextoListener(){
            @Override
            public void enviarTexto(String t){
                switch(t){
                    case "Salvar":
                        if(escolhaArquivo.getSelectedFile() == null){ //Salvando o arquivo pela primeira vez
                            int ret = escolhaArquivo.showSaveDialog(Frame.this);
                            if(ret == JFileChooser.APPROVE_OPTION){
                                System.out.println(escolhaArquivo.getSelectedFile());
                                salvarPessoas();
                            }
                        }else{ //Salvando o arquivo pelas vezes subsequentes
                            salvarPessoas();
                        }
                        break;
                    case "Carregar":
                        if(escolhaArquivo.getSelectedFile() == null){ //Abrindo o arquivo pela primeira vez
                            int ret = escolhaArquivo.showOpenDialog(Frame.this);
                            if(ret == JFileChooser.APPROVE_OPTION){
                                System.out.println(escolhaArquivo.getSelectedFile());
                                carregarPessoas();
                            }
                        }else{ //Abrindo o arquivo pelas vezes subsequentes
                            carregarPessoas();
                        }
                        break; 
                }
            }
        });
        
        //Manipulação do conteúdo a ser exibido na tabela
        areaDeTabela.setConteudoPessoas(cp.getPessoas());
        
        //Exibe o JFrame
        super.setVisible(true);
        //Encerra a operação ao fechar o JFrame
        super.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
    
    //Configurações da barra de menu
    private JMenuBar criarMenu(){
        //Inicializa a barra de menu
        JMenuBar barraDeMenu = new JMenuBar();
        
        //Registro das opções do menu Arquivo
        JMenu menuArquivo = new JMenu("Arquivo");
        JMenuItem importarArquivo = new JMenuItem("Importar arquivo ...");
        JMenuItem exportarArquivo = new JMenuItem("Exportar arquivo ...");
        JMenuItem sair = new JMenuItem("Sair");
        
        //Implementação do menu Arquivo
        barraDeMenu.add(menuArquivo);
        menuArquivo.add(importarArquivo);
        menuArquivo.add(exportarArquivo);
        menuArquivo.addSeparator(); //Linha de separação
        menuArquivo.add(sair);
        
        //Registro das opções do menu Janela
        JMenu menuJanela = new JMenu("Janela");
        JMenu exibir = new JMenu("Exibir");
        
        //Implementação do menu Janela
        barraDeMenu.add(menuJanela);
        menuJanela.add(exibir);
        
        //Importar arquivo
        importarArquivo.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                int ret = escolhaArquivo.showOpenDialog(Frame.this);
                
                if(ret == JFileChooser.APPROVE_OPTION){
                    System.out.println(escolhaArquivo.getSelectedFile());
                    carregarPessoas();
                }
            }
        });
        
        //Exportar arquivo
        exportarArquivo.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                int ret = escolhaArquivo.showSaveDialog(Frame.this);
                
                if(ret == JFileChooser.APPROVE_OPTION){
                    System.out.println(escolhaArquivo.getSelectedFile());
                    salvarPessoas();
                }
            }
        });
        
        //Confirmação de encerramento do programa
        sair.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                int ret = JOptionPane.showConfirmDialog(Frame.this, "Realmente deseja sair?", "Confirmação de saída", JOptionPane.OK_CANCEL_OPTION);
                
                if(ret == JOptionPane.OK_OPTION){
                    System.exit(0);
                }
            }
        });
        
        //Exibir ou ocultar a área de formulário
        JCheckBoxMenuItem chkFormulario = new JCheckBoxMenuItem("Formulário");
        exibir.add(chkFormulario);
        chkFormulario.setSelected(true);
        chkFormulario.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                JCheckBoxMenuItem chk = (JCheckBoxMenuItem) e.getSource();
                areaDeFormulario.setVisible(chk.isSelected());
            }
        });
        
        //Teclas de atalho
        menuArquivo.setMnemonic(KeyEvent.VK_A); //Alt + A: abrir o menu Arquivo
        sair.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK)); //Ctrl + X: encerrar o programa
        chkFormulario.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK)); //Ctrl + E: exibir ou ocultar a área de formulário
        
        return barraDeMenu;
    }
    
    //Abrir arquivo
    private void carregarPessoas(){
        try{
            Pessoa[] p = cp.carregarArquivo(escolhaArquivo.getSelectedFile().getAbsolutePath());
            areaDeTabela.setConteudoPessoas(p);
            areaDeTabela.atualizarTabela();
        }catch(FileNotFoundException ex){
            JOptionPane.showMessageDialog(Frame.this, "Não foi possível carregar o arquivo.", "Erro ao carregar arquivo", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    //Salvar arquivo
    private void salvarPessoas(){
        try{
            cp.salvarArquivo(escolhaArquivo.getSelectedFile().getAbsolutePath());
        }catch(FileNotFoundException ex){
            JOptionPane.showMessageDialog(Frame.this, "Não foi possível salvar o arquivo.", "Erro ao salvar arquivo", JOptionPane.ERROR_MESSAGE);
        }
    }
}
