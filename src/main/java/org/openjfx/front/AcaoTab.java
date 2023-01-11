package org.openjfx.front;

import org.openjfx.back.AcaoListada;

/*Alunos:
 * Gustavo de Jesus Furtado ,   202176024;
 * Pedro Andrade Pereira Leão , 202035008.
 */

public class AcaoTab {
    private final String empresa;
    private final String stock;
    private final String valor;
    private final String volume;
    private final String variacao;
    private final AcaoListada aponta;

    public AcaoTab(AcaoListada aponta, String empresa, String stock, String valor, String volume, String variacao) {
        this.aponta = aponta;
        this.empresa = empresa;
        this.stock = stock;
        this.valor = valor;
        this.volume = volume;
        this.variacao = variacao;
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
    
    public String getVariacao() {
        return variacao;
    }

    public String getVolume() {
        return volume;
    }

    public AcaoListada getAponta() {
        return aponta;
    }
}
