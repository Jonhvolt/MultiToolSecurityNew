package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import java.net.URL;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.stage.Stage;

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
    private JFXTextField notesField;

    @FXML
    private JFXButton saveBtnEditTable;

    @FXML
    private JFXButton cancelBtn;

    Client client;
    ConnectionToDB connect = new ConnectionToDB();
    private BaseClientsController baseClient;

    @FXML
    void initialize() {
        cancelBtn.setOnAction(event -> {
            Stage stage = (Stage) cancelBtn.getScene().getWindow();
            stage.close();
        });

        saveBtnEditTable.setOnAction(actionEvent -> {
            editClient(editClientItem());
            Stage stage = (Stage) saveBtnEditTable.getScene().getWindow();
            stage.close();
        });
    }

    //метод сохраняет в БД нового клиента и закрывает окно редактирования
    public void addBtnsave(){
        saveBtnEditTable.setOnAction(actionEvent -> {
            Stage stage = (Stage) saveBtnEditTable.getScene().getWindow();
            addClient();
            this.baseClient.reloadTableClients();
            stage.close();
        });
    }

    //метод заполняет данными поля в окне редактирования, при нажатии кнопки "Изменить"
    public void setClient(Client client) {
        if (client == null) {
            return;
        }
        this.client = client;
        nameField.setText(this.client.getName());
        addressField.setText(this.client.getAddress());
        userField.setText(this.client.getContactUser());
        telephoneNumberField.setText(this.client.getTelephoneNumber());
        emailField.setText(this.client.getEmail());
        areaField.setText(this.client.getAreaSecurity());
        priceToMonthField.setText(this.client.getPriceToMonth());
        notesField.setText(this.client.getNotes());
    }

    //метод создаёт объект типа Client с данными отредактированными пользователем после нажатия кнопки Изменить
    public Client editClientItem() {
        client.setName(nameField.getText());
        client.setAddress(addressField.getText());
        client.setContactUser(userField.getText());
        client.setTelephoneNumber(telephoneNumberField.getText());
        client.setEmail(emailField.getText());
        client.setAreaSecurity(areaField.getText());
        client.setPriceToMonth(priceToMonthField.getText());
        client.setNotes(notesField.getText());
        return client;
    }

    //метод закрывате окно редактирования при нажатии кнопки "Отмена"
    public void actionClose(javafx.event.ActionEvent actionEvent) {
        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    //берём id выбранного поля в таблицы и удаляем его из БД
    public void deleteClient(Client client){
        int idDeleteClient = client.getId();
        String sql = "delete from baseobjecttable where id= '"+idDeleteClient+"'";
        connectForEditDB(sql);
    }

    public void editClient(Client client){
        int idClient = client.getId();
        String sql = "update baseobjecttable set name='"+client.getName()+"', address = '"+client.getAddress()+"', contactUser='"+client.getContactUser()+"', telephoneNumber='"+client.getTelephoneNumber()+"', email='"+client.getEmail()+"', areaSecurity='"+client.getAreaSecurity()+"', priceToMonth='"+client.getPriceToMonth()+"', notes='"+client.getNotes()+"' WHERE id= '"+idClient+"'";
        connectForEditDB(sql);
    }

    //при нажатии кнопки сохранить, отправляем нового клиента в БД
    public void addClient() {
        String sql = "INSERT INTO baseobjecttable (name, address, contactUser, telephoneNumber, email, areaSecurity, priceToMonth, notes)"
                + " VALUES ('"+nameField.getText()+"', '"+addressField.getText()+"', '"+userField.getText()+"', '"+telephoneNumberField.getText()+"', '"+emailField.getText()+"', '"+areaField.getText()+"', '"+priceToMonthField.getText()+"', '"+notesField.getText()+"')";
        connectForEditDB(sql);
    }

    public void connectForEditDB(String sql) {
        //метод получает строку запроса sql и формирует запрос к БД
        try {
            connect.connectionToDBnote();
            PreparedStatement statement = connect.cnt.prepareStatement(sql);
            statement.executeUpdate();
            connect.cnt.close();
            System.out.println("Соединение с БД закрыто");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setBaseClientController (BaseClientsController baseClient) {
        this.baseClient = baseClient;
    }
}
