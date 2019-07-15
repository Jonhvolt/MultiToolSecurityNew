package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.beans.Note;
import sample.connection.ConnectToWEB;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class SampleController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXButton btnClientBase;

    @FXML
    JFXButton btnIncome;

    @FXML
    private JFXButton btnInWork;

    @FXML
    private JFXButton btnTheDebet;

    @FXML
    private JFXButton btnCalc;

    @FXML
    private JFXButton btnCalendar;

    @FXML
    private TitledPane userTheNoteOfSample;

    @FXML
    private Button userTheNoteSaveBtn;

    @FXML
    private TextArea userInputTextArea;

    @FXML
    private Label simpleTodayTheNote;

    @FXML
    private JFXDatePicker datePicker;

    @FXML
    private Label labelTodayTheNote;

    Stage stage;
    Main main = new Main();

    @FXML
    void initialize() {
        todayTheNoteToSample();

        btnClientBase.setOnAction(event -> {
            stage = (Stage) btnClientBase.getScene().getWindow();
            stage.close();
            main.showNewWindow("fxml/BaseClients.fxml", "База клиентов", 1000, 700, Modality.APPLICATION_MODAL);
        });

        btnTheDebet.setOnAction(event -> {
            stage = (Stage)btnTheDebet.getScene().getWindow();
            stage.close();
            main.showNewWindow("fxml/Debetors.fxml", "Дебеторская задолженность", 1000, 700, Modality.APPLICATION_MODAL);
        });

        btnIncome.setOnAction(event -> {
            main.showNewWindow("fxml/Income.fxml", "Формирование отчета", 1000, 700, Modality.NONE);
        });

        btnCalendar.setOnAction(event -> {
            main.showNewWindow("fxml/Calendar.fxml", "Календарь", 574, 300, Modality.NONE);
        });

        btnCalc.setOnAction(event -> {
            main.showNewWindow("fxml/Calculator.fxml", "Калькулятор", 300, 400, Modality.NONE);
        });
    }

    //Работаем с заметками на стартовом экране
    public void createTheNoteStartWindow() {
        //по нажатию кнопки "Сохранить", берём текст заметки и дату, и передаём в БД
        userTheNoteSaveBtn.setOnAction(actionEvent -> {
            String userTheNote = userInputTextArea.getText();

            if (userTheNote != null && ! (userTheNote.isEmpty())) {
                if (datePicker.getValue() == null) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText("Вы не выбрали дату");
                    alert.showAndWait();
                    return;
                }
                LocalDate date = datePicker.getValue();
                String stringDATE = date.toString();
                if (!(userTheNote.equals("")) && !(userTheNote == null) && date != null) {
                    Note note = new Note(userTheNote, stringDATE);
                    ConnectToWEB connectToWEB = new ConnectToWEB();
                    connectToWEB.saveNoteToWEB(note);
                }
                userTheNoteOfSample.setExpanded(false);
            } else return;
        });
    }

    //показывает заметку на главном экране, если на этот день есть заметка
    public void todayTheNoteToSample() {
        String strDATE_NOW = LocalDate.now().toString();
        ConnectToWEB connectToWEB = new ConnectToWEB();
        connectToWEB.getNotesToWEB();
        List<Note> listNotes = new ArrayList<>(connectToWEB.getNotesToWEB());

        if (listNotes.size() > 0) {
            int count = 0;
            for (Note note : listNotes) {
                if (note.getDate().equals(strDATE_NOW)) {
                    count++;
                    simpleTodayTheNote.setText(simpleTodayTheNote.getText() + count + ") " + note.getThe_note() + System.lineSeparator());
                }
            }
        } else {
            labelTodayTheNote.setText("На сегодня заметок нет");
        }
    }
}
