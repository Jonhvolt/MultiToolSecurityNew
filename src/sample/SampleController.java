package sample;

import com.jfoenix.controls.JFXButton;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import com.jfoenix.controls.JFXDatePicker;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TitledPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.ConnectionToDB;

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
    void initialize() {
        todayTheNoteToSample();
    }

    //Работаем с заметками на стартовом экране
    public void createTheNoteStartWindow() {
        //по нажатию кнопки "Сохранить", берём текст заметки и дату, формируем statement и передаём в БД в нужные поля
        userTheNoteSaveBtn.setOnAction(actionEvent -> {
            String userTheNote = userInputTextArea.getText();
            LocalDate date = datePicker.getValue();
            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            String userTheNoteDate = date.format(dateFormat);
            ConnectionToDB connect = new ConnectionToDB();
            try {
                connect.connectionToDBnote();
            } catch (Exception e) {
                e.printStackTrace();
            }
            connect.createStatementExecuteUserTheNote(userTheNote, userTheNoteDate);
            userInputTextArea.setText("");
            //здесь нужно найти способ сворачивать окно заметок после нажатия кнопки сохранить
        });
    }

    //метод работает с заметками и запросом к БД
    public void todayTheNoteToSample() {
        String note;
        //в этой переменной String будет совпадение текущей даты и даты из БД, если её там смогла найти программа
        Calendar todayDate = new GregorianCalendar();
        SimpleDateFormat formatForDateNow = new SimpleDateFormat("dd.MM.yyyy");
        //получаем текущую дату в формате String
        String dateStringTodayForSimple = formatForDateNow.format(todayDate.getTime());
        //далее подключаемся к БД и формируем Statement в классе ConnectionToDB, чтобы вытащить данные из колонки Date
        ConnectionToDB connect = new ConnectionToDB();
        try {
            connect.connectionToDBnote();
            connect.createStatementSelectTheNoteToday();
            ArrayList<String> listDate = connect.dateResultList;
            for (int x = 0; x < listDate.size(); x++) {
                //если есть совпадения, то выводим на главном экране заметку из БД
                if(listDate.get(x).equals(dateStringTodayForSimple)) {
                    ArrayList<String> listNote = connect.noteResultList;
                    note = listNote.get(x);
                    String a = simpleTodayTheNote.getText();
                    simpleTodayTheNote.setText(a + "\n" + note);
                }
                else if (x == listDate.size()){
                    //Выводим, если нет совпадений, т.е. не найдены заметки на сегодня
                    String sNone = "На сегодня заметок нет.";
                    simpleTodayTheNote.setText(sNone);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //показываем окно с базой клиентов
    public void showClientBase() throws IOException {
        Stage stage;
        stage = (Stage) btnClientBase.getScene().getWindow();
        stage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/BaseClients.fxml"));
        Parent root1 = fxmlLoader.load();
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("База клиентов");
        stage.setScene(new Scene(root1, 1000, 700));
        stage.show();
    }

    //показываем окно с отчётом о доходах
    public void showReportIncome() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/Income.fxml"));
        Stage stage = new Stage();
        try {
            Parent parent = loader.load();
            stage.setTitle("MultiToolSecurity v0.1");
            stage.setResizable(false);
            stage.setScene(new Scene(parent, 1000, 700));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //показываем окно с календарём
    public void showCalendar() throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("fxml/Calendar.fxml"));
        stage.setTitle("Календарь");
        stage.setResizable(false);
        stage.setScene(new Scene(root, 574, 300));
        stage.show();
    }

    //показываем окно с калькулятором
    public void showCalculator() throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("fxml/Calculator.fxml"));
        stage.setTitle("Калькулятор");
        stage.setResizable(false);
        stage.setScene(new Scene(root, 300, 400));
        stage.show();
    }
}
