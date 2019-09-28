package sample.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import sample.beans.SimCard;
import sample.connection.ConnectToWEBImpl;

import java.net.URL;
import java.util.ResourceBundle;

public class EditDialogSimCardController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    public Label nameClientForSimLabel;

    @FXML
    public JFXTextField kitField;

    @FXML
    public JFXTextField simOneField;

    @FXML
    public JFXTextField simTwoField;

    @FXML
    public JFXTextField commentsField;

    @FXML
    private JFXButton saveEditDebetorBtn;

    @FXML
    private JFXButton cancelEditSimCardBtn;

    SimCard simCard;
    SimCardController simCardController;

    @FXML
    void initialize() {
        saveEditDebetorBtn.setOnAction(event -> {
            saveEdit();
        });

        cancelEditSimCardBtn.setOnAction(event -> {
            Stage stage = (Stage) cancelEditSimCardBtn.getScene().getWindow();
            stage.close();
        });
    }

    public void saveEdit() {
        Stage stage = (Stage) saveEditDebetorBtn.getScene().getWindow();

        this.simCard.setKit(kitField.getText());
        this.simCard.setNumber_one(simOneField.getText());
        this.simCard.setNumber_two(simTwoField.getText());
        this.simCard.setThe_note(commentsField.getText());

        ConnectToWEBImpl connectToWEB = new ConnectToWEBImpl();
        connectToWEB.saveSimCard(this.simCard);

        stage.close();
    }
}
