package sample;

import javafx.application.Application;
import javafx.embed.swing.JFXPanel;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("fxml/Sample.fxml"));
        primaryStage.setTitle("MultiToolSecurity v0.1");
        primaryStage.setScene(new Scene(root, 1000, 700));
        primaryStage.setResizable(false);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

    public FXMLLoader showNewWindow(String fxmlAddress, String title, int width, int height, Modality modality) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlAddress));
        try {
            Parent parent = fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(modality);
            stage.setTitle(title);
            stage.setScene(new Scene(parent, width, height));
            stage.show();
        } catch (IOException e) {
            System.out.println(e);
        }
        return fxmlLoader;
    }

    public JFXPanel createPanel(String title, int width, int height) {
        JFXPanel panel = new JFXPanel();
        panel.setName(title);
        Dimension dimension = new Dimension(width, height);
        panel.setSize(dimension);

        return panel;
    }
}
