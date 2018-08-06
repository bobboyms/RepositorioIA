package projetoIA.controllers;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.datavec.api.util.ClassPathResource;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.deeplearning4j.util.ModelSerializer;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;

import br.com.etico.ir.indexador.Documento;
import br.com.etico.ir.indexador.Serealizador;
import br.com.etico.ir.utils.Termo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class TelaController {

	private MultiLayerNetwork model;

	public TelaController() throws IOException {

		model = ModelSerializer.restoreMultiLayerNetwork(new ClassPathResource("MyMultiLayerNetwork.zip").getFile());

	}

	@FXML
	private TextArea txtArtigoEmTexto;

	@FXML
	private Button btnClassificar;

	@FXML
	private TextField txtUrlArtigo;

	@FXML
	private TextField txtQualClasse;

	@FXML
	void classificarArtigo(ActionEvent event) {

		String url = "";
		String artigo = "";

		try {

			url = txtUrlArtigo.getText();
			artigo = txtArtigoEmTexto.getText();

			if (url != null && url.trim().length() > 5) {
				Document doc = Jsoup.connect(url).get();
				artigo = doc.getElementsByTag("body").text();
				preditorArtigo(artigo);
			} else if (artigo.trim().length() > 10) {
				preditorArtigo(artigo);
			} else {

			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void preditorArtigo(String artigo) {

		Map<Long, Documento> documentos = new HashMap<Long, Documento>();

		try {

			if (artigo.trim().length() > 0) {

				System.out.println(artigo);

				documentos.put(1l, new Documento(artigo, ""));

				Documento.termosUnicosDaBase = (Set<Termo>) Serealizador.obterObjeto();
				String[][] vetor = new String[documentos.size()][Documento.termosUnicosDaBase.size() + 1];

				System.out.println(Documento.termosUnicosDaBase.size());

				int coluna = 0;

				INDArray input = Nd4j.zeros(1, Documento.termosUnicosDaBase.size());

				// percorre toda base de termos
				for (Termo termoBase : Documento.termosUnicosDaBase) {

					int linha = 0;

					for (long key : documentos.keySet()) {

						Documento documento = documentos.get(key);

						if (documento.getTokens().contains(termoBase)) {
							input.putScalar(0, coluna, documento.getTermo(termoBase).getQtdTermo());
						} else {
							input.putScalar(0, coluna, 0);
						}
						//
						// vetor[linha][Documento.termosUnicosDaBase.size()] = documento.getCategoria();
						linha++;
					}

					coluna++;
				}

				// System.out.println(input.getRow(0));

				// carrega rede neural
				System.out.println("Carregando rede neural");

				System.out.println("Carregando rede neural carregada");

				INDArray saida = model.output(input);

				System.out.println(saida);

				System.out.println("SAUDE: " + Math.round(saida.getColumn(0).getDouble(0) * 100));
				System.out.println("ECONOMIA: " + Math.round(saida.getColumn(1).getDouble(0) * 100));
				System.out.println("ESPORTE: " + Math.round(saida.getColumn(2).getDouble(0) * 100));
				System.out.println("TECNOLOGIA: " + Math.round(saida.getColumn(3).getDouble(0) * 100));
				//
				Map<String, Long> unsortMap = new HashMap<String, Long>();
				unsortMap.put("SAUDE", Math.round(saida.getColumn(0).getDouble(0) * 100));
				unsortMap.put("ECONOMIA", Math.round(saida.getColumn(1).getDouble(0) * 100));
				unsortMap.put("ESPORTE", Math.round(saida.getColumn(2).getDouble(0) * 100));
				unsortMap.put("TECNOLOGIA", Math.round(saida.getColumn(3).getDouble(0) * 100));

				Map<String, Long> sortedMap = TelaController.sortByValue(unsortMap);

				int i = 0;
				for (Map.Entry<String, Long> entry : sortedMap.entrySet()) {
					System.out.println("Key : " + entry.getKey() + " Value : " + entry.getValue());

					if (i == 3) {
						
						txtQualClasse.setText(entry.getKey());
						
						return;
					}

					i++;
				}

			} else {
				System.out.println("Não possui a tag ARTICLE");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static Map<String, Long> sortByValue(Map<String, Long> unsortMap) {

		// 1. Convert Map to List of Map
		List<Map.Entry<String, Long>> list = new LinkedList<Map.Entry<String, Long>>(unsortMap.entrySet());

		// 2. Sort list with Collections.sort(), provide a custom Comparator
		// Try switch the o1 o2 position for a different order
		Collections.sort(list, new Comparator<Map.Entry<String, Long>>() {
			public int compare(Map.Entry<String, Long> o1, Map.Entry<String, Long> o2) {
				return (o1.getValue()).compareTo(o2.getValue());
			}
		});

		// 3. Loop the sorted list and put it into a new insertion order Map
		// LinkedHashMap
		Map<String, Long> sortedMap = new LinkedHashMap<String, Long>();
		for (Map.Entry<String, Long> entry : list) {
			sortedMap.put(entry.getKey(), entry.getValue());
		}

		return sortedMap;
	}

}
