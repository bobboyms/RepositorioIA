package br.com.etico.ir.preditor;

import java.text.DecimalFormat;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import antlr.collections.List;
import br.com.etico.ir.indexador.Serealizador;
import br.com.etico.ir.utils.Termo;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.lazy.IBk;
import weka.classifiers.trees.J48;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.FastVector;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

public class Preditor {
	
	
	public double[] fazerPrevisaoIBk(String tf, String idf, String tfidf) {
		DataSource ds = null;
		try {
			ds = new DataSource("/Users/thiagoluizrodrigues/Documents/IA/baseNO.arff");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Instances instance = null;
		try {
			instance = ds.getDataSet();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		instance.setClassIndex(3);
//		System.out.println(instance.toString());
		
		IBk bk = new IBk();
		try {
			bk.buildClassifier(instance);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
//		@attribute TF numeric
//		@attribute IDF numeric
//		@attribute TF-IDF numeric
//		@attribute CLASSE {ECONOMIA,ESPORTE}
//		,,,ECONOMIA
		
//		1.0000000000000038,1186.6378310700327,3.554740035581543,ESPORTE
//		0.9999999999999982,548.6109580738314,3.5220098577471775,ECONOMIA
		
		Instance novo = new DenseInstance(instance.numAttributes());
		novo.setDataset(instance);
		novo.setValue(0,Float.parseFloat(tf));
		novo.setValue(1,Float.parseFloat(idf));
		novo.setValue(2,Float.parseFloat(tfidf));
//		novo.setValue(new Attribute(""), value);
		
		double[] resultado = null;
		try {
			resultado = bk.distributionForInstance(novo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		DecimalFormat df = new DecimalFormat("#,###.0000");
//		
//		System.out.println("ECONOMIA:" + df.format(resultado[0] * 100));
//		System.out.println("ESPORTE:" + df.format(resultado[1] * 100));

		return resultado;
		
	}

	
	
	
	public static double[] main(String[][] vetor) throws Exception {
		
		//obtem a instancia da base IA
		DataSource ds = new DataSource("/Users/thiagoluizrodrigues/Documents/IA/baseNO.arff");
		Instances instance = ds.getDataSet();
		
		System.out.println(instance.numAttributes());
		instance.setClassIndex(instance.numAttributes() -1);
		
		J48 bk = new J48();
		
//		NaiveBayes bk = new NaiveBayes();
		bk.buildClassifier(instance);
		
		
		Instance novo = new DenseInstance(instance.numAttributes());
		novo.setDataset(instance);
		
		//obtem a base de termos
		Set<Termo> termos = (Set<Termo>) Serealizador.obterObjeto();
		
		int i = 0;
		
		
		for (Termo termo : termos) {
			novo.setValue(i, new Long(vetor[0][i]));
			i++;
		}
		
		double[] resultado = bk.distributionForInstance(novo);
		return resultado;
		
//		DecimalFormat df = new DecimalFormat("#,###.0000");
//		for (i = 0; i < resultado.length; i++) {
//			System.out.println(df.format(resultado[i] * 100));
//		}
		
		
	}
	
}
