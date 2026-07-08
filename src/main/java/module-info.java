module proyect_50zo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens proyect_50zo to javafx.fxml;
    opens proyect_50zo.CONTROLLER to javafx.fxml;
    opens proyect_50zo.UTILS to javafx.fxml;
    opens proyect_50zo.MODEL to javafx.fxml;

    exports proyect_50zo;
    exports proyect_50zo.CONTROLLER;
    exports proyect_50zo.UTILS;
    exports proyect_50zo.MODEL;
}