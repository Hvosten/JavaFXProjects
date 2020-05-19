package SimpleCalculator;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("SimpleCalculator.fxml"));
        Scene scene = new Scene(root);

        String cssFile = getClass().getResource("SimpleCalculator.css").toExternalForm();
        scene.getStylesheets().add(cssFile);

        primaryStage.setTitle("Calculator v1.0");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);

        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}