
package br.com.etico.ir.extrator;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import org.hibernate.Session;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import br.com.etico.modelo.beans.Postagem;
import br.com.etico.persistencia.jpa.JPAUtil;


public class Extrator {

	
	public static void main2() throws IOException {

		Session session = JPAUtil.getSession();
		session.getTransaction().begin();

		BufferedReader reader = new BufferedReader(
				new InputStreamReader(new FileInputStream("/Users/thiagoluizrodrigues/Documents/IA/socialismo.csv")));
		String linha = null;

		int i = 1;
		while ((linha = reader.readLine()) != null) {

			try {

				Document doc2 = Jsoup.connect(linha.split(" ")[1]).get();

				String artigo = doc2.getElementsByTag("article").text();

				if (artigo.trim().length() > 0) {

					Postagem postagem = new Postagem();
					postagem.setIndexado(false);
					postagem.setTituloPostagem(artigo);
					postagem.setCategoria("ESQUERDA");

					session.persist(postagem);

					System.out.println("INDEXADO: " + i);
					i++;

				} else {

					artigo = doc2.getElementsByTag("body").text();

					if (artigo.trim().length() > 0) {

						Postagem postagem = new Postagem();
						postagem.setIndexado(false);
						postagem.setTituloPostagem(artigo);
						postagem.setCategoria("ESQUERDA");

						session.persist(postagem);

						System.out.println("INDEXADO: " + i);
						i++;
					}

				}

			} catch (IllegalArgumentException ig) {

				System.out.println("ERRo: " + linha);

				continue;
			}

		}
		reader.close();

		session.getTransaction().commit();

	}

	public static void main(String[] args) throws IOException {
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("C:\\projeto_ia\\tecnologia.txt")));
		String linha = null;
		
		int i = 1;
		StringBuilder builder = new StringBuilder();
		while ((linha = reader.readLine()) != null) {
			
			try {
				
				Document doc2 = Jsoup.connect(linha.trim()).get();
				String artigo = doc2.getElementsByTag("article").text();
				
				if (artigo.trim().length() >0) {
					
					String str = "tecnologia;"+artigo;
					
					System.out.println(str);
					
					builder.append(str + "\n");
					
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		FileWriter arq = new FileWriter("C:\\projeto_ia\\tmp.txt");
		arq.write(builder.toString());
		arq.close();
	}

	public static void main2(String[] args) throws IOException {

		// main2();
		//
		// if (true) {
		// return;
		// }

		// Document doc =
		// Jsoup.connect("http://g1.globo.com/tecnologia/blog/tira-duvidas-de-tecnologia/post/whatsapp-como-converter-audios-em-mensagens-de-texto.html").get();
		//
		// System.out.println(doc.getElementsByTag("article").text());

		Document doc = Jsoup.connect("http://g1.globo.com/economia/").get();

		Elements elements = doc.getElementsByTag("a");

		for (Element element : elements) {

			// System.out.println(element);

			String linkHref = element.attr("href");

			if (linkHref.contains("/economia/") && (linkHref.contains(".ghtml") || linkHref.contains("html"))) {
				// System.out.println(linkHref);

				try {

					Document doc2 = Jsoup.connect(linkHref).get();

					String artigo = doc2.getElementsByTag("article").text();

					if (artigo.trim().length() > 0) {

						System.out.println(artigo);
					}

				} catch (IllegalArgumentException ig) {

					continue;
				}

			}

		}

	}

}
