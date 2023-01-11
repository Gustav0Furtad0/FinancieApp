package org.openjfx.front;

import org.openjfx.back.AcaoComprada;

/*Alunos:
 * Gustavo de Jesus Furtado ,   202176024;
 * Pedro Andrade Pereira Leão , 202035008.
 */

public class AcaoCompTab {
    private final String empresa;
    private final String stock;
    private String valor;
    private String valorCompra;
    private String ganhos;
    private String dataCompra;
    private String quantidade;
    private String valorTotal;  
    private final AcaoComprada aponta;

    public AcaoCompTab(AcaoComprada aponta, String empresa, String stock, String valor,
    String valorCompra, String ganhos, String dataCompra, String quantidade, String valorTotal) {
        this.aponta = aponta;
        this.empresa = empresa;
        this.stock = stock;
        this.valor = valor;
        this.valorCompra = valorCompra;
        this.ganhos = ganhos;
        this.dataCompra = dataCompra;
        this.quantidade = quantidade;
        this.valorTotal = valorTotal;
    }

    public String getEmpresa() {
        return empresa;
    }

    public String getStock() {
        return stock;
    }

    public String getValor() {
        return valor;
    }

    public AcaoComprada getAponta() {
        return aponta;
    }

    public String getDataCompra() {
        return dataCompra;
    }

    public String getGanhos() {
        return ganhos;
    }

    public String getQuantidade() {
        return quantidade;
    }

    public String getValorCompra() {
        return valorCompra;
    }

    public String getValorTotal() {
        return valorTotal;
    }
}
