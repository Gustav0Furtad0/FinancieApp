package org.openjfx.back;

public class AcaoListada extends Acao {
    protected final Object variacao;
    protected final Object volume;

    public AcaoListada (String nome, double valor, String empresa, Object volume, Object variacao) {
        super(nome, valor, empresa);
        this.variacao = variacao;
        this.volume = volume;
    }

    public Object getVariacao() {
        return variacao;
    }

    public Object getVolume() {
        return volume;
    }

}
