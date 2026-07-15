module sudoku.proyect_50zo {
    requires javafx.controls;
    requires javafx.fxml;


    opens proyect_50zo to javafx.fxml;
    opens proyect_50zo.CONTROLLER to javafx.fxml;

    exports proyect_50zo;
    exports proyect_50zo.CONTROLLER;
    exports proyect_50zo.MODEL;
    exports proyect_50zo.EXCEPTION;
    exports proyect_50zo.HILO;
}