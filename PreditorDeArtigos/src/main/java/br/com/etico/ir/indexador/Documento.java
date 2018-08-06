package br.com.etico.ir.indexador;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import br.com.etico.ir.tokenizacao.Tokenizacao;
import br.com.etico.ir.utils.Termo;

public class Documento {

	public static void main(String[] args) {
//		Termo termo = new Termo("1");		
//		Termo termo1 = new Termo("5");	
//		Termo termo2 = new Termo("3");
//		Termo termo3 = new Termo("4");
//		
//		Set<Termo> termos = new LinkedHashSet()<>;
//		termos.add(termo);
//		termos.add(termo1);
//		termos.add(termo2);
//		termos.add(termo3);
//		
//		termos.forEach((k)->System.out.println("Item : " + k.getTermo()));
//		
//		
//		System.out.println(termos.size());
		
	}
	
	public static Set<Termo> termosUnicosDaBase = new LinkedHashSet<Termo>();
	
	private static Tokenizacao tokenizador = new Tokenizacao();
	private Set<Termo> tokens;
	
	private String texto;
	private String categoria;
	
	public Documento(String texto, String categoria) {
		
		this.texto = texto;
		this.categoria = categoria;
		
		if (tokens == null) {
			tokens = new HashSet<Termo>();
			tokeNizacao(texto);
		}
		
	}
	
	public Termo getTermo(Termo key) {
		
		for (Termo token : tokens) {
			if (token.equals(key)) {
				return token;
			}
		}
		
		return null;
	}
	
	public long getTotalPalavras() {
		return getTokens().size();
	}
	
	private void tokeNizacao(String texto) {
		setTokens(tokenizador.tokenizadorTermo(new StringBuilder(texto))) ;
		
		for (Termo termo: getTokens()) {
			Documento.termosUnicosDaBase.add(termo);
		}
	}
	
	@Override
	public String toString() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}
	
	@Override
	public boolean equals(Object obj) {
		
		if (((Documento)obj).toString().equals(texto)) {
			return true;
		}
		
		return false;
		
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public Set<Termo> getTokens() {
		return tokens;
	}

	public void setTokens(Set<Termo> tokens) {
		this.tokens = tokens;
	}


}
