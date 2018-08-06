package br.com.etico.ir.utils;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import br.com.etico.ir.indexador.Documento;
import br.com.etico.ir.indexador.Serealizador;
import br.com.etico.ir.preditor.Preditor;
import br.com.etico.modelo.beans.Postagem;

public class Tela extends JFrame {

	private JPanel contentPane;
	private JTextField textSite;
	private JTextField textEsquerda;
	private JLabel lblEsporte;
	private JTextField textDireita;
	private JLabel lblEconomia;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tela frame = new Tela();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	Postagem postagem = null;

	/**
	 * Create the frame.
	 */
	public Tela() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		textSite = new JTextField();
		textSite.setBounds(17, 41, 412, 29);
		contentPane.add(textSite);
		textSite.setColumns(10);

		JButton btnClassificar = new JButton("Classificar");
		btnClassificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				long tempoInicial = System.currentTimeMillis();
				
				// FAZ O DOWNLOAD DO SITE
				Document doc2 = null;
				try {
					doc2 = Jsoup.connect(textSite.getText().trim()).get();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				String artigo = doc2.getElementsByTag("article").text();
				
				
				Map<Long, Documento> documentos = new HashMap<>();
				documentos.put(1l, new Documento(artigo,""));
				
				Documento.termosUnicosDaBase = (Set<Termo>) Serealizador.obterObjeto();
				String[][] vetor = new String[documentos.size()][Documento.termosUnicosDaBase.size() + 1];

				String tmp = "@relation baseNO\n\n";
				
				int coluna = 0;
				StringBuilder builder = new StringBuilder();
				
				//persiste a base de termas
//				Serealizador.gravarObjeto(Documento.termosUnicosDaBase);
				
				//percorre toda base de termos
				for (Termo termoBase : Documento.termosUnicosDaBase) {

					int linha = 0;
					
					tmp += "@attribute " + termoBase.getTermo() + " {S,N} \n";
					
					for (long key : documentos.keySet()) {

						Documento documento = documentos.get(key);

						if (documento.getTokens().contains(termoBase)) {
							vetor[linha][coluna] = String.valueOf(documento.getTermo(termoBase).getQtdTermo());
						} else {
							vetor[linha][coluna] = "0";
						}
						
						vetor[linha][Documento.termosUnicosDaBase.size()] = documento.getCategoria();
						linha++;
					}
					
					coluna++;
					
				}
				
				try {
					double[] resultado = Preditor.main(vetor);
					DecimalFormat df = new DecimalFormat("#,###.0000");
					
					//0 esporte
					textEsquerda.setText(df.format(resultado[0] * 100));
					textDireita.setText(df.format(resultado[1] * 100));
					
//					for (i = 0; i < resultado.length; i++) {
//					}
					
				} catch (Exception ex) {
					// TODO Auto-generated catch block
					ex.printStackTrace();
				}
				
				long tempoFinal = System.currentTimeMillis();
				System.out.printf("%.3f segundos%n", (tempoFinal - tempoInicial) / 1000d);
				
				
			}
		});
		btnClassificar.setBounds(17, 82, 117, 29);
		contentPane.add(btnClassificar);
		
		textEsquerda = new JTextField();
		textEsquerda.setText("0,0%");
		textEsquerda.setBounds(17, 139, 130, 26);
		contentPane.add(textEsquerda);
		textEsquerda.setColumns(10);
		
		lblEsporte = new JLabel("Esquerda");
		lblEsporte.setBounds(17, 123, 61, 16);
		contentPane.add(lblEsporte);
		
		textDireita = new JTextField();
		textDireita.setText("0,0%");
		textDireita.setBounds(154, 139, 130, 26);
		contentPane.add(textDireita);
		textDireita.setColumns(10);
		
		lblEconomia = new JLabel("Economia");
		lblEconomia.setBounds(158, 123, 61, 16);
		contentPane.add(lblEconomia);
	}
}
