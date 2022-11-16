package App;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ArchiveRW {

    public static boolean verificaArquivo (String path) throws FileNotFoundException {
        boolean exists = (new File(path)).exists();
        if (!exists) {
            PrintWriter out = new PrintWriter(path);
            out.println("username = Gustavo");
            out.println("money = 100");
            out.println("actions = null");
            out.close();
        }
        return exists;
    }
    
    public static List<String[]> leitor(String path) throws IOException {

        verificaArquivo(path);

        List<String[]> result = new ArrayList<String[]>();  

		BufferedReader buffRead = new BufferedReader(new FileReader(path));

		String linha = "",chave = "", valor = "";

		do {
            
			if (linha != null)
               
                try {
                    chave = linha.substring(0, linha.indexOf("=")-1);
                    valor = linha.substring(linha.indexOf("=")+2, linha.length());
                    String[] teste = {chave, valor};
                    result.add( teste );
                    System.out.println(result);
                } catch (Exception e) {

                }
                System.out.println(chave + " e " + valor);

			linha = buffRead.readLine();

		} while (linha != null);

		buffRead.close();
        return null;
    }

	public static void escritor(String path) throws IOException {
		BufferedWriter buffWrite = new BufferedWriter(new FileWriter(path));
		String linha = "";
		try (Scanner in = new Scanner(System.in)) {
            System.out.println("Escreva algo: ");
            linha = in.nextLine();
        }
		buffWrite.append(linha + "\n");
		buffWrite.close();
	}
}
