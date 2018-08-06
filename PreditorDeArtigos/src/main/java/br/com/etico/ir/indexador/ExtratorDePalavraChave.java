package br.com.etico.ir.indexador;

import java.io.IOException;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import br.com.etico.ir.utils.Termo;

public class ExtratorDePalavraChave {
	
	public static String API_GOOGLE = "AIzaSyD2VAgV5OnL554SD2OtOwq2F5_jxfNgtEk";
	
	
	public static void main(String[] args) throws IOException {
		
//		Document doc = Jsoup
//				.connect("https://www.google.com/search?q=mario")
//				.userAgent("Mozilla/5.0")
//				.timeout(5000).get();
		
		
//		System.out.println(doc.toString());
		
		
		String pesquisa = "COMO ESCOLHER UM SOFTWARE DE GESTÃO PARA SUA EMPRESA";
		
		TituloDocumento tituloDocumento = new TituloDocumento(pesquisa);
		Set<Termo> termos =  tituloDocumento.getTokens();
		
		Document doc1 = Jsoup.connect("https://www.eticosoftware.com.br/artigos/escolher_um_software_para_minha_empresa_").get();
		Documento documento1 = new Documento(doc1.getElementsByTag("article").text(),"");
		
		Document doc2 = Jsoup.connect("https://www.guiaempreendedor.com/como-escolher-um-software-de-gestao/").get();
		Documento documento2 = new Documento(doc2.getElementsByTag("article").text(),"");
		
		
		
		for (Termo termoPesquisa: termos) {
			double peso1 = (documento1.getTermo(termoPesquisa).getFrequenciaDoTermoTF() * 100l);
			double peso2 = (documento2.getTermo(termoPesquisa).getFrequenciaDoTermoTF() * 100l);	
			
			System.out.println(termoPesquisa.getTermo() + ": " + peso1 + " | " + peso2);
			
		}
		
		pesquisa = pesquisa.trim();
		
		System.out.println(pesquisa);

		
		
		//verifica a posição do site no google
		
		
		
	}
	
}
