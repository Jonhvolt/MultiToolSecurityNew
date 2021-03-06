package sample.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import sample.beans.Debetors;
import sample.service.DebetorService;
import sample.service.serviceImpl.DebetorServiceImpl;

import java.net.URL;
import java.util.ResourceBundle;

public class EditDialogDebetorsController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    public Label nameDebetorLabel;

    @FXML
    public Label totalDebtLabel;

    @FXML
    public Label lastPaymentLabel;

    @FXML
    public Label commentsLabel;

    @FXML
    public JFXTextField totalDebtField;

    @FXML
    public JFXTextField lastPaymentField;

    @FXML
    public JFXTextField commentsField;

    @FXML
    private JFXButton saveEditDebetorBtn;

    @FXML
    private JFXButton cancelEditDebetorBtn;

    public Debetors debetor;
    public DebetorsTableController debetorsTableController;
    private DebetorService debetorService = new DebetorServiceImpl();

    @FXML
    void initialize() {
        cancelEditDebetorBtn.setOnAction(event -> {
            Stage stage = (Stage) cancelEditDebetorBtn.getScene().getWindow();
            stage.close();
        });
    }

    public void saveEdit() {
        Stage stage = (Stage) saveEditDebetorBtn.getScene().getWindow();
        this.debetor.setTotal_debt(totalDebtField.getText());
        this.debetor.setLast_payment(lastPaymentField.getText());
        this.debetor.setComments(commentsField.getText());

        debetorService.saveDebetor(debetor);

        debetorsTableController.setTotalDebtLabel();

        stage.close();
    }

}
