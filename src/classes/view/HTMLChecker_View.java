package classes.view;
import java.awt.EventQueue;
import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;

import classes.HTMLChecker;
import classes.listaEncadeada.ListaEncadeada;
import classes.listaEncadeada.NoLista;

import javax.swing.JTextPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.ListSelectionModel;
import java.awt.ScrollPane;

public class HTMLChecker_View {

	private JFrame frame;
	private JTextField edArquivo;
	private JTable tbTabela;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HTMLChecker_View window = new HTMLChecker_View();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public HTMLChecker_View() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 444);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lbArquivo = new JLabel("Arquivo:");
		
		edArquivo = new JTextField();
		edArquivo.setColumns(10);
		
		JTextPane mmRetorno = new JTextPane();
		mmRetorno.setEditable(false);
		
		DefaultTableModel modelo = new DefaultTableModel();
		modelo.addColumn("Tag");
		modelo.addColumn("Número de ocorrências");	
		
		tbTabela = new JTable(modelo);
		
		
		
       DefaultTableCellRenderer renderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (row % 2 == 0) { // Define o estilo para linhas pares
                    component.setBackground(Color.WHITE);
                } else { // Define o estilo para linhas ímpares
                    component.setBackground(Color.LIGHT_GRAY);
                }
                return component;
            }
        };
        
        tbTabela.setDefaultRenderer(Object.class, renderer);
		
        JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(tbTabela);
        
		JButton btAnalisar = new JButton("Analisar");
		btAnalisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					modelo.setNumRows(0);
					mmRetorno.setText("");
					HTMLChecker html = new HTMLChecker(edArquivo.getText());
					mmRetorno.setText(html.check());
					ListaEncadeada<String> tags = html.getTags();
					NoLista<String> noLista = tags.getPrimeiro();
					String tag;
					String qtd;
					
					while ((noLista != tags.getUltimo()) &&
						   (noLista !=  null)) {
						tag = noLista.getInfo();
						qtd = noLista.getProximo().getInfo();
						
						modelo.addRow(new Object[] {tag, qtd});
						
						
						noLista = noLista.getProximo();
						noLista = noLista.getProximo();
					}
					
					
					
					
				} catch (IOException e1) {
					e1.printStackTrace();
					mmRetorno.setText("Arquivo não encontrado!");
				}
			}
		});
		
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(mmRetorno, GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE)
						.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
							.addComponent(lbArquivo)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(edArquivo, GroupLayout.DEFAULT_SIZE, 292, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btAnalisar))
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lbArquivo)
						.addComponent(edArquivo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btAnalisar))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(mmRetorno, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 212, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(13, Short.MAX_VALUE))
		);
		
		frame.getContentPane().setLayout(groupLayout);
	}
}

