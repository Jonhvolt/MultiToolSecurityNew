package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.stage.Stage;
import sample.beans.Client;
import sample.connection.ConnectToWEB;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EditDialogTableController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXTextField nameField;

    @FXML
    private JFXTextField addressField;

    @FXML
    private JFXTextField userField;

    @FXML
    private JFXTextField telephoneNumberField;

    @FXML
    private JFXTextField emailField;

    @FXML
    private JFXTextField areaField;

    @FXML
    private JFXTextField priceToMonthField;

    @FXML
    private JFXTextField simCardsField;

    @FXML
    private JFXTextField numberClientsField;

    @FXML
    private JFXTextField notesField;

    @FXML
    private JFXButton saveBtnEditTable;

    @FXML
    private JFXButton cancelBtn;

    Client client;
    ConnectToWEB connectToWEB = new ConnectToWEB();
    private TableClientsController baseClient;

    @FXML
    void initialize() {
        cancelBtn.setOnAction(event -> {
            Stage stage = (Stage) cancelBtn.getScene().getWindow();
            stage.close();
        });

        saveBtnEditTable.setOnAction(actionEvent -> {
            editClient(editClientItem());
            Stage stage = (Stage) saveBtnEditTable.getScene().getWindow();

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/BaseClients.fxml"));
                loader.load();
                baseClient = loader.getController();

            } catch (IOException e) {
                System.out.println(e);
            }

            stage.close();
        });
    }

    //сохраняет в БД нового клиента и закрывает окно редактирования
    public void addBtnsave() {
        saveBtnEditTable.setOnAction(actionEvent -> {
            Stage stage = (Stage) saveBtnEditTable.getScene().getWindow();
            addClient();
            this.baseClient.reloadTableClients();
            stage.close();
        });
    }

    //заполняет данными поля в окне редактирования, при нажатии кнопки "Изменить"
    public void setClient(Client client) {
            if (client == null) {
                return;
            }
            this.client = client;
            nameField.setText(this.client.getName());
            addressField.setText(this.client.getAddress());
            userField.setText(this.client.getContact_user());
            telephoneNumberField.setText(this.client.getTelephone_number());
            emailField.setText(this.client.getEmail());
            areaField.setText(this.client.getArea_security());
            priceToMonthField.setText(this.client.getPrice_to_month());
            simCardsField.setText(this.client.getSim_cards());
            numberClientsField.setText(this.client.getNumber_clients());
            notesField.setText(this.client.getNotes());

    }

    //создаёт объект типа Client с данными отредактированными пользователем после нажатия кнопки Изменить
    public Client editClientItem() {
        client.setName(nameField.getText());
        client.setAddress(addressField.getText());
        client.setContact_user(userField.getText());
        client.setTelephone_number(telephoneNumberField.getText());
        client.setEmail(emailField.getText());
        client.setArea_security(areaField.getText());
        client.setPrice_to_month(priceToMonthField.getText());
        client.setSim_cards(simCardsField.getText());
        client.setNumber_clients(numberClientsField.getText());
        client.setNotes(notesField.getText());
        return client;
    }

    //закрывате окно редактирования при нажатии кнопки "Отмена"
    public void actionClose(javafx.event.ActionEvent actionEvent) {
        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    public void deleteClient(Client client) {
        connectToWEB.deleteConnectToWEB(client);
    }

    public void editClient(Client client) {
        connectToWEB.saveConnectToWEB(client);
    }

    //при нажатии кнопки сохранить, отправляем нового клиента в БД
    public void addClient() {
        Client client = new Client(nameField.getText(), addressField.getText(), userField.getText(), telephoneNumberField.getText(), emailField.getText(), areaField.getText(), priceToMonthField.getText(), notesField.getText(), simCardsField.getText(), numberClientsField.getText());
        connectToWEB.saveConnectToWEB(client);
    }

    public void setBaseClientController(TableClientsController baseClient) {
        this.baseClient = baseClient;
    }
}
