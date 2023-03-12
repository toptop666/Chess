package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;



public class Main extends Application {

    public static int SCREEN_HEIGHT = 1000;
    public static int SCREEN_LENGTH = 1000;

    public static int NUMBER_OF_CELLS = 8;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent rootMenu = FXMLLoader.load(getClass().getResource("menu.fxml"));
        primaryStage.setTitle("Chess");
        primaryStage.setScene(new Scene(rootMenu, SCREEN_LENGTH, SCREEN_HEIGHT));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
