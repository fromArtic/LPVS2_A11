package atividade.view;

/**
 *
 * @author Jv Loreti
 */

import atividade.controller.ControllerCadastro;
import atividade.model.Cadastro;
import atividade.model.CadastroEstado;
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
    //AreaDeTexto areaDeTexto;
    ControllerCadastro cd;
    JFileChooser escolhaArquivo;
    
    public Frame(){
        //Define as dimensões
        super.setSize(1700, 500);
        
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
            public void excluirCadastro(long id){ //Método para excluir cadastro da tabela
                boolean retorno = cd.excluirCadastro(id);
                if(retorno){ //Remoção sucedeu
                    JOptionPane.showMessageDialog(Frame.this, "Cadastro removido com sucesso.", "Excluir cadsatro", JOptionPane.INFORMATION_MESSAGE);
                    areaDeTabela.atualizarTabela();
                }else{ //Remoção fracassou
                    JOptionPane.showMessageDialog(Frame.this, "Ocorreu um erro durante a remoção.", "Excluir cadastro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
        //Inicializa a área de texto
        //areaDeTexto = new AreaDeTexto();
        //Implementa e define a posição da área de texto
        //super.add(areaDeTexto, BorderLayout.EAST);
        
        //Inicializa ControllerCadastro (serve p/ comunicação com a classe Cadastro)
        cd = new ControllerCadastro();
        //Inicializa escolhaArquivo (serve p/ se utilizar o arquivo importado)
        escolhaArquivo = new JFileChooser();
        
        //Exportar (Frame -> ControllerCadastro -> Arquivo) e imprimir cadastro ao salvar
        areaDeFormulario.setFormularioListener(new FormularioListener(){
            @Override
            public void enviarFormulario(String nome, String endereco, String cidade, CadastroEstado estado, String sexo, boolean LPVS2, boolean POOS3, boolean DSIS4){
                //if(!nome.isEmpty() && !endereco.isEmpty() && !cidade.isEmpty() && !estado.isEmpty() && !sexo.isEmpty()){ //Valida se os campos do formulário não estão vazios
                    cd.adicionarCadastro(nome, endereco, cidade, estado, sexo, LPVS2, POOS3, DSIS4); //Envia os parâmetros para ControllerCadastro
                    areaDeTabela.atualizarTabela(); //Atualiza os parâmetros de impressão de cada coluna da tabela
                    /*areaDeTexto.escreverTexto("Nome: " + nome + "\n" +
                                           "Endereço: " + endereco + "\n" +
                                           "Cidade: " +  cidade +
                                           ", " + estado + "\n" +
                                           "Sexo: " + sexo);
                                            if(LPVS2 == true){
                                                areaDeTexto.escreverTexto("\nMatriculado(a) em LPVS2.");
                                            }
                                            if(POOS3 == true){
                                                areaDeTexto.escreverTexto("\nMatriculado(a) em POOS3.");
                                            }
                                            if(DSIS4 == true){
                                                areaDeTexto.escreverTexto("\nMatriculado(a) em DSIS4.");
                                            }
                                            areaDeTexto.escreverTexto("\n\n");*/
                //}
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
                                salvarCadastros();
                            }
                        }else{ //Salvando pelas vezes subsequentes
                            salvarCadastros();
                        }
                        break;
                    case "Carregar":
                        if(escolhaArquivo.getSelectedFile() == null){ //Abrindo o arquivo pela primeira vez
                            int ret = escolhaArquivo.showOpenDialog(Frame.this);
                            if(ret == JFileChooser.APPROVE_OPTION){
                                System.out.println(escolhaArquivo.getSelectedFile());
                                carregarCadastros();
                            }
                        }else{ //Abrindo pelas vezes subsequentes
                            carregarCadastros();
                        }
                        break; 
                }
            }
        });
        
        //Manipulação do conteúdo a ser exibido na tabela
        areaDeTabela.setConteudoCadastros(cd.getCadastros());
        
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
        
        //Registro das opções do menu Tabela
        JMenu menuTabela = new JMenu("Tabela");
        JMenu exibir = new JMenu("Exibir");
        
        //Implementação do menu Tabela
        barraDeMenu.add(menuTabela);
        menuTabela.add(exibir);
        
        //Importar arquivo
        importarArquivo.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                int ret = escolhaArquivo.showOpenDialog(Frame.this);
                
                if(ret == JFileChooser.APPROVE_OPTION){
                    System.out.println(escolhaArquivo.getSelectedFile());
                    carregarCadastros();
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
                    salvarCadastros();
                }
            }
        });
        
        //Confirmação de encerramento do programa
        sair.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                int ret = JOptionPane.showConfirmDialog(Frame.this, "Encerrar operação?", "Confirmação de encerramento", JOptionPane.YES_NO_OPTION);
                
                if(ret == JOptionPane.YES_OPTION){
                    System.exit(0);
                }
            }
        });
        
        //Exibir ou ocultar a área de tabela
        JCheckBoxMenuItem chkTabela = new JCheckBoxMenuItem();
        exibir.add(chkTabela);
        chkTabela.setSelected(true);
        chkTabela.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                JCheckBoxMenuItem chk = (JCheckBoxMenuItem) e.getSource();
                areaDeTabela.setVisible(chk.isSelected());
            }
        });
        
        //Teclas de atalho
        menuArquivo.setMnemonic(KeyEvent.VK_A); //Alt + A: abrir o menu Arquivo
        importarArquivo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, ActionEvent.CTRL_MASK)); //Ctrl + I: importar arquivo
        exportarArquivo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK)); //Ctrl + E: exportar arquivo
        sair.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK)); //Ctrl + X: encerrar o programa
        chkTabela.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK)); //Ctrl + O: exibir ou ocultar a tabela
        
        return barraDeMenu;
    }
    
    //Abrir arquivo
    private void carregarCadastros(){
        try{
            Cadastro[] c = cd.carregarArquivo(escolhaArquivo.getSelectedFile().getAbsolutePath());
            areaDeTabela.setConteudoCadastros(c);
            areaDeTabela.atualizarTabela();
        }catch(FileNotFoundException ex){
            JOptionPane.showMessageDialog(Frame.this, "Não foi possível carregar o arquivo.", "Erro ao carregar arquivo", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    //Salvar arquivo
    private void salvarCadastros(){
        try{
            cd.salvarArquivo(escolhaArquivo.getSelectedFile().getAbsolutePath());
        }catch(FileNotFoundException ex){
            JOptionPane.showMessageDialog(Frame.this, "Não foi possível salvar o arquivo.", "Erro ao salvar arquivo", JOptionPane.ERROR_MESSAGE);
        }
    }
}
