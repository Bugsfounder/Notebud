module com.bugsfounder.notebud {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;

    opens com.bugsfounder.notebud to javafx.fxml;
    exports com.bugsfounder.notebud;
}