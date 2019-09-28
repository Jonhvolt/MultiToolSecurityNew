package sample.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import sample.beans.SimCard;

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
    private JFXButton cancelEditDebetorBtn;

    SimCard simCard;
    SimCardController simCardController;

    @FXML
    void saveEditSimCard(ActionEvent event) {
        saveEdit();
    }

    @FXML
    void initialize() {

    }

    public SimCard saveEdit() {
        simCard.setKit(kitField.getText());
        simCard.setNumber_one(simOneField.getText());
        simCard.setNumber_two(simTwoField.getText());
        simCard.setThe_note(commentsField.getText());

        return simCard;
    }

    public void setSimCardController(SimCardController simCardController) {
        this.simCardController = simCardController;
    }
}
