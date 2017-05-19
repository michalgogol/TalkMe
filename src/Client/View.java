package Client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class View extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        //Parent root = FXMLLoader.load(getClass().getResource("../Client/theView.fxml"));
        FXMLLoader temp = new FXMLLoader(getClass().getResource("Stage.fxml"));
        Controller control = temp.getController();
        Parent root = (Parent) temp.load();
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();

    }

    public static void main(String[] args) {

        launch(args);
    }
}
