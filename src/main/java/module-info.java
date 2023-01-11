module full.openjfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.json;
    requires javax.json;

    opens org.openjfx to javafx.fxml;
    exports org.openjfx;
    exports org.openjfx.back;
    exports org.openjfx.front;
    exports org.openjfx.ReadJson;
    exports org.openjfx.ArchiveRW;
}
/*Alunos:
 * Gustavo de Jesus Furtado ,   202176024;
 * Pedro Andrade Pereira Leão , 202035008.
 */
