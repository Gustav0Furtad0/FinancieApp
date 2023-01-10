package org.openjfx.back;

import java.time.LocalDate;

public class AcaoComprada extends AcaoListada {
    private int quantidade;
    private double valorCompra;
    private LocalDate dataCompra;
    
    public AcaoComprada( AcaoListada acaoCompra, LocalDate dataCompra, int quantidade)  {
        super(acaoCompra.getNome(), acaoCompra.getValor(), acaoCompra.getEmpresa(), 
        acaoCompra.getVolume(), acaoCompra.getVariacao());
        this.quantidade = quantidade;
        this.dataCompra = dataCompra;
        this.valorCompra = acaoCompra.getValor();
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

    public void setValor(double valor) {
        this.valor = valor;
    }

}