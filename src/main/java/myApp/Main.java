package myApp;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;


public class Main extends Application {



    @Override
    public void start(Stage stage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("/fxml/myApp.fxml"));
        stage.setTitle("2048");
        stage.setResizable(false);
        stage.setScene(new Scene(root, 450, 568));
        stage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }

}
