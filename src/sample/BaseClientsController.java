package sample;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.Predicate;

import com.jfoenix.controls.JFXButton;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sun.security.pkcs11.wrapper.CK_INFO;

public class BaseClientsController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane hidePane;

    @FXML
    public TableView<Client> tableObject;

    @FXML
    private TableColumn<Client, String> collumNameClient;

    @FXML
    private TableColumn<Client, String> collumAddressClient;

    @FXML
    private TableColumn<Client, String> collumTelephoneNumber;

    @FXML
    private TableColumn<Client, String> collumContactUser;

    @FXML
    private TableColumn<Client, String> collumAreaSecurity;

    @FXML
    private TableColumn<Client, String> collumPriceToMonth;

    @FXML
    private TableColumn<Client, String> collumEmailClient;

    @FXML
    private TableColumn<Client, String> collumTheNotes;

    @FXML
    private JFXButton btnBack;

    @FXML
    private JFXButton addBtn;

    @FXML
    private JFXButton editBtn;

    @FXML
    private JFXButton delBtn;

    @FXML
    private Label labelCount;

    @FXML
    private Label totalLabel;

    @FXML
    private TextField searchField;

    Client client;
    ObservableList<Client> listObjectInDB;
    Stage stage;

    @FXML
    void initialize() {
        tableObject.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        initListeners(client);
        reloadTableClients();
        filtredSearch();
    }

    public void reloadTableClients() {
        listOfCollums(); //формирует List с объектами типа клиент вытащенными из БД
        insertTable(); //заполняет таблицу данными
        tableObject.setItems(listObjectInDB); //добавляем колонки с информацией в таблицу
    }

    public void insertTable() {
        collumNameClient.setCellValueFactory(cellData -> cellData.getValue().getterName());
        collumAddressClient.setCellValueFactory(cellData -> cellData.getValue().getterAddress());
        collumContactUser.setCellValueFactory(cellData -> cellData.getValue().getterContactUser());
        collumTelephoneNumber.setCellValueFactory(cellData -> cellData.getValue().getterTelephoneNumber());
        collumEmailClient.setCellValueFactory(cellData -> cellData.getValue().getterEmail());
        collumAreaSecurity.setCellValueFactory(cellData -> cellData.getValue().getterAreaSecutiry());
        collumPriceToMonth.setCellValueFactory(cellData -> cellData.getValue().getterPriceToMonth());
        collumTheNotes.setCellValueFactory(cellData -> cellData.getValue().getterNotes());
        //меняет запись об общем колличестве объектов
        updateCountLabel();
    }

    public void editBtnThisTableObject(ActionEvent actionEvent) throws Exception {
        Object source = actionEvent.getSource();
        //если нажата не кнопка, то выходим из метода
        if (!(source instanceof Button)) {
            return;
        }
        Button clickedBtn = (Button) source;
        client = tableObject.getSelectionModel().getSelectedItem();

        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/EditDialogTable.fxml"));
        Parent parent = loader.load();
        stage.setTitle("Редактирование");
        stage.setResizable(false);
        stage.setScene(new Scene(parent, 640, 409));
        stage.initModality(Modality.APPLICATION_MODAL);
        EditDialogTableController controller = loader.<EditDialogTableController>getController();
        switch (clickedBtn.getId()) {
            case "addBtn":
                try {
                    controller.addBtnsave();
                    controller.setBaseClientController(this);
                    stage.showAndWait();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "editBtn":
                if (client == null) break;
                createDialogStage("Редактирование", 650, 434, client);
                break;
            case "delBtn":
                if (client == null) break;

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Внимание");
                alert.setHeaderText("Удалить выбранный объект?");

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK){
                   tableObject.getItems().remove(client);
                    updateCountLabel();
                } else {
                    alert.close();
                    break;
                }

                controller.deleteClient(client);
                break;
        }
    }

    //метод берёт данные из БД и создаёт List с данными
    public void listOfCollums() {
        try {
            listObjectInDB = FXCollections.observableArrayList();
            ConnectionToDB connect = new ConnectionToDB();
            connect.connectionToDBnote();
            Statement statement = connect.cnt.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from baseobjecttable");

            while (resultSet.next()) {
                Client client = new Client();
                client.setId(resultSet.getInt("id"));
                client.setName(resultSet.getString("name"));
                client.setAddress(resultSet.getString("address"));
                client.setContactUser(resultSet.getString("contactUser"));
                client.setTelephoneNumber(resultSet.getString("telephoneNumber"));
                client.setEmail(resultSet.getString("email"));
                client.setAreaSecurity(resultSet.getString("areaSecurity"));
                client.setPriceToMonth(resultSet.getString("priceToMonth"));
                client.setNotes(resultSet.getString("notes"));
                listObjectInDB.add(client);
            }
            connect.cnt.close();
        } catch (Exception e) {

        }

    }

    //создаём окно редактирования
    public void createDialogStage (String title, int width, int height, Client client) throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/EditDialogTable.fxml"));
        Parent parent = loader.load();
        Scene scene = new Scene(parent, width, height);
        stage.setTitle(title);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);

        EditDialogTableController controller = loader.<EditDialogTableController>getController();
        if (client != null) controller.setClient(client);
        stage.showAndWait();
    }

    //слушает клики мышью в таблице по выбранному полю
    private void initListeners(Client client) {
        tableObject.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.getClickCount() == 2) {  //если два клика
                    try{
                        Client client = tableObject.getSelectionModel().getSelectedItem();
                        createDialogStage("Редактирование", 650, 434, client);
                    } catch (IOException e) {
                    }
                }
                if (mouseEvent.getButton() == MouseButton.SECONDARY) {
                    //по нажатию правой кнопки мыши устанавливаем цвет
                }
            }
        });
    }

    //возвращаемся в главное окно программы
    public void hideWindow() throws IOException {
        stage = (Stage) btnBack.getScene().getWindow();
        stage.close();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/sample.fxml"));
        Parent root = loader.load();
        stage.setResizable(false);
        stage.setTitle("MultiToolSecurity v0.1");
        stage.setScene(new Scene(root, 1000, 700));
        stage.show();
    }

    //обновляет данные об общем количестве записей в таблице
    public void updateCountLabel() {
        labelCount.setText("");
        labelCount.setText("Объектов: " + listObjectInDB.size());
    }

    //метод считает колонки "Сумма ежемесячная" и итог устанавливает в label
    public void setTotalLabel() {
        int total = 0;
        for (int x = 0; x < listObjectInDB.size() - 1; x++) {
           total += Integer.parseInt(String.valueOf(listObjectInDB.get(x)) + String.valueOf(listObjectInDB.get(x + 1)));
        }

        totalLabel.setText(String.valueOf(total));
    }

    //метод отвечает за поле поиска и логика фильтра
    public void filtredSearch() {
        ObservableList data =  tableObject.getItems();
        searchField.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (oldValue != null && (newValue.length() < oldValue.length())) {
                tableObject.setItems(data);
            }
            String value = newValue.toLowerCase();
            ObservableList<Client> subentries = FXCollections.observableArrayList();

            long count = tableObject.getColumns().stream().count();
            for (int i = 0; i < tableObject.getItems().size(); i++) {
                for (int j = 0; j < count; j++) {
                    String entry = "" + tableObject.getColumns().get(j).getCellData(i);
                    if (entry.toLowerCase().contains(value)) {
                        subentries.add(tableObject.getItems().get(i));
                        break;
                    }
                }
            }
            tableObject.setItems(subentries);
        });
    }

}
