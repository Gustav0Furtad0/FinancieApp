package org.openjfx.back;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import org.openjfx.ArchiveRW.JavaxTeste;

public class App{
    private static Scanner teclado;

    public static String FormatD (double n) {
        String dn = String.format("%.2f", n);
        return dn;
    }

    public static void main(String[] args) throws Exception{

        Boolean arquivo = JavaxTeste.existe();

        List<acao> lista = acao.puxaValores();  

        teclado = new Scanner(System.in);

        System.out.println("Bem vindo ao sistema de compra de acoes");

        User pessoa;
        if (arquivo) {
            pessoa = JavaxTeste.readFileUser();
        } else {
            System.out.println("Digite seu nome para fazer seu cadastro: ");
            pessoa = new User(teclado.nextLine(), 5000);
            JavaxTeste.createFileUser(pessoa.userToMap());
        }

        System.out.println("Bem vindo ao sistema " + pessoa.getUsername() + "!\n\n\n");

        String s;
        int i = 1280, acao;
        do {
            for (int ler = i; ler < i+10;  ler++) {
                System.out.println(ler + ": " + lista.get(ler).getNome() + "   Valor: " + FormatD(lista.get(ler).getValor())
                + " Var: " + (lista.get(ler).getVariacao().equals(null) ? "0" : lista.get(ler).getVariacao()) );
            }

            System.out.println("Escolha uma ação para comprar ou navegar... [> próximas, < anteriores, N sair, <number> numero da acao]");
            System.out.println("Ou digite C para exibir sua carteira");

            s = teclado.nextLine().toUpperCase();
            if (s.equals(">"))
                i+=10;

            else if (s.equals("<"))
                i-=10;

            else if (s != null && s.matches("[0-9]*")) {
                System.out.println("Digite a quantidade de acoes que deseja comprar: ");
                int quatidade = Integer.parseInt(teclado.nextLine());
                acao = Integer.parseInt(s);
                LocalDate agora = LocalDate.now();
                pessoa.compraAcao(lista.get(acao), agora, quatidade);
                JavaxTeste.updateFileUser(pessoa);

            } else if (s.equals("C")) {
                pessoa.exibirCarteira();

            } else if (s.equals("N")) {
                System.out.println("Saindo!!!!!!!");
                JavaxTeste.updateFileUser(pessoa);
            }

        } while (!s.equals("N"));
    }
}
 