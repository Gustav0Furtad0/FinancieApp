package App;

import java.time.LocalDate;
import java.util.*;

public class User {
    private double money;
    private String username;
    private List<acao> actions  = new ArrayList<acao>();
    private String moeda;

    public User (String username) {
        this.username = username;
        this.moeda = "BRL";
        this.money = 1000;
    }

    public void compraAcao(String acao, double valorCompra, LocalDate dataCompra, int quantidade) {
        if ((this.money - valorCompra) < 0) {
            System.out.println("Error: Não tem dinheiro para comprar a acao.");
        } else {

            if(this.actions.size() == 0) {
                this.actions.add(new acao(acao, valorCompra, dataCompra, quantidade));
            } else {
                int verificaSeAcao = verificaAcao(acao);

                if (verificaSeAcao == -1) {
                    this.actions.add(new acao(acao, valorCompra, dataCompra, quantidade));
                    System.out.println("Acao adicionada a carteira!");
                } else {
                    this.actions.get(verificaSeAcao).aumentaQtd(quantidade);
                    System.out.println("Acao incrementada a carteira!");
                }
            }
           
            this.money -= valorCompra * quantidade;
            ordenaAcao();
            System.out.println("Saldo resultante em conta: R$" + this.money + "\n\n");
        }
    }

    public void acoesConta(){
        double valorAcoes = 0;
        for(int i = 0; i < this.actions.size(); i++) {
            System.out.println(this.actions.get(i));
            valorAcoes += this.actions.get(i).val();
        }
        System.out.println("O valor de acoes que detém é R$" + valorAcoes + "." );
    }

    public String moedaConta() {
        return this.moeda;
    }

    public String username() {
        return this.username;
    }

    public Double saldoConta() {
        return this.money;
    }

    private int verificaAcao(String nome) {
        for(int i = 0; i < this.actions.size(); i++) {
            if(this.actions.get(i).getNome().equals(nome)) return i;
        }
        return -1;
    }

    private void ordenaAcao() {
        if(this.actions.size() == 1) return;
        String esse, frente;
        Boolean ordem = false;
        while (ordem == false) {
            for(int i = 0; i < (this.actions.size()-1); i++) {
                esse = this.actions.get(i).getNome();
                frente = this.actions.get(i+1).getNome();
                System.out.println("Comparando " + esse + " " + frente);
                System.out.println("É oq? : " + esse + " " + (charMaior(esse, frente) == 0 ? "é maior" : "é menor"));
                if(charMaior(esse, frente) == 0) {
                    acao auxiliar = this.actions.get(i);
                    this.actions.set(i, this.actions.get(i+1));
                    this.actions.set(i+1, auxiliar);
                    break;
                }
                ordem = true;
            }
        }
    }

    private int charMaior(String a, String b) {
        System.out.println("Tamanho de " + a + " é "+ a.length() + " e de " + b + " é " + b.length());
        if (a.length() == b.length()){

            for(int i = 0; i < a.length(); i++) {
                System.out.println("Para " + a.charAt(i) + " ou " + b.charAt(i));
                if(a.charAt(i) > b.charAt(i)) return 0;
                else if(a.charAt(i) < b.charAt(i)) return 1;
            }
            return -1;

        } else if (a.length() < b.length()){

            for(int i = 0; i < a.length(); i++) {   
                System.out.println("Para " + a.charAt(i) + " ou " + b.charAt(i));
                if(a.charAt(i) > b.charAt(i)) return 0;
                else if(a.charAt(i) < b.charAt(i)) return 1;
            }
            return -1;

        } else {

            for(int i = 0; i < b.length(); i++) {   
                System.out.println("Para " + a.charAt(i) + " ou " + b.charAt(i));
                if(a.charAt(i) > b.charAt(i)) return 0;
                else if(a.charAt(i) < b.charAt(i)) return 1;
            }
            return 1;
        }
    }
}
