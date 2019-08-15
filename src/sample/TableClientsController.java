package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.beans.Client;
import sample.connection.ConnectToWEB;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class TableClientsController {

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
    private TableColumn<Client, String> collumSimCards;

    @FXML
    private TableColumn<Client, String> collumNumberClients;

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

    @FXML
    private JFXTextField searchJFXTextField;

    Client client;
    ObservableList<Client> listObjectInDB;
    Stage stage;
    Main main = new Main();

    @FXML
    void initialize() {
        tableObject.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        initListeners();
        reloadTableClients();
        filtredSearch();
    }

    public void reloadTableClients() {
        listOfCollums(); //формирует List с объектами типа клиент вытащенными из БД
        insertTable(); //заполняет таблицу данными
        setTotalLabel();
        tableObject.setItems(listObjectInDB); //добавляем колонки с информацией в таблицу
    }

    //берёт данные из БД и создаёт List с данными
    public void listOfCollums() {
        ConnectToWEB connectToWEB = new ConnectToWEB();
        listObjectInDB = FXCollections.observableArrayList(connectToWEB.getConnectToWEB("clients"));
    }

    public void insertTable() {
        collumNameClient.setCellValueFactory(cellData -> cellData.getValue().getterName());
        collumAddressClient.setCellValueFactory(cellData -> cellData.getValue().getterAddress());
        collumContactUser.setCellValueFactory(cellData -> cellData.getValue().getterContactUser());
        collumTelephoneNumber.setCellValueFactory(cellData -> cellData.getValue().getterTelephoneNumber());
        collumEmailClient.setCellValueFactory(cellData -> cellData.getValue().getterEmail());
        collumAreaSecurity.setCellValueFactory(cellData -> cellData.getValue().getterAreaSecutiry());
        collumPriceToMonth.setCellValueFactory(cellData -> cellData.getValue().getterPriceToMonth());
        collumSimCards.setCellValueFactory(cellData -> cellData.getValue().getterSimCards());
        collumNumberClients.setCellValueFactory(cellData -> cellData.getValue().getterNumberClients());
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

        switch (clickedBtn.getId()) {
            case "addBtn":
                try {
                    EditDialogTableController controller = main.showNewWindow("fxml/EditDialogTable.fxml", "Редактирование", 640, 409, Modality.APPLICATION_MODAL).getController();
                    controller.addBtnsave();
                    controller.setBaseClientController(this);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "editBtn":
                if (client == null) break;
                createDialogStage("Редактирование", 650, 434, client);
                break;
            case "delBtn":
                FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/EditDialogTable.fxml"));
                loader.load();
                EditDialogTableController controller = loader.<EditDialogTableController>getController();

                if (client == null) break;

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Внимание");
                alert.setHeaderText("Удалить выбранный объект?");

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {
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

    //создаём окно редактирования
    public void createDialogStage(String title, int width, int height, Client client) throws IOException {
        EditDialogTableController controller = main.showNewWindow("fxml/EditDialogTable.fxml", title, width, height, Modality.APPLICATION_MODAL).getController();
        if (client != null) controller.setClient(client);
    }

    //слушает клики мышью в таблице по выбранному полю
    private void initListeners() {
        tableObject.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.getClickCount() == 2) {  //если два клика
                    try {
                        Client client = tableObject.getSelectionModel().getSelectedItem();
                        createDialogStage("Редактирование", 650, 434, client);
                    } catch (IOException e) {
                    }
                }
            }
        });
    }

    //возвращаемся в главное окно программы
    public void hideWindow() throws IOException {
        stage = (Stage) btnBack.getScene().getWindow();
        stage.close();
        main.showNewWindow("fxml/Sample.fxml", "MultiToolSecurity v0.1", 1000, 700, Modality.NONE);
    }

    //обновляет данные об общем количестве записей в таблице
    public void updateCountLabel() {
        labelCount.setText("");
        labelCount.setText("Объектов: " + listObjectInDB.size());
        setTotalLabel();
    }

    //считает колонки "Сумма ежемесячная" и итог устанавливает в label
    public void setTotalLabel() {
        double totalPrice = 0;
        String temp;
        if (listObjectInDB.size() > 0) {
            for (int x = 0; x < listObjectInDB.size(); x++) {
                temp = listObjectInDB.get(x).getPrice_to_month();
                if (!(temp.equals("") && temp != null)) {
                    //добавить проверку на содержание числа в поле
                    totalPrice = totalPrice + Integer.parseInt(temp);
                } else {
                    totalPrice = totalPrice + 0;
                }
            }
        }
        totalLabel.setText(String.valueOf(totalPrice));
    }

    //метод отвечает за поле поиска и логика фильтра
    public void filtredSearch() {
        ObservableList data = tableObject.getItems();
        searchJFXTextField.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
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