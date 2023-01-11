package org.openjfx.back;

import java.time.LocalDate;
import java.util.List;

/*Alunos:
 * Gustavo de Jesus Furtado ,   202176024;
 * Pedro Andrade Pereira Leão , 202035008.
 */

public interface Vendas {
    public default void compraAcao(AcaoListada acaoCompra, LocalDate dataCompra, int quantidade) { }

    public default void vendeAcao(AcaoComprada acaoVende, int qtd) { }

    public default void updateCarteira(List<AcaoListada> acoesList) { }
}
