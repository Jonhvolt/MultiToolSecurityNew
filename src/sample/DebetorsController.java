package sample;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.beans.Debetors;
import sample.connection.ConnectToWEB;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Optional;

public class DebetorsController {

    @FXML
    public TableView<Debetors> tableDebetors;

    @FXML
    protected TableColumn<Debetors, String> nameDebetorCollumn;

    @FXML
    private TableColumn<Debetors, String> totalDebtCollumn;

    @FXML
    private TableColumn<Debetors, String> lastPaymentCollumn;

    @FXML
    private TableColumn<Debetors, String> commentsCollumn;

    @FXML
    protected TableColumn<Debetors, String> telephoneDebetorCollumn;

    @FXML
    protected TableColumn<Debetors, String> emailDebetorCollumn;

    @FXML
    private Label totalDebtLabel;

    @FXML
    private JFXButton backToSampleWindow;

    @FXML
    private JFXButton addDebetorBtn;

    @FXML
    private JFXButton editDebetorBtn;

    @FXML
    private JFXButton deleteDebetorBtn;

    @FXML
    private JFXButton unloadingBtn;

    ObservableList<Debetors> listDebetors;
    Debetors debetor;
    Main main = new Main();

    @FXML
    void initialize() {
        listOfCollumsDebetors(); //берёт данные из БД и создаёт List с данными
        insertDebetorsTable();//заполняет таблицу должников данными
        setTotalDebtLabel();
        tableDebetorsListener();

        addDebetorBtn.setOnAction(event -> {
            AddNewDebetorWindowController controller = main.showNewWindow("fxml/AddNewDebetorWindow.fxml", "Добавить должника", 450, 700, Modality.APPLICATION_MODAL).getController();
            controller.setDebetorsController(this);
        });

    }

    public void editDebetor() {
        EditDialogDebetorsController controller;
        Debetors debetor = tableDebetors.getSelectionModel().getSelectedItem();
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
            setTotalDebtLabel();
        }
    }

    public void tableDebetorsListener() {
        tableDebetors.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.getClickCount() == 2) {  //если два клика
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
        debetor = tableDebetors.getSelectionModel().getSelectedItem();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Внимание");
        alert.setHeaderText("Удалить выбранный объект?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            ConnectToWEB connectToWEB = new ConnectToWEB();
            connectToWEB.deleteConnectToWEB(debetor);


            tableDebetors.getItems().remove(debetor);
            setTotalDebtLabel();
        } else {
            alert.close();
        }
    }

    public void addNewDebetorInDBTable(Debetors debetor) {
        ConnectToWEB connectToWEB = new ConnectToWEB();
        connectToWEB.saveConnectToWEB(debetor);
        }

    public void listOfCollumsDebetors() {
        ConnectToWEB connectToWEB = new ConnectToWEB();
        listDebetors = FXCollections.observableArrayList(connectToWEB.getConnectToWEB("debetors"));
    }

    public void insertDebetorsTable() {
        nameDebetorCollumn.setCellValueFactory(cellData -> cellData.getValue().getterNameDebetor());
        totalDebtCollumn.setCellValueFactory(cellData -> cellData.getValue().getterTotalDebt());
        lastPaymentCollumn.setCellValueFactory(cellData -> cellData.getValue().getterLastPayment());
        commentsCollumn.setCellValueFactory(cellData -> cellData.getValue().getterComments());
        telephoneDebetorCollumn.setCellValueFactory(cellData -> cellData.getValue().getterTelephoneNumber());
        emailDebetorCollumn.setCellValueFactory(cellData -> cellData.getValue().getterEmail());
        tableDebetors.setItems(listDebetors);
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
        totalDebtLabel.setText(String.valueOf(totalPrice));
    }

    public void hideWindowDebetors() {
        Stage stage = (Stage) backToSampleWindow.getScene().getWindow();
        stage.close();
        main.showNewWindow("fxml/Sample.fxml", "MultiToolSecurity v0.1", 1000, 700, Modality.NONE);
    }

    public void unloadingReportDebetors() throws IOException {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);
        fileChooser.setTitle("Сохранить отчёт");
        Stage stage = (Stage) unloadingBtn.getScene().getWindow();
        File file = fileChooser.showSaveDialog(stage);
        if (file != null) {
            FileWriter writer = new FileWriter(file);
            writer.write("Общая задолженность: " + totalDebtLabel.getText() + System.lineSeparator());
            writer.write("Список должников: " + System.lineSeparator());

            for (Debetors debetor : listDebetors) {
                writer.write(debetor.getName_debetor() + " - " + debetor.getTotal_debt() + " рублей." + System.lineSeparator());
            }

            writer.close();
        }
    }
}