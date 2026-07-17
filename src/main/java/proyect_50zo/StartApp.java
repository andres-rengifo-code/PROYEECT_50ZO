package proyect_50zo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import proyect_50zo.UTILS.InsertScene;
import proyect_50zo.UTILS.Paths;

import java.io.IOException;

public class StartApp extends Application {


    public static void main(String[] args){
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {

        stage.setTitle("El Cincuentazo");
        stage.setMinWidth(500);
        stage.setMinHeight(400);

        InsertScene.setStage(stage);
        InsertScene.setScene( Paths.INICIO);

    }
}