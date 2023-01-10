package org.openjfx.back;

import java.time.LocalDate;
import java.util.List;

public interface Vendas {
    public default void compraAcao(Acao acaoCompra, LocalDate dataCompra, int quantidade) { }

    public default void vendeAcao(AcaoComprada acaoVende, int qtd) { }

    public default void updateCarteira(List<Acao> acoesList) { }
}
