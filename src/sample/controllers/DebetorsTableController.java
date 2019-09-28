package sample.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.Main;
import sample.beans.Debetors;
import sample.connection.ConnectToWEBImpl;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Optional;

public class DebetorsTableController {

    private SampleController sampleController;
    private Main main = new Main();
    private Debetors debetor;
    private ObservableList<Debetors> listDebetors;

    public DebetorsTableController(SampleController sampleController) {
        this.sampleController = sampleController;

        listOfCollumsDebetors(); //берёт данные из БД и создаёт List с данными
        insertDebetorsTable();//заполняет таблицу должников данными
        setTotalDebtLabel();
        tableDebetorsListener();

        this.sampleController.addDebetorBtn.setOnAction(event -> {
            AddNewDebetorWindowController controller = main.showNewWindow("fxml/AddNewDebetorWindow.fxml", "Добавить должника", 450, 700, Modality.APPLICATION_MODAL).getController();
            controller.setDebetorsController(this);
        });

        this.sampleController.unloadingBtn.setOnAction(event -> {
            try {
                unloadingReportDebetors();
            } catch (IOException e) {
                System.out.println("Ошибка при сохранении файла  " + e);
                e.printStackTrace();
            }
        });

        this.sampleController.deleteDebetorBtn.setOnAction(event -> {
            deleteDebetor();
        });

        this.sampleController.editDebetorBtn.setOnAction(event -> {
            editDebetor();
        });

    }

    public void editDebetor() {
        EditDialogDebetorsController controller;
        Debetors debetor = sampleController.tableDebetors.getSelectionModel().getSelectedItem();
        if (debetor == null) return;
        else {
            controller = main.showNewWindow("fxml/EditDialogDebetors.fxml", "Редактирование", 600, 434, Modality.APPLICATION_MODAL).getController();
            controller.nameDebetorLabel.setText(debetor.getName_debetor());
            if (!(debetor.getTotal_debt() == null) && !(debetor.getTotal_debt().equals(""))) {
                controller.totalDebtField.setText(debetor.getTotal_debt());
            }
            if (!(debetor.getLast_payment() == null) && !(debetor.getLast_payment().equals(""))) {
                controller.lastPaymentField.setText(debetor.getLast_payment());
            }
            if (!(debetor.getComments() == null) && !(debetor.getComments().equals(""))) {
                controller.commentsField.setText(debetor.getComments());
            }
            controller.debetor = debetor;
            controller.debetorsTableController = this;

            setTotalDebtLabel();
        }
    }

    public void tableDebetorsListener() {
        sampleController.tableDebetors.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.getClickCount() == 2 && mouseEvent.getButton() == MouseButton.PRIMARY) {  //если два клика
                    editDebetor();
                }
            }
        });
    }

    public void reloadTableDebetors() {
        listOfCollumsDebetors();
        insertDebetorsTable();
    }

    public void putNewDebetor(Debetors debetor) {
        addNewDebetorInDBTable(debetor);
        listOfCollumsDebetors();
    }

    public void deleteDebetor() {
        debetor = sampleController.tableDebetors.getSelectionModel().getSelectedItem();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Внимание");
        alert.setHeaderText("Удалить выбранный объект?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            ConnectToWEBImpl connectToWEB = new ConnectToWEBImpl();
            connectToWEB.deleteClient(debetor);


            sampleController.tableDebetors.getItems().remove(debetor);
            setTotalDebtLabel();
        } else {
            alert.close();
        }
    }

    public void addNewDebetorInDBTable(Debetors debetor) {
        ConnectToWEBImpl connectToWEB = new ConnectToWEBImpl();
        connectToWEB.saveClient(debetor);
    }

    public void listOfCollumsDebetors() {
        ConnectToWEBImpl connectToWEB = new ConnectToWEBImpl();
        listDebetors = FXCollections.observableArrayList(connectToWEB.getClient("debetors"));
    }

    public void insertDebetorsTable() {
        sampleController.nameDebetorCollumn.setCellValueFactory(cellData -> cellData.getValue().getterNameDebetor());
        sampleController.totalDebtCollumn.setCellValueFactory(cellData -> cellData.getValue().getterTotalDebt());
        sampleController.lastPaymentCollumn.setCellValueFactory(cellData -> cellData.getValue().getterLastPayment());
        sampleController.commentsCollumn.setCellValueFactory(cellData -> cellData.getValue().getterComments());
        sampleController.telephoneDebetorCollumn.setCellValueFactory(cellData -> cellData.getValue().getterTelephoneNumber());
        sampleController.emailDebetorCollumn.setCellValueFactory(cellData -> cellData.getValue().getterEmail());
        sampleController.tableDebetors.setItems(listDebetors);
    }

    public void setTotalDebtLabel() {
        double totalPrice = 0;
        String temp;
        if (listDebetors.size() > 0) {
            for (int x = 0; x < listDebetors.size(); x++) {
                temp = listDebetors.get(x).getTotal_debt();
                if (!(temp.equals("")) && temp != null) {
                    //добавить проверку на содержание числа в поле
                    totalPrice = totalPrice + Integer.parseInt(temp);
                } else {
                    totalPrice = totalPrice + 0;
                }
            }
        }
        sampleController.totalDebtLabel.setText(String.valueOf(totalPrice));
    }

    public void unloadingReportDebetors() throws IOException {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);
        fileChooser.setTitle("Сохранить отчёт");
        Stage stage = (Stage) sampleController.unloadingBtn.getScene().getWindow();
        File file = fileChooser.showSaveDialog(stage);
        if (file != null) {
            FileWriter writer = new FileWriter(file);
            writer.write("Общая задолженность: " + sampleController.totalDebtLabel.getText() + System.lineSeparator());
            writer.write("Список должников: " + System.lineSeparator());

            for (Debetors debetor : listDebetors) {
                writer.write(debetor.getName_debetor() + " - " + debetor.getTotal_debt() + " рублей." + System.lineSeparator());
            }

            writer.close();
        }
    }
}
