package projetoIA;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.deeplearning4j.util.ModelSerializer;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;

import br.com.etico.ir.indexador.Documento;
import br.com.etico.ir.indexador.Serealizador;
import br.com.etico.ir.utils.Termo;

public class Preditor {

	public static void main(String[] args) {
		
		Map<Long, Documento> documentos = new HashMap<Long, Documento>();
		
		String url = "https://www.parmais.com.br/blog/atual-situacao-economica-do-brasil/";
		Document doc2;
		
		try {
			
			doc2 = Jsoup.connect(url).get();
			
			String artigo = doc2.getElementsByTag("body").text(); //"EUA crescem 4,1% no segundo trimestre, o  4 econômica americana se recupera com solidez. “O ritmo é incrível”, avaliou o presidente, “somos a inveja econômica do mundo” Sandro Pozzi Twitter Nova York 27 JUL 2018 - 21:56 CEST O presidente Donald Trump num evento em Iowa. JOSHUA ROBERTS REUTERS MAIS INFORMAÇÕES Estados Unidos e União Europeia esfriam guerra comercial Patrimônio dos norte-americanos chega aos 370 trilhões de reais A economia dos Estados Unidos teve um rendimento particularmente bom no segundo trimestre. A primeira estimativa indica que cresceu a uma taxa anualizada de 4,1%, melhor resultado em quase quatro anos. Mas a grande pergunta é se pode sustentar-se acima de 3%, como promete o presidente Donald Trump, ou se vai se moderar a um nível mais próximo do seu potencial, de 2,5%, já que parte do impulso se deveu às exportações de soja antecipando-se à nova tarifa chinesa. A recuperação é sólida em relação aos 2,2% do primeiro trimestre, e sai a apenas três meses da eleição legislativa em que o Partido Republicano busca manter sua maioria no Congresso. Nos três trimestres anteriores já rondava os 3% que o mandatário republicano vislumbra e necessita para poder custear a desoneração tributária que está no centro do seu plano econômico. O melhor trimestre até agora na recuperação depois da crise foi o terceiro de 2014, quando os EUA cresceram 4,9% em um intervalo de 12 meses. O consumo privado, que representa dois terços da economia, se reforça ao passar do anêmico 0,9% do começo de 2018 para 4% no trimestre recém-encerrado. É um ritmo superior à tendência média de 2,6% dos últimos anos, e que o presidente pode atribuir a seus incentivos fiscais. Um mercado trabalhista com pleno emprego, a alta dos salários, a redução dos impostos e a recuperação da confiança dão sustentação ao movimento. O investimento empresarial cresceu 7,3%, moderando-se frente aos 11% do primeiro trimestre. A solidez do indicador está dentro do nível de tolerância da Reserva Federal (banco central), que, na sua reunião da semana que vem, provavelmente deixará a taxa referencial de juros entre 1,75% e 2%, nível fixado em junho após sete altas desde dezembro de 2015. Trump comentou publicamente dias atrás que não está contente com essa progressão no preço do dinheiro, embora ao mesmo tempo diga que a economia vai melhor do que nunca. “O ritmo é incrível”, avaliou o presidente, “somos a inveja econômica do mundo”. Na véspera, já disse que estaria contente com um crescimento próximo de 4%. O consenso de Wall Street antecipava um ritmo próximo de 4,4%. Trump, acompanhado por sua equipe econômica, disse que os EUA estão a caminho de obter em 2018 o nível mais alto de crescimento em 13 anos. “Será superior a 3%”, anteviu, “e, se for mantido, a economia duplicará seu tamanho em 10 anos”. O estímulo da soja Espera-se que o Fed faça neste ano mais dois aumentos de juros, de um quarto de ponto cada um, em setembro e em dezembro – esta já depois das eleições legislativas. A retórica do republicano, portanto, não deixa margem agora para que a equipe dirigida por Jerome Powell prossiga com o plano de retirada gradual dos estímulos, a fim de evitar um reaquecimento da economia e demonstrar ao mesmo tempo que não se deixa influir por pressões políticas. O sólido dado de crescimento coincide com uma atitude mais agressiva de Trump no âmbito do comércio. A alta dos custos tarifários e a valorização do dólar estão obrigando as grandes multinacionais a reverem para baixo suas projeções de negócios. Powell diz, entretanto, que é cedo para dizer o impacto que o protecionismo terá no conjunto da economia. Há, entretanto, elementos do indicador que refletem os efeitos da guerra comercial, embora no sentido inverso. As exportações cresceram 9,3% porque se aceleraram os envios de soja para a China, antecipando a entrada em vigor da tarifa sobre os produtos agrícolas dos EUA como medida de retaliação. Portanto, esse efeito positivo pelo lado das exportações, que segundo as estimativas contribuiu em mais de um ponto percentual para o PIB deste trimestre, é artificial. O mesmo impulso temporário pode ter aparecido em outras categorias, como o maquinário. A Harley-Davidson indicou em seu balanço publicado nesta semana que elevou os estoques de motocicletas exportadas para a Europa, antecipando-se à entrada em vigor de novas tarifas. O risco mais adiante é que afinal não sejam vendidas e se tornem um lastro. Trump insiste em que obterá acordos que sustentarão o crescimento. Recomanar no Facebook Twittear Otros Fechar Recomendar no Linkedin Recomendar no GooglePlus Recomendar no Pinterest MAIS INFORMAÇÕES O crescimento econômico dá oxigênio a Trump Jerome Powell o novo presidente do Fed, banco central mais poderoso do mundo Arquivado em: Federal Reserve Jerome Powell PIB Crescimento econômico Taxas juro Donald Trump Indicadores econômicos Organismos econômicos Conjuntura econômica Estados Unidos América do Norte Créditos América Serviços bancários Economia Banca Finanças newsletters Inscreva-se em nossos newsletters\r\n" + 
					//+ "" Jornal diz que Mbappé jogou últimas duas partidas da Copa com lesão nas costas Segundo o L'Équipe, o atacante teria sofrido lesão nas costas apenas 24 horas antes da partida das semifinais contra a Bélgica FOX Sports Eleito a Revelação da Copa do Mundo da Rússia, o atacante campeão com a seleção francesa Kylian Mbappé teria disputado as últimas duas partidas do torneio – semifinal e final – com uma lesão nas costas. Segundo publicação do diário L’Équipe, o camisa 10 dos Bleus teria se machucado 24h antes do confronto contra os belgas. FOX Sports transmite o maior torneio de pré-temporada do futebol europeu Autor de quatro gols no Mundial, Mbappé teria se machucado sozinho. Na véspera da semifinal, o atacante estava concentrado no seu quarto, quando se levantou para tomar banho. Foi então que o camisa 10 fez um movimento falso e começou a sentir dor na região das costas e caindo paralisado na sua cama, como descreveu a publicação. Veja as últimas do Mercado da Bola e quem pode chegar ao seu time Logo em seguida, o titular do Paris Saint-Germain foi submetido a exames, que confirmaram que o jogador havia lesionado três vértebras. Em nenhum momento seu corte da disputa do torneio foi descartado, com Mbappé iniciando tratamento intensivo e sendo orientado a disfarçar ao máximo a lesão, não somente para não preocupar os demais companheiros, como também para não dar indícios de que estava machucado para os adversários. Apesar de não ter balançado as redes contra a Bélgica, o atacante teve papel fundamental na decisão contra a Croácia e marcou um dos gols da vitória por 4 a 2, em Moscou. Leia também: Barça sobe proposta por Willian Fla faz proposta de R$35 milhões por volante Destaque da Rússia a caminho do Chelsea Crédito foto: EFE Tags futebol seleção francesa Copa do Mundo 2018 Copa do Mundo 25 July 2018 18:55 hs França divulga novo escudo após bicampeonato da Copa do Mundo Nesta quarta-feira (25 de julho), Federação Francesa mostrou a todos como será o emblema da seleção, que terá agora a segunda estrela, referente às conquistas de 1998 e 2018 CONTINUE A LEITURA Copa do Mundo 25 July 2018 21:42 hs CBF renova com Tite e Edu Gaspar até a Copa do Mundo de 2022 Nesta quarta-feira (25 de julho), entidade confirmou a sequência do trabalho da atual comissão técnica do Brasil para os próximos quatro anos CONTINUE A LEITURA Copa do Mundo 25 July 2018 16:47 hs Mbappé revela que tentou convencer companheiro de seleção a ir para o PSG O jovem atacante fez a revelação em entrevista à revista France Football CONTINUE A LEITURA Copa do Mundo 22 July 2018 0:22 hs Neymar desabafa sobre fama de cai-cai: \"Pegaram no pé de quem sofre falta\" Craque voltou a falar com a imprensa neste sábado (21), durante evento realizado em Praia Grande, São Paulo. Jogador não fugiu das perguntas e rebateu críticas sofridas na Copa do Mundo da Rússia CONTINUE A LEITURA Copa do Mundo 26 July 2018 11:52 hs Tite fará história pela Seleção Brasileira se seguir no cargo até a Copa de 2022 Se continuar à frente da equipe canarinho até o final de seu contrato, o gaúcho será dono da maior passagem contínua de um treinador pela equipe pentacampeã mundial CONTINUE A LEITURA Copa do Mundo 27 July 2018 21:7 hs A postagem de Coutinho que está emocionando a web; veja Nesta sexta-feira (27 de julho), craque registrou o encontro com o garoto Wallace, da Vila Cruzeiro e que emocionou o Brasil com uma camisa improvisada do meia CONTINUE A LEITURA Copa do Mundo 29 July 2018 23:51 hs Imprensa inglesa denuncia sabotagem na eleição do Catar como sede da Copa de 2022 Sunday Times informa que houve divulgação de notícias falsas e toda a elaboração de um projeto para fazer os rivais perderem força na concorrência CONTINUE A LEITURA Copa do Mundo 25 July 2018 16:3 hs Fifa elege pintura da França como o gol mais bonito da Copa do Mundo Nesta quarta-feira (25 de julho), entidade colocou o chutaço de Pavard contra a Argentina como o tento mais belo do Mundial da Rússia CONTINUE A LEITURA Copa do Mundo 26 July 2018 15:30 hs Rodrygo detona críticos de Neymar: “Estão falando muita besteira” Em entrevista ao Estadão, destaque do Santos saiu em defesa do maior nome do futebol brasileiro no momento. Segundo ele, desempenho do camisa 10 na Copa foi acima do esperado CONTINUE A LEITURA Copa do Mundo 24 July 2018 12:15 hs Geromel revela jogador que ficou 'inconsolável' após eliminação do Brasil na Copa O zagueiro do Grêmio fez a revelação em entrevista ao canal Sportv nesta segunda-feira (23 de julho) CONTINUE A LEITURA";//doc2.getElementsByTag("article").text();
			
			if (artigo.trim().length() >0) {
				
				System.out.println(artigo);
				
				documentos.put(1l, new Documento(artigo, ""));
				
				Documento.termosUnicosDaBase = (Set<Termo>) Serealizador.obterObjeto();
				String[][] vetor = new String[documentos.size()][Documento.termosUnicosDaBase.size() + 1];
				
				System.out.println(Documento.termosUnicosDaBase.size());
				
				int coluna = 0;
				
				INDArray input = Nd4j.zeros(1, Documento.termosUnicosDaBase.size());
				
				//percorre toda base de termos
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
//						vetor[linha][Documento.termosUnicosDaBase.size()] = documento.getCategoria();
						linha++;
					}
					
					coluna++;
				}
				
//				System.out.println(input.getRow(0));

				//carrega rede neural
				System.out.println("Carregando rede neural");
				MultiLayerNetwork model = ModelSerializer.restoreMultiLayerNetwork(new File("C:\\projeto_ia\\base\\MyMultiLayerNetwork.zip"));
				
				System.out.println("Carregando rede neural carregada");
				
				INDArray saida = model.output(input);
				
				System.out.println(saida);
				
				
				System.out.println("SAUDE: " + Math.round(saida.getColumn(0).getDouble(0) * 100));
				System.out.println("ECONOMIA: " + Math.round(saida.getColumn(1).getDouble(0) * 100));
				System.out.println("ESPORTE: " + Math.round(saida.getColumn(2).getDouble(0) * 100));
				System.out.println("TECNOLOGIA: " + Math.round(saida.getColumn(3).getDouble(0) * 100));
				
				
			} else {
				System.out.println("Não possui a tag ARTICLE");
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
}
