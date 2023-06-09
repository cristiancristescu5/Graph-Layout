module com.example.demo1 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires com.almasb.fxgl.all;
    requires graph4j;
    requires java.desktop;
    requires javafx.swing;

    opens com.example.demo1 to javafx.fxml;
    exports com.example.demo1;
    exports com.example.demo1.Controllers;
    opens com.example.demo1.Controllers to javafx.fxml;
}