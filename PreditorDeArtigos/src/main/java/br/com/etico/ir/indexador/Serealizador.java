package br.com.etico.ir.indexador;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.datavec.api.util.ClassPathResource;

public class Serealizador {

	private static File baseTermosFile;

	public static void gravarObjeto(Object object) {

		try {
			baseTermosFile = new ClassPathResource("termos.bin").getFile();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		try {

			/*
			 * A Classe FileOutputStream é responsável por criar o arquivo fisicamente no
			 * disco, assim poderemos realizar a escrita neste.
			 */
			FileOutputStream fout = new FileOutputStream(baseTermosFile);

			/*
			 * A Classe ObjectOutputStream escreve os objetos no FileOutputStream
			 */
			ObjectOutputStream oos = new ObjectOutputStream(fout);

			/*
			 * Veja aqui a mágica ocorrendo: Estamos gravando um objeto do tipo Address no
			 * arquivo address.ser. Atenção: O nosso objeto Address que está sendo gravado,
			 * já é gravado de forma serializada
			 */
			oos.writeObject(object);

			oos.close();
			System.out.println("Done");

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static Object obterObjeto() {
		
		Object object = null;
		
		try {
			
			
			baseTermosFile = new ClassPathResource("termos.bin").getFile();

			System.out.println("leu objeto");
			
			/*
			 * Responsável por carregar o arquivo address.ser
			 */
			FileInputStream fin = new FileInputStream(baseTermosFile);

			/*
			 * Responsável por ler o objeto referente ao arquivo
			 */
			ObjectInputStream ois = new ObjectInputStream(fin);

			/*
			 * Aqui a mágica é feita, onde os bytes presentes no arquivo address.ser são
			 * convertidos em uma instância de Address.
			 */
			object = ois.readObject();
			ois.close();

			return object;
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return object;
	}

}
