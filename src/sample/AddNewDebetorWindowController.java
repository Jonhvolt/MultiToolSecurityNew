package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import sample.beans.Client;
import sample.connection.ConnectToWEB;
import sample.beans.Debetors;

import java.io.IOException;

public class AddNewDebetorWindowController {

    @FXML
    private TableView<Client> tableSearchDebetors;

    @FXML
    private TableColumn<Client, String> nameObjectDebetorCollumn;

    @FXML
    private JFXButton chooseBtn;

    @FXML
    private JFXButton cancelBtn;

    @FXML
    private JFXTextField searchDebetorField;

    private ObservableList<Client> listObjectInDB;
    Debetors debetor;
    //DebetorsController debetorsController = new DebetorsController();
    private DebetorsController debetorsController;

    @FXML
    void initialize() {
        tableSearchDebetors.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        insertListOfCollumn();
        insertCollumnDebetors();
        tableSearchDebetors.setItems(listObjectInDB);
        searchDebetor();

        cancelBtn.setOnAction(event -> {
            Stage stage = (Stage) cancelBtn.getScene().getWindow();
            stage.close();
        });
    }

    public void createNewDebetorForDebetorsTable() throws IOException {
        debetor = new Debetors();
        Client client = tableSearchDebetors.getSelectionModel().getSelectedItem();
        debetor.setName_debetor(client.getName());
        debetor.setTotal_debt("");
        debetor.setLast_payment("");
        debetor.setComments("");
        if (client.getTelephone_number().equals(null) || client.getTelephone_number() == null) {
            debetor.setTelephone_number("");
        } else {
            debetor.setTelephone_number(client.getTelephone_number());
        }

        if (client.getEmail().equals(null) || client.getEmail() == null) {
            debetor.setEmail("");
        } else {
            debetor.setEmail(client.getEmail());
        }

        this.debetorsController.putNewDebetor(debetor);
        this.debetorsController.reloadTableDebetors();

        Stage stage = (Stage) chooseBtn.getScene().getWindow();
        stage.close();
    }

    public void setDebetorsController(DebetorsController debetorsController) {
        this.debetorsController = debetorsController;
    }


    public void insertListOfCollumn() {
        ConnectToWEB connectToWEB = new ConnectToWEB();
        listObjectInDB = FXCollections.observableArrayList(connectToWEB.getConnectToWEB("clients"));
    }

    public void insertCollumnDebetors() {
        nameObjectDebetorCollumn.setCellValueFactory(cellData -> cellData.getValue().getterName());
    }

    public void searchDebetor() {
        ObservableList data = tableSearchDebetors.getItems();
        searchDebetorField.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (oldValue != null && (newValue.length() < oldValue.length())) {
                tableSearchDebetors.setItems(data);
            }
            String value = newValue.toLowerCase();
            ObservableList<Client> subentries = FXCollections.observableArrayList();

            long count = tableSearchDebetors.getColumns().stream().count();
            for (int i = 0; i < tableSearchDebetors.getItems().size(); i++) {
                for (int j = 0; j < count; j++) {
                    String entry = "" + tableSearchDebetors.getColumns().get(j).getCellData(i);
                    if (entry.toLowerCase().contains(value)) {
                        subentries.add(tableSearchDebetors.getItems().get(i));
                        break;
                    }
                }
            }
            tableSearchDebetors.setItems(subentries);
        });
    }
}
