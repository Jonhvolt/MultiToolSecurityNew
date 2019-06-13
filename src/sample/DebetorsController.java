package sample;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.Statement;
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
    private AddNewDebetorWindowController addNewDebetorWindowController;

    EditDialogTableController editDialogTableController = new EditDialogTableController();

    @FXML
    void initialize() {
        listOfCollumsDebetors(); //берёт данные из БД и создаёт List с данными
        insertDebetorsTable();//заполняет таблицу должников данными
        setTotalDebtLabel();
        tableDebetorsListener();

        addDebetorBtn.setOnAction(event -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/AddNewDebetorWindow.fxml"));
                Parent parent = loader.load();
                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setTitle("Добавить должника");
                stage.setScene(new Scene(parent, 450, 700));
                stage.show();
                AddNewDebetorWindowController controller = loader.<AddNewDebetorWindowController>getController();
                controller.setDebetorsController(this);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }

    public void editDebetor() {
        EditDialogDebetorsController controller;
        Debetors debetor = tableDebetors.getSelectionModel().getSelectedItem();
        if (debetor == null) return;
        else {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/editDialogDebetors.fxml"));
                Parent root = fxmlLoader.load();
                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setTitle("Редактирование");
                stage.setScene(new Scene(root, 600, 434));

                controller = fxmlLoader.getController();
                controller.nameDebetorLabel.setText(debetor.getNameDebetor());
                if (!(debetor.getTotalDebt() == null) && !(debetor.getTotalDebt().equals(""))) {
                    controller.totalDebtField.setText(debetor.getTotalDebt());
                }
                if (!(debetor.getLastPayment() == null) && !(debetor.getLastPayment().equals(""))) {
                    controller.lastPaymentField.setText(debetor.getLastPayment());
                }
                if (!(debetor.getComments() == null) && !(debetor.getComments().equals(""))) {
                    controller.commentsField.setText(debetor.getComments());
                }

                controller.debetor = debetor;

                stage.show();
            } catch (IOException e) {

            }
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
            int idDeleteDebetor = debetor.getId();
            String sql = "delete from debetorstable where id= '" + idDeleteDebetor + "'";
            editDialogTableController.connectForEditDB(sql);

            tableDebetors.getItems().remove(debetor);
            setTotalDebtLabel();
        } else {
            alert.close();
        }
    }

    public void addNewDebetorInDBTable(Debetors debetor) {
        String sql = "INSERT INTO debetorstable (nameDebetor, totalDebt, lastPayment, comments, telephoneNumber, email)"
                + " VALUES ('" + debetor.getNameDebetor() + "', '" + debetor.getTotalDebt() + "', '" + debetor.getLastPayment() + "', '" + debetor.getComments() +
                "', '" + debetor.getTelephonNumber() + "', '" + debetor.getEmail() + "')";
        editDialogTableController.connectForEditDB(sql);
    }

    public void listOfCollumsDebetors() {
        try {
            listDebetors = FXCollections.observableArrayList();
            ConnectionToDB connect = new ConnectionToDB();
            connect.connectionToDBnote();
            Statement statement = connect.cnt.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from debetorstable");

            while (resultSet.next()) {
                Debetors debetor = new Debetors();
                debetor.setId(resultSet.getInt("id"));
                debetor.setNameDebetor(resultSet.getString("nameDebetor"));
                debetor.setTotalDebt(resultSet.getString("totalDebt"));
                debetor.setLastPayment(resultSet.getString("lastPayment"));
                debetor.setComments(resultSet.getString("comments"));
                debetor.setTelephonNumber(resultSet.getString("telephoneNumber"));
                debetor.setEmail(resultSet.getString("email"));
                listDebetors.add(debetor);
            }
            connect.cnt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
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
                temp = listDebetors.get(x).getTotalDebt();
                if (!(temp.equals(""))) {
                    //добавить проверку на содержание числа в поле
                    totalPrice = totalPrice + Integer.parseInt(temp);
                } else {
                    totalPrice = totalPrice + 0;
                }
            }
        }
        totalDebtLabel.setText(String.valueOf(totalPrice));
    }

    public void hideWindowDebetors() throws IOException {
        Stage stage = (Stage) backToSampleWindow.getScene().getWindow();
        stage.close();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/sample.fxml"));
        Parent root = loader.load();
        stage.setResizable(false);
        stage.setTitle("MultiToolSecurity v0.1");
        stage.setScene(new Scene(root, 1000, 700));
        stage.show();
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
                writer.write(debetor.getNameDebetor() + " - " + debetor.getTotalDebt() + " рублей." + System.lineSeparator());
            }

            writer.close();
        }
    }
}
