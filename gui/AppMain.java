package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class AppMain extends Application {



    @Override
    public void start(Stage stage) throws Exception {


        Parent root = new FXMLLoader(AppMain.class.getResource("menu_accueil.fxml")).load();
        stage.setTitle("Ricochet Robots!");

        stage.setScene(new Scene(root, 760, 760));
        stage.show();


    }

    public static void main(String[] args) {
        launch();
    }
}