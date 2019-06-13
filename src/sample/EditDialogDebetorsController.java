package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
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

    public double totalDebtDouble;

    Debetors debetor;

    @FXML
    void initialize() {
        cancelEditDebetorBtn.setOnAction(event -> {
            Stage stage = (Stage) cancelEditDebetorBtn.getScene().getWindow();
            stage.close();
        });
    }

    public void saveEdit() throws IOException {
        Stage stage = (Stage) saveEditDebetorBtn.getScene().getWindow();
        //создать новый объект Дебетор
        int idClient = this.debetor.getId();
        this.debetor.setTotalDebt(totalDebtField.getText());
        this.debetor.setLastPayment(lastPaymentField.getText());
        this.debetor.setComments(commentsField.getText());
        //отправляем в БД
        EditDialogTableController editDialogTableController = new EditDialogTableController();
        String sql = "update debetorstable set nameDebetor='" + debetor.getNameDebetor() + "', totalDebt = '" + debetor.getTotalDebt() + "', lastPayment='" +
                debetor.getLastPayment() + "', comments='" + debetor.getComments() + "', telephoneNumber='" + debetor.getTelephonNumber() + "', email='"
                + debetor.getEmail() + "' WHERE id= '" + idClient + "'";
        editDialogTableController.connectForEditDB(sql);
        stage.close();
    }
}
