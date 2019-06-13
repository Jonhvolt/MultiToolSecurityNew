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

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.Statement;

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
        debetor.setNameDebetor(client.getName());
        debetor.setTotalDebt("");
        debetor.setLastPayment("");
        debetor.setComments("");
        if (client.getTelephoneNumber().equals(null) || client.getTelephoneNumber() == null) {
            debetor.setTelephonNumber("");
        } else {
            debetor.setTelephonNumber(client.getTelephoneNumber());
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
                client.setSimCards(resultSet.getString("simCards"));
                client.setNumberClients(resultSet.getString("numberClients"));
                client.setNotes(resultSet.getString("notes"));
                listObjectInDB.add(client);
            }
            connect.cnt.close();
        } catch (Exception e) {

        }
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
