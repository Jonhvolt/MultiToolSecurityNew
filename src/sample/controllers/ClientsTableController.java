package sample.controllers;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import sample.EditDialogTableController;
import sample.Main;
import sample.SampleController;
import sample.beans.Client;
import sample.connection.ConnectToWEB;

import java.io.IOException;
import java.util.Optional;

public class ClientsTableController {
    Client client;
    ObservableList<Client> listObjectInDB;
    Main main = new Main();


    private SampleController sampleController;

    public ClientsTableController(SampleController sampleController) {
        this.sampleController = sampleController;

        this.sampleController.tableObject.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        initListeners();
        reloadTableClients();
        filtredSearch();
    }

    public void reloadTableClients() {
        listOfCollums(); //формирует List с объектами типа клиент вытащенными из БД
        insertTable(); //заполняет таблицу данными
        setTotalLabel();
        sampleController.tableObject.setItems(listObjectInDB); //добавляем колонки с информацией в таблицу
    }

    //берёт данные из БД и создаёт List с данными
    public void listOfCollums() {
        ConnectToWEB connectToWEB = new ConnectToWEB();
        listObjectInDB = FXCollections.observableArrayList(connectToWEB.getClient("clients"));
    }

    public void insertTable() {
        sampleController.collumNameClient.setCellValueFactory(cellData -> cellData.getValue().getterName());
        sampleController.collumAddressClient.setCellValueFactory(cellData -> cellData.getValue().getterAddress());
        sampleController.collumContactUser.setCellValueFactory(cellData -> cellData.getValue().getterContactUser());
        sampleController.collumTelephoneNumber.setCellValueFactory(cellData -> cellData.getValue().getterTelephoneNumber());
        sampleController.collumEmailClient.setCellValueFactory(cellData -> cellData.getValue().getterEmail());
        sampleController.collumAreaSecurity.setCellValueFactory(cellData -> cellData.getValue().getterAreaSecutiry());
        sampleController.collumPriceToMonth.setCellValueFactory(cellData -> cellData.getValue().getterPriceToMonth());
        sampleController.collumSimCards.setCellValueFactory(cellData -> cellData.getValue().getterSimCards());
        sampleController.collumNumberClients.setCellValueFactory(cellData -> cellData.getValue().getterNumberClients());
        sampleController.collumTheNotes.setCellValueFactory(cellData -> cellData.getValue().getterNotes());
        sampleController.collumnStatus.setCellValueFactory(cellData -> cellData.getValue().getterStatusContract());
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
        client = sampleController.tableObject.getSelectionModel().getSelectedItem();

        switch (clickedBtn.getId()) {
            case "addBtn":
                try {
                    EditDialogTableController controller = main.showNewWindow("fxml/EditDialogTable.fxml", "Редактирование", 640, 409, Modality.APPLICATION_MODAL).getController();
                    controller.setBaseClientController(this);
                    controller.addBtnsave();
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
                if (result.get() == ButtonType.OK) {
                    EditDialogTableController editDialogTableController = new EditDialogTableController();
                    editDialogTableController.deleteClient(client);

                    sampleController.tableObject.getItems().remove(client);
                    updateCountLabel();
                } else {
                    alert.close();
                    break;
                }

                break;
        }
    }

    //создаём окно редактирования
    public void createDialogStage(String title, int width, int height, Client client) throws IOException {
        EditDialogTableController controller = main.showNewWindow("fxml/EditDialogTable.fxml", title, width, height, Modality.APPLICATION_MODAL).getController();

        if (client != null) controller.setClient(client, this);
    }

    /**
     * слушает клики мышью в таблице по выбранному полю
     * инициализирует все слушатели кнопок в таблице
     */
    private void initListeners() {
        sampleController.tableObject.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.getClickCount() == 2 && mouseEvent.getButton() == MouseButton.PRIMARY) {  //если два клика
                    try {
                        Client client = sampleController.tableObject.getSelectionModel().getSelectedItem();
                        createDialogStage("Редактирование", 650, 434, client);
                    } catch (IOException e) {
                    }
                }
            }
        });

        sampleController.editBtn.setOnAction(event -> {
            try {
                editBtnThisTableObject(event);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        sampleController.addBtn.setOnAction(event -> {
            try {
                editBtnThisTableObject(event);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        sampleController.delBtn.setOnAction(event -> {
            try {
                editBtnThisTableObject(event);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    //обновляет данные об общем количестве записей в таблице
    public void updateCountLabel() {
        sampleController.labelCount.setText("");
        sampleController.labelCount.setText("Объектов: " + listObjectInDB.size());
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
        sampleController.totalLabel.setText(String.valueOf(totalPrice));
    }

    //метод отвечает за поле поиска и логика фильтра
    public void filtredSearch() {
        ObservableList data = sampleController.tableObject.getItems();
        sampleController.searchJFXTextField.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (oldValue != null && (newValue.length() < oldValue.length())) {
                sampleController.tableObject.setItems(data);
            }
            String value = newValue.toLowerCase();
            ObservableList<Client> subentries = FXCollections.observableArrayList();

            long count = sampleController.tableObject.getColumns().stream().count();
            for (int i = 0; i < sampleController.tableObject.getItems().size(); i++) {
                for (int j = 0; j < count; j++) {
                    String entry = "" + sampleController.tableObject.getColumns().get(j).getCellData(i);
                    if (entry.toLowerCase().contains(value)) {
                        subentries.add(sampleController.tableObject.getItems().get(i));
                        break;
                    }
                }
            }
            sampleController.tableObject.setItems(subentries);
        });
    }

}
