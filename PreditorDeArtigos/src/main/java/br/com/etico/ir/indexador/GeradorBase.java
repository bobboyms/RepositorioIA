
package br.com.etico.ir.indexador;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.criterion.Restrictions;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.netlib.blas.Dcopy;

import br.com.etico.ir.preditor.Preditor;
import br.com.etico.ir.tokenizacao.Tokenizacao;
import br.com.etico.ir.utils.Termo;
import br.com.etico.modelo.beans.Postagem;
import br.com.etico.persistencia.jpa.JPAUtil;
import groovy.ui.SystemOutputInterceptor;

public class GeradorBase {

	public static Map<Long, Documento> documentos = new HashMap<>();

	public static void gerarArquivo(File file, StringBuilder builder) {
		FileWriter arquivo;
		try {
			arquivo = new FileWriter(file);
			arquivo.write(builder.toString());
			arquivo.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws IOException {

		long tempoInicial = System.currentTimeMillis();

		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("C:\\projeto_ia\\base_dados.txt")));
		String linhas = null;
		
		long i = 1;
		while ((linhas = reader.readLine()) != null) {
			
			String texto = linhas.split(";")[1];
			String categoria = linhas.split(";")[0];
			
			documentos.put(i, new Documento(texto, categoria));
			
			i++;
		}
		
		System.out.println("TERMOS: " + Documento.termosUnicosDaBase.size());
		
//		documentos.put(1l, new Documento("jose antonio antonio", "alegria"));
//		documentos.put(2l, new Documento("pedro agenor", "triste"));
//		documentos.put(3l, new Documento("maria", "medo"));
//		documentos.put(4l, new Documento("bola", "medo"));
		
//		Documento.termosUnicosDaBase = (Set<Termo>) Serealizador.obterObjeto();
		String[][] vetor = new String[documentos.size()][Documento.termosUnicosDaBase.size() + 1];

		String tmp = "";//"@relation baseNO\n\n";
		
		int coluna = 0;
		StringBuilder builder = new StringBuilder();
		
		//persiste a base de termas
		Serealizador.gravarObjeto(Documento.termosUnicosDaBase);
		
		long qtd = 0; //Documento.termosUnicosDaBase.size();
		
		//percorre toda base de termos
		for (Termo termoBase : Documento.termosUnicosDaBase) {

			int linha = 0;
			
			tmp += termoBase.getTermo() + ",";
			
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
		
		
//		tmp += "@attribute Class {ESQUERDA, DIREITA}\n\n@data\n";
		
		builder.append(tmp + "classe\n");
		
		for(int linha=0 ; linha < documentos.size() ; linha++){
			tmp = "";
            for(coluna = 0; coluna <= Documento.termosUnicosDaBase.size() ; coluna ++){
            
            		if (coluna < Documento.termosUnicosDaBase.size()) {
            			tmp += (vetor[linha][coluna]) + ",";
            		} else {
            			tmp += (vetor[linha][coluna]);
            		}
            	
            }
            tmp += "\n";
            builder.append(tmp);
        }

//		System.out.println(builder.toString());
		gerarArquivo(new File("C:\\projeto_ia\\base_para_ia.csv"), builder);
//
		long tempoFinal = System.currentTimeMillis();
	    System.out.printf("%.3f segundos%n", (tempoFinal - tempoInicial) / 1000d);
		
	}

}