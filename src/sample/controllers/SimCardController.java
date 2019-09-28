package sample.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import sample.Main;
import sample.beans.SimCard;
import sample.service.SimCardService;
import sample.service.serviceImpl.SimCardServiceImpl;

import java.util.Optional;

public class SimCardController {

    private SampleController sampleController;
    ObservableList<SimCard> listSimCard;
    Main main = new Main();
    SimCard simCard;
    private SimCardService simCardService = new SimCardServiceImpl();

    public SimCardController(SampleController sampleController) {
        this.sampleController = sampleController;

        updateTableSimCard();
        insertSimCardTable();
        initSimCardTableListener();

        this.sampleController.addSimCard.setOnAction(event -> {
            add();
        });

        this.sampleController.editSimCard.setOnAction(event -> {
            edit();
        });

        this.sampleController.deleteSimCard.setOnAction(event -> {
            delete();
        });
    }

    private void initSimCardTableListener() {
        sampleController.tableSimCard.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getClickCount() == 2 && event.getButton() == MouseButton.PRIMARY) {
                    edit();
                }
            }
        });
    }

    private void edit() {
        EditDialogSimCardController controller;
        simCard = sampleController.tableSimCard.getSelectionModel().getSelectedItem();
        if (simCard == null) return;
        else {
            controller = main.showNewWindow("fxml/EditDialogSimCard.fxml", "Редактирование", 600, 434, Modality.APPLICATION_MODAL).getController();
            controller.nameClientForSimLabel.setText(simCard.getName_client());
            if (simCard.getKit() != null && !(simCard.getKit().equals(""))) {
                controller.kitField.setText(simCard.getKit());
            }
            if (simCard.getNumber_one() != null && !(simCard.getterNumberOne().equals(""))) {
                controller.simOneField.setText(simCard.getNumber_one());
            }
            if (simCard.getNumber_two() != null && !(simCard.getterNumberTwo().equals(""))) {
                controller.simTwoField.setText(simCard.getNumber_two());
            }
            if (simCard.getThe_note() != null && !(simCard.getterTheNote().equals(""))) {
                controller.commentsField.setText(simCard.getThe_note());
            }
        }

        controller.simCard = simCard;
        controller.simCardController = this;
    }

    private void add() {
        AddNewDebetorWindowController controller = main.showNewWindow("fxml/AddNewDebetorWindow.fxml", "Добавить должника", 450, 700, Modality.APPLICATION_MODAL).getController();
        controller.setSimCardController(this);
    }

    private void delete() {
        simCard = sampleController.tableSimCard.getSelectionModel().getSelectedItem();

        if (simCard == null) return;
        else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Внимание");
            alert.setHeaderText("Удалить выбранный комплект сим?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                simCardService.deleteSimCard(simCard);

                sampleController.tableSimCard.getItems().remove(simCard);
            } else {
                alert.close();
            }
        }
    }

    public void addNewSimCard(SimCard simCard) {
        simCardService.saveSimCard(simCard);
        updateTableSimCard();
    }

    private void updateTableSimCard() {
        listSimCard = FXCollections.observableArrayList(simCardService.getSimCard());
        insertSimCardTable();
    }

    private void insertSimCardTable() {
        sampleController.kitSimCard.setCellValueFactory(cellData -> cellData.getValue().getterKit());
        sampleController.numberOneCimCard.setCellValueFactory(cellData -> cellData.getValue().getterNumberOne());
        sampleController.numberTwoSimCard.setCellValueFactory(cellData -> cellData.getValue().getterNumberTwo());
        sampleController.theNoteSimCard.setCellValueFactory(cellData -> cellData.getValue().getterTheNote());
        sampleController.clientSimCard.setCellValueFactory(cellData -> cellData.getValue().getterName_client());
        sampleController.tableSimCard.setItems(listSimCard);
    }

}
