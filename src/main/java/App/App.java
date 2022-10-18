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
    private static Scanner teclado;

    public static void main(String[] args) throws IOException{
        teclado = new Scanner(System.in);
        List<String[]> lista = GetCSV.read("https://static.quandl.com/coverage/WIKI_PRICES.csv"); // lista.get(ler)[0]
        
        System.out.println("Bem vindo ao sistema de compra de acoes");
        System.out.println("Digite seu nome para fazer seu cadastro: ");
        User pessoa = new User(teclado.nextLine());
        System.out.println("Bem vindo ao sistema " + pessoa.username() + "!\n\n\n");

        System.out.println("Escolha uma ação para comprar ou navegar... [> próximas, < anteriores, N sair, <number> numero da acao]");

        String s;
        int i = 0, acao;
        do {
            for (int ler = i; ler < i+10;  ler++) {
                System.out.println( ler +": "+lista.get(ler)[0]);
            }
            System.out.println("Escolha uma ação para comprar ou navegar... [> próximas, < anteriores, N sair, <number> numero da acao]");
            s = teclado.nextLine();
            if (s.equals(">"))
                i+=10;
            else if (s.equals("<"))
                i-=10;
            else if (s != null && s.matches("[0-9.]+")) {
                System.out.println("Digite a quantidade de acoes que deseja comprar: ");
                int quatidade = Integer.parseInt(teclado.nextLine());
                acao = Integer.parseInt(s);
                LocalDate agora = LocalDate.now();
                pessoa.compraAcao(lista.get(acao)[0], 20, agora, quatidade);
            }
        } while (!s.equals("N"));
        
        System.out.println("\n\nAcoes de " + pessoa.username() + ".");
        pessoa.acoesConta();
        pessoa.saldoConta();
    }
}
