package br.com.etico.ir.tokenizacao;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;

import br.com.etico.ir.utils.Termo;

public class StopWords {

	private static String STOPWORDS = "da, com, de, num, do, ja, ao, as, dos, se, por, sem, mas"
			+ "a, agora, ainda, algu�m, algum, alguma, algumas, alguns, ampla, amplas, amplo, amplos, ante, antes, ao, aos,"
			+ " ap�s, aquela, aquelas, aquele, aqueles, aquilo, as, at�, atrav�s, cada, coisa, coisas,"
			+ " com, como, contra, contudo, da, daquele, daqueles, das, de, dela, delas, dele, deles, "
			+ "depois, dessa, dessas, desse, desses, desta, destas, deste, deste, destes, deve, devem, "
			+ "devendo, dever, dever�, dever�o, deveria, deveriam, devia, deviam, disse, disso, disto, "
			+ "dito, diz, dizem, do, dos, e, �, ela, elas, ele, eles, em, enquanto, entre, era, essa, "
			+ "essas, esse, esses, esta, est�, estamos, est�o, estas, estava, estavam, est�vamos, este, "
			+ "estes, estou, eu, fazendo, fazer, feita, feitas, feito, feitos, foi, for, foram, fosse, "
			+ "fossem, grande, grandes, h�, isso, isto, j�, la, l�, lhe, lhes, lo, mas, me, mesma, mesmas,"
			+ " mesmo, mesmos, meu, meus, minha, minhas, muita, muitas, muito, muitos, na, n�o, nas, nem, "
			+ "nenhum, nessa, nessas, nesta, nestas, ningu�m, no, nos, n�s, nossa, nossas, nosso, nossos, "
			+ "num, numa, nunca, o, os, ou, outra, outras, outro, outros, para, pela, pelas, pelo, pelos,"
			+ " pequena, pequenas, pequeno, pequenos, per, perante, pode, pude, podendo, poder, poderia, poderiam,"
			+ " podia, podiam, pois, por, por�m, porque, posso, pouca, poucas, pouco, poucos, primeiro, primeiros,"
			+ " pr�pria, pr�prias, pr�prio, pr�prios, quais, qual, quando, quanto, quantos, que, quem, s�o, se, "
			+ "seja, sejam, sem, sempre, sendo, ser�, ser�o, seu, seus, si, sido, s�, sob, sobre, sua, suas, talvez,"
			+ " tamb�m, tampouco, te, tem, tendo, tenha, ter, teu, teus, ti, tido, tinha, tinham, toda, todas, todavia,"
			+ " todo, todos, tu, tua, tuas, tudo, �ltima, �ltimas, �ltimo, �ltimos, um, uma, umas, uns, vendo, ver, "
			+ "vez, vindo, vir, vos, v�s, r$, R$, (), mais, mas";
	
	private StopWords() {}

	//implementa o padrão de projeto singleton
	public static StopWords getInstance() {
		
		if (stopWords == null) {
			stopWords = new StopWords();
		}
		
		return stopWords;
	}
	
	private static StopWords stopWords;
	
	public static boolean isStopWord(String palavra) {
		
		StringBuilder builder = new StringBuilder(STOPWORDS);
		
		List<String> stops = getInstance().tokenizador(builder);
		
		for (String stop : stops) {
			if (stop.equals(palavra)) {
				
//				System.out.println("REMOVIDO : " + palavra);
				
				return true;
			}
		}
		
		return false;
		
	}

	public String textNormalizer(String texto) {
		texto = Normalizer.normalize(texto, Normalizer.Form.NFD);
		return texto.replaceAll("[^\\p{ASCII}]", "");
	}

	public List<String> tokenizador(StringBuilder stringBuilder) {
		String[] texto = stringBuilder.toString().split(" "); // ".split(" ");

		List<String> lisTokens = new ArrayList<String>();

		for (String palavra : texto) {

			String[] letras = palavra.trim().split("");

			String token = "";

			for (String letra : letras) {

				// transforma a letra em minusculo
				letra = letra.toLowerCase();

				if (letra.equals(",") || letra.equals(".") || letra.equals("/") || letra.equals("\\")
						|| letra.equals("-") || letra.equals("*")) {
					continue;
				}

				token += letra;
			}

			lisTokens.add(textNormalizer(token));

		}

		return lisTokens;
	}


}
