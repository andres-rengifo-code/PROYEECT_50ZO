module sudoku.proyect_50zo {
    requires javafx.controls;
    requires javafx.fxml;


    opens sudoku.proyect_50zo to javafx.fxml;
    opens sudoku.proyect_50zo.CONTROLLER to javafx.fxml;

    exports sudoku.proyect_50zo;
    exports sudoku.proyect_50zo.CONTROLLER;
}