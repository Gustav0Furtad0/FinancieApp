package App;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import ReadCSV.GetCSV;

public class acao {
    private int quantidade;
    private String nome;
    private double valorCompra;
    private LocalDate dataCompra;

    public acao (String nome, double valorCompra, LocalDate dataCompra, int quantidade) {
        this.nome = nome;
        this.valorCompra = valorCompra;
        this.dataCompra = dataCompra;
        this.quantidade = quantidade;
    }

    public String toString() {
        String str = this.nome + ", " + this.quantidade + ", " + this.valorCompra + ", " + this.dataCompra;
        return str;
    }

    public String getNome() {
        return this.nome;
    }

    public Double val() {
        return valorCompra * quantidade;
    }

    public void aumentaQtd(int qtd) {
        this.quantidade += qtd;
    }

    public void puxaValores() throws IOException {
        // "https://data.nasdaq.com/api/v3/datasets/WIKI/"+ this.nome + ".csv?order=asc"     ?limit=1 order=arc
        List<String[]> valores = GetCSV.read("https://data.nasdaq.com/api/v3/datasets/WIKI/"+this.nome+".csv?order=asc");
        for (int i = valores.size(); i > (valores.size()-2); i--) {
            System.out.println(nome);
        }
    }
}
