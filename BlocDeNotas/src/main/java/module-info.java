module nico.blocdenotas {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens nico.blocdenotas to javafx.fxml;
    exports nico.blocdenotas;
}
