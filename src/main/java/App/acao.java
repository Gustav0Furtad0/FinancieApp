package App;

import java.time.LocalDate;

public class acao {
    private String nome;
    private double valorCompra;
    private LocalDate dataCompra;

    public acao (String nome, double valorCompra, LocalDate dataCompra) {
        this.nome = nome;
        this.valorCompra = valorCompra;
        this.dataCompra = dataCompra;
    }

    public String toString() {
        String str = this.nome + ", " + this.valorCompra + ", " + this.dataCompra;
        return str;
    }
}
