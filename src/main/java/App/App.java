/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package App;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import ReadCSV.GetCSV;

/**
 *
 * @author ADMIN
 */

public class App{
    // private static Scanner teclado;

    public static void main(String[] args) throws IOException{
        // List<String[]> lista = GetCSV.read("https://static.quandl.com/coverage/WIKI_PRICES.csv"); // lista.get(ler)[0]
        //int i = 0;
        //teclado = new Scanner(System.in);

        // String s;
        // do {
        //     for (int ler = i; ler < i+10; ler ++) {
        //         System.out.println();
        //     }
        //     System.out.println("\n\nQuer ler mais 10 nomes?");
        //     s = teclado.nextLine();
        //     if (!s.equals("N"))
        //         i+=10;
        // } while (!s.equals("N"));
        
        User Gustavo = new User("Gustavo");

        LocalDate agora = LocalDate.now(); // Create a date object

        Gustavo.compraAcao("INTL", 20.50, agora);

    } 
}
