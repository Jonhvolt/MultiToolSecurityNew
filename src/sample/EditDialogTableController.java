package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.stage.Stage;
import sample.beans.Client;
import sample.connection.ConnectToWEB;
import sample.controllers.ClientsTableController;

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
    private SplitMenuButton statusContractMenu;

    @FXML
    private MenuItem contractSignItem;

    @FXML
    private MenuItem contractNonSignItem;

    @FXML
    private Label statusContractLabel;

    @FXML
    private JFXButton saveBtnEditTable;

    @FXML
    private JFXButton cancelBtn;

    Client client;
    ConnectToWEB connectToWEB = new ConnectToWEB();
    private ClientsTableController clientsTableController;

    @FXML
    void initialize() {
        cancelBtn.setOnAction(event -> {
            Stage stage = (Stage) cancelBtn.getScene().getWindow();
            stage.close();
        });

        saveBtnEditTable.setOnAction(actionEvent -> {
            Stage stage = (Stage) saveBtnEditTable.getScene().getWindow();
            editClient(editClientItem());
            stage.close();

            clientsTableController.setTotalLabel();
        });

        contractSignItem.setOnAction(event -> {
            statusContractLabel.setText("Подписан");
        });

        contractNonSignItem.setOnAction(event -> {
            statusContractLabel.setText("Не подписан");
        });
    }

    //сохраняет в БД нового клиента и закрывает окно редактирования
    public void addBtnsave() {
        saveBtnEditTable.setOnAction(actionEvent -> {
            Stage stage = (Stage) saveBtnEditTable.getScene().getWindow();
            addClient();
            clientsTableController.reloadTableClients();
            stage.close();
        });
    }

    //заполняет данными поля в окне редактирования, при нажатии кнопки "Изменить"
    public void setClient(Client client, ClientsTableController tableClientController) {
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

            if (this.client.getStatus_contract().equals("1")) {
                statusContractLabel.setText("Подписан");
            } else {
                statusContractLabel.setText("Не подписан");
            }

            this.clientsTableController = tableClientController;
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

        if (statusContractLabel.getText().equals("Подписан")) {
            client.setStatus_contract(String.valueOf(1));
        } else {
            client.setStatus_contract(String.valueOf(0));
        }

        return client;
    }

    //закрывате окно редактирования при нажатии кнопки "Отмена"
    public void actionClose(javafx.event.ActionEvent actionEvent) {
        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    public void deleteClient(Client client) {
        connectToWEB.deleteClient(client);
    }

    public void editClient(Client client) {
        connectToWEB.saveClient(client);
    }

    //при нажатии кнопки сохранить, отправляем нового клиента в БД
    public void addClient() {
        Client client = new Client(nameField.getText(), addressField.getText(), userField.getText(), telephoneNumberField.getText(), emailField.getText(), areaField.getText(), priceToMonthField.getText(), notesField.getText(), simCardsField.getText(), numberClientsField.getText(), String.valueOf(0));
        connectToWEB.saveClient(client);
    }

//    public void setBaseClientController(TableClientsController baseClient) {
//        this.baseClient = baseClient;
//    }

    public void setBaseClientController(ClientsTableController clientController) {
        this.clientsTableController = clientController;
    }
}
