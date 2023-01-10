package org.openjfx.back;

import java.time.LocalDate;

public class AcaoComprada extends Acao {
    private int quantidade;
    private double valorCompra;
    private LocalDate dataCompra;
    
    public AcaoComprada( String nome, double valor, String empresa, LocalDate dataCompra, int quantidade)  {
        super(nome, valor, empresa);
        this.quantidade = quantidade;
        this.dataCompra = dataCompra;
        this.valorCompra = valor;
    }

    public void aumentaQtd(int qtd) {
        this.quantidade += qtd;
    }

    public void diminuiQtd(int qtd) {
        this.quantidade -= qtd;
    }

    public double lucro() {
        return this.valor - this.valorCompra;
    }

    public int getQuantidade() {
        return quantidade;  
    }

    public double getValorCompra() {
        return valorCompra;
    }

    public LocalDate getDataCompra() {
        return dataCompra;
    }

}