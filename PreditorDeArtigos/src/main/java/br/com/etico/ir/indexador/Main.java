package br.com.etico.ir.indexador;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import br.com.etico.ir.utils.Termo;

public class Main extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldBusca;
	private JTextField textFieldArtigo1;
	private JTextField textFieldArtigo2;
	private JTextArea textAreaArtigo;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Main() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 441);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(6, 6, 519, 74);
		contentPane.add(panel);
		panel.setLayout(null);
		
		textFieldBusca = new JTextField();
		textFieldBusca.setBounds(6, 47, 507, 23);
		panel.add(textFieldBusca);
		textFieldBusca.setColumns(10);
		
		JLabel lblBusca = new JLabel("Busca");
		lblBusca.setBounds(6, 19, 61, 16);
		panel.add(lblBusca);
		
		JLabel lblArtigo = new JLabel("Artigo 1");
		lblArtigo.setBounds(16, 92, 61, 16);
		contentPane.add(lblArtigo);
		
		textFieldArtigo1 = new JTextField();
		textFieldArtigo1.setBounds(6, 111, 253, 29);
		contentPane.add(textFieldArtigo1);
		textFieldArtigo1.setColumns(10);
		
		textFieldArtigo2 = new JTextField();
		textFieldArtigo2.setBounds(271, 111, 254, 29);
		contentPane.add(textFieldArtigo2);
		textFieldArtigo2.setColumns(10);
		
		JLabel lblArtigo_1 = new JLabel("Artigo 2");
		lblArtigo_1.setBounds(282, 92, 61, 16);
		contentPane.add(lblArtigo_1);
		
		JButton btnExecutarAnalise = new JButton("Executar analise");
		btnExecutarAnalise.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String[] colunas = new String[]{"","",""};
				String[][] dados = new String[][]{};
				
				table.setModel(new DefaultTableModel(dados,colunas));
				
				String pesquisa = textFieldBusca.getText();
				
				if (pesquisa == null || pesquisa.trim().isEmpty()) {
					JOptionPane.showMessageDialog(null,"Digite a pesquisa");
					return;
				}
				
				TituloDocumento tituloDocumento = new TituloDocumento(pesquisa);
				Set<Termo> termos =  tituloDocumento.getTokens();
				
				Document doc1 = null;
				Document doc2 = null;
				try {
					
					if (!textFieldArtigo1.getText().trim().isEmpty()) {
						doc1 = Jsoup.connect(textFieldArtigo1.getText()).get();
					} 
					
					if (!textFieldArtigo2.getText().trim().isEmpty()) {
						doc2 = Jsoup.connect(textFieldArtigo2.getText()).get();
					}
					
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				Documento documento1 = null;
				Documento documento2 = null;
				if (doc1 != null) {
					documento1 = new Documento(doc1.getElementsByTag("article").text(),"");
				} else {
					if (textAreaArtigo.getText().toString().length() > 0) {
						documento1 = new Documento(textAreaArtigo.getText().toString(),"");
					}
				}
				
				if (doc2 != null) {
					documento2 = new Documento(doc2.getElementsByTag("article").text(),"");
				}
				
				DefaultTableModel dtm =  (DefaultTableModel)table.getModel();
				dtm.addRow(new Object[]{"TERMO","TF-DOC1","TF-DOC2"});
				
				double soma1 = 0;
				double soma2 = 0;
				
				for (Termo termoPesquisa: termos) {
					
					double peso1 = 0;
					if (documento1 != null && documento1.getTermo(termoPesquisa) != null) {
						peso1 = (documento1.getTermo(termoPesquisa).getFrequenciaDoTermoTF() * 100l);
					}
					
					double peso2 = 0;
					if (documento2 != null && documento2.getTermo(termoPesquisa) != null) {
						peso2 = (documento2.getTermo(termoPesquisa).getFrequenciaDoTermoTF() * 100l);	
					}
					
					dtm.addRow(new Object[]{termoPesquisa.getTermo(),peso1,peso2});
					
					soma1 += peso1;  
					soma2 += peso2;  
					
				}
				
				dtm.addRow(new Object[]{"--------","--------","--------"});
				dtm.addRow(new Object[]{"TOTAL TF",soma1,soma2});
				
				if (soma1 > 0 && soma2 >0) {
					dtm.addRow(new Object[]{"QTD PALAVRAS",documento1.getTotalPalavras(),documento2.getTotalPalavras()});
					dtm.addRow(new Object[]{"DIVISÃO",((soma1 / documento1.getTotalPalavras()) * 100),(soma2 / documento2.getTotalPalavras() * 100)});
				}
				
				if (soma1 > 0) {
					dtm.addRow(new Object[]{"QTD PALAVRAS",documento1.getTotalPalavras(),"0000"});
					dtm.addRow(new Object[]{"DIVISÃO",((soma1 / documento1.getTotalPalavras()) * 100),"0000"});
				}
				
				if (soma2 > 0) {
					dtm.addRow(new Object[]{"QTD PALAVRAS","0000",documento1.getTotalPalavras()});
					dtm.addRow(new Object[]{"DIVISÃO","0000",(soma2 / documento2.getTotalPalavras() * 100)});
				}
				
			}
		});
		btnExecutarAnalise.setBounds(163, 149, 253, 29);
		contentPane.add(btnExecutarAnalise);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setToolTipText("Resultado");
		tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		tabbedPane.setBounds(6, 190, 519, 212);
		contentPane.add(tabbedPane);
		
		table = new JTable();
		tabbedPane.addTab("New tab", null, table, null);
		
		textAreaArtigo = new JTextArea();
		textAreaArtigo.setBounds(537, 6, 357, 396);
		contentPane.add(textAreaArtigo);
	}
}
