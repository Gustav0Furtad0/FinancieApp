module full.openjfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.json;
    requires javax.json;

    opens org.openjfx.front to javafx.fxml;
    exports org.openjfx.front;
    exports org.openjfx.back;
}
