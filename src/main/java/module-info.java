module com.example.cafe {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens com.example.cafe to javafx.fxml;
    exports com.example.cafe;
}