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
			
			String artigo = doc2.getElementsByTag("body").text(); //"EUA crescem 4,1% no segundo trimestre, o  4 econ�mica americana se recupera com solidez. �O ritmo � incr�vel�, avaliou o presidente, �somos a inveja econ�mica do mundo� Sandro Pozzi Twitter Nova York 27 JUL 2018 - 21:56 CEST O presidente Donald Trump num evento em Iowa. JOSHUA ROBERTS REUTERS MAIS INFORMA��ES Estados Unidos e Uni�o Europeia esfriam guerra comercial Patrim�nio dos norte-americanos chega aos 370 trilh�es de reais A economia dos Estados Unidos teve um rendimento particularmente bom no segundo trimestre. A primeira estimativa indica que cresceu a uma taxa anualizada de 4,1%, melhor resultado em quase quatro anos. Mas a grande pergunta � se pode sustentar-se acima de 3%, como promete o presidente Donald Trump, ou se vai se moderar a um n�vel mais pr�ximo do seu potencial, de 2,5%, j� que parte do impulso se deveu �s exporta��es de soja antecipando-se � nova tarifa chinesa. A recupera��o � s�lida em rela��o aos 2,2% do primeiro trimestre, e sai a apenas tr�s meses da elei��o legislativa em que o Partido Republicano busca manter sua maioria no Congresso. Nos tr�s trimestres anteriores j� rondava os 3% que o mandat�rio republicano vislumbra e necessita para poder custear a desonera��o tribut�ria que est� no centro do seu plano econ�mico. O melhor trimestre at� agora na recupera��o depois da crise foi o terceiro de 2014, quando os EUA cresceram 4,9% em um intervalo de 12 meses. O consumo privado, que representa dois ter�os da economia, se refor�a ao passar do an�mico 0,9% do come�o de 2018 para 4% no trimestre rec�m-encerrado. � um ritmo superior � tend�ncia m�dia de 2,6% dos �ltimos anos, e que o presidente pode atribuir a seus incentivos fiscais. Um mercado trabalhista com pleno emprego, a alta dos sal�rios, a redu��o dos impostos e a recupera��o da confian�a d�o sustenta��o ao movimento. O investimento empresarial cresceu 7,3%, moderando-se frente aos 11% do primeiro trimestre. A solidez do indicador est� dentro do n�vel de toler�ncia da Reserva Federal (banco central), que, na sua reuni�o da semana que vem, provavelmente deixar� a taxa referencial de juros entre 1,75% e 2%, n�vel fixado em junho ap�s sete altas desde dezembro de 2015. Trump comentou publicamente dias atr�s que n�o est� contente com essa progress�o no pre�o do dinheiro, embora ao mesmo tempo diga que a economia vai melhor do que nunca. �O ritmo � incr�vel�, avaliou o presidente, �somos a inveja econ�mica do mundo�. Na v�spera, j� disse que estaria contente com um crescimento pr�ximo de 4%. O consenso de Wall Street antecipava um ritmo pr�ximo de 4,4%. Trump, acompanhado por sua equipe econ�mica, disse que os EUA est�o a caminho de obter em 2018 o n�vel mais alto de crescimento em 13 anos. �Ser� superior a 3%�, anteviu, �e, se for mantido, a economia duplicar� seu tamanho em 10 anos�. O est�mulo da soja Espera-se que o Fed fa�a neste ano mais dois aumentos de juros, de um quarto de ponto cada um, em setembro e em dezembro � esta j� depois das elei��es legislativas. A ret�rica do republicano, portanto, n�o deixa margem agora para que a equipe dirigida por Jerome Powell prossiga com o plano de retirada gradual dos est�mulos, a fim de evitar um reaquecimento da economia e demonstrar ao mesmo tempo que n�o se deixa influir por press�es pol�ticas. O s�lido dado de crescimento coincide com uma atitude mais agressiva de Trump no �mbito do com�rcio. A alta dos custos tarif�rios e a valoriza��o do d�lar est�o obrigando as grandes multinacionais a reverem para baixo suas proje��es de neg�cios. Powell diz, entretanto, que � cedo para dizer o impacto que o protecionismo ter� no conjunto da economia. H�, entretanto, elementos do indicador que refletem os efeitos da guerra comercial, embora no sentido inverso. As exporta��es cresceram 9,3% porque se aceleraram os envios de soja para a China, antecipando a entrada em vigor da tarifa sobre os produtos agr�colas dos EUA como medida de retalia��o. Portanto, esse efeito positivo pelo lado das exporta��es, que segundo as estimativas contribuiu em mais de um ponto percentual para o PIB deste trimestre, � artificial. O mesmo impulso tempor�rio pode ter aparecido em outras categorias, como o maquin�rio. A Harley-Davidson indicou em seu balan�o publicado nesta semana que elevou os estoques de motocicletas exportadas para a Europa, antecipando-se � entrada em vigor de novas tarifas. O risco mais adiante � que afinal n�o sejam vendidas e se tornem um lastro. Trump insiste em que obter� acordos que sustentar�o o crescimento. Recomanar no Facebook Twittear Otros Fechar Recomendar no Linkedin Recomendar no GooglePlus Recomendar no Pinterest MAIS INFORMA��ES O crescimento econ�mico d� oxig�nio a Trump Jerome Powell o novo presidente do Fed, banco central mais poderoso do mundo Arquivado em: Federal Reserve Jerome Powell PIB Crescimento econ�mico Taxas juro Donald Trump Indicadores econ�micos Organismos econ�micos Conjuntura econ�mica Estados Unidos Am�rica do Norte Cr�ditos Am�rica Servi�os banc�rios Economia Banca Finan�as newsletters Inscreva-se em nossos newsletters\r\n" + 
					//+ "" Jornal diz que Mbapp� jogou �ltimas duas partidas da Copa com les�o nas costas Segundo o L'�quipe, o atacante teria sofrido les�o nas costas apenas 24 horas antes da partida das semifinais contra a B�lgica FOX Sports Eleito a Revela��o da Copa do Mundo da R�ssia, o atacante campe�o com a sele��o francesa Kylian Mbapp� teria disputado as �ltimas duas partidas do torneio � semifinal e final � com uma les�o nas costas. Segundo publica��o do di�rio L��quipe, o camisa 10 dos Bleus teria se machucado 24h antes do confronto contra os belgas. FOX Sports transmite o maior torneio de pr�-temporada do futebol europeu Autor de quatro gols no Mundial, Mbapp� teria se machucado sozinho. Na v�spera da semifinal, o atacante estava concentrado no seu quarto, quando se levantou para tomar banho. Foi ent�o que o camisa 10 fez um movimento falso e come�ou a sentir dor na regi�o das costas e caindo paralisado na sua cama, como descreveu a publica��o. Veja as �ltimas do Mercado da Bola e quem pode chegar ao seu time Logo em seguida, o titular do Paris Saint-Germain foi submetido a exames, que confirmaram que o jogador havia lesionado tr�s v�rtebras. Em nenhum momento seu corte da disputa do torneio foi descartado, com Mbapp� iniciando tratamento intensivo e sendo orientado a disfar�ar ao m�ximo a les�o, n�o somente para n�o preocupar os demais companheiros, como tamb�m para n�o dar ind�cios de que estava machucado para os advers�rios. Apesar de n�o ter balan�ado as redes contra a B�lgica, o atacante teve papel fundamental na decis�o contra a Cro�cia e marcou um dos gols da vit�ria por 4 a 2, em Moscou. Leia tamb�m: Bar�a sobe proposta por Willian Fla faz proposta de R$35 milh�es por volante Destaque da R�ssia a caminho do Chelsea Cr�dito foto: EFE Tags futebol sele��o francesa Copa do Mundo 2018 Copa do Mundo 25 July 2018 18:55 hs Fran�a divulga novo escudo ap�s bicampeonato da Copa do Mundo Nesta quarta-feira (25 de julho), Federa��o Francesa mostrou a todos como ser� o emblema da sele��o, que ter� agora a segunda estrela, referente �s conquistas de 1998 e 2018 CONTINUE A LEITURA Copa do Mundo 25 July 2018 21:42 hs CBF renova com Tite e Edu Gaspar at� a Copa do Mundo de 2022 Nesta quarta-feira (25 de julho), entidade confirmou a sequ�ncia do trabalho da atual comiss�o t�cnica do Brasil para os pr�ximos quatro anos CONTINUE A LEITURA Copa do Mundo 25 July 2018 16:47 hs Mbapp� revela que tentou convencer companheiro de sele��o a ir para o PSG O jovem atacante fez a revela��o em entrevista � revista France Football CONTINUE A LEITURA Copa do Mundo 22 July 2018 0:22 hs Neymar desabafa sobre fama de cai-cai: \"Pegaram no p� de quem sofre falta\" Craque voltou a falar com a imprensa neste s�bado (21), durante evento realizado em Praia Grande, S�o Paulo. Jogador n�o fugiu das perguntas e rebateu cr�ticas sofridas na Copa do Mundo da R�ssia CONTINUE A LEITURA Copa do Mundo 26 July 2018 11:52 hs Tite far� hist�ria pela Sele��o Brasileira se seguir no cargo at� a Copa de 2022 Se continuar � frente da equipe canarinho at� o final de seu contrato, o ga�cho ser� dono da maior passagem cont�nua de um treinador pela equipe pentacampe� mundial CONTINUE A LEITURA Copa do Mundo 27 July 2018 21:7 hs A postagem de Coutinho que est� emocionando a web; veja Nesta sexta-feira (27 de julho), craque registrou o encontro com o garoto Wallace, da Vila Cruzeiro e que emocionou o Brasil com uma camisa improvisada do meia CONTINUE A LEITURA Copa do Mundo 29 July 2018 23:51 hs Imprensa inglesa denuncia sabotagem na elei��o do Catar como sede da Copa de 2022 Sunday Times informa que houve divulga��o de not�cias falsas e toda a elabora��o de um projeto para fazer os rivais perderem for�a na concorr�ncia CONTINUE A LEITURA Copa do Mundo 25 July 2018 16:3 hs Fifa elege pintura da Fran�a como o gol mais bonito da Copa do Mundo Nesta quarta-feira (25 de julho), entidade colocou o chuta�o de Pavard contra a Argentina como o tento mais belo do Mundial da R�ssia CONTINUE A LEITURA Copa do Mundo 26 July 2018 15:30 hs Rodrygo detona cr�ticos de Neymar: �Est�o falando muita besteira� Em entrevista ao Estad�o, destaque do Santos saiu em defesa do maior nome do futebol brasileiro no momento. Segundo ele, desempenho do camisa 10 na Copa foi acima do esperado CONTINUE A LEITURA Copa do Mundo 24 July 2018 12:15 hs Geromel revela jogador que ficou 'inconsol�vel' ap�s elimina��o do Brasil na Copa O zagueiro do Gr�mio fez a revela��o em entrevista ao canal Sportv nesta segunda-feira (23 de julho) CONTINUE A LEITURA";//doc2.getElementsByTag("article").text();
			
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
				System.out.println("N�o possui a tag ARTICLE");
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
}
