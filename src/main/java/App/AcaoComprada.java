package App;

import java.time.LocalDate;

public class AcaoComprada extends acao {
    private int quantidade;
    private double valorCompra;
    private LocalDate dataCompra;
    
    public AcaoComprada( acao acaoCompra, LocalDate dataCompra, int quantidade)  {
        super(acaoCompra.getNome(), acaoCompra.getValor(), acaoCompra.getEmpresa(), 
        acaoCompra.getVolume());
        this.quantidade = quantidade;
        this.dataCompra = dataCompra;
        this.valorCompra = acaoCompra.getValor();
    }

    public void aumentaQtd(int qtd) {
        this.quantidade += qtd;
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