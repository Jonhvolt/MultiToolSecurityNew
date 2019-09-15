package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import sample.beans.Client;
import sample.beans.Debetors;
import sample.beans.Note;
import sample.connection.ConnectToWEB;
import sample.controllers.ClientsTableController;
import sample.controllers.DebetorsTableController;
import sample.controllers.IncomeReportController;

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
    private Pane theNotePane;

    @FXML
    private TitledPane userTheNoteOfSample;

    @FXML
    private Button userTheNoteSaveBtn;

    @FXML
    private TextArea userInputTextArea;

    @FXML
    private Label labelTodayTheNote;

    @FXML
    private Label simpleTodayTheNote;

    @FXML
    private Pane buttonsPanel;

    @FXML
    private JFXButton btnClientBase;

    @FXML
    private JFXButton btnIncome;

    @FXML
    private JFXButton btnTheDebet;

    @FXML
    private JFXButton btnInWork;

    @FXML
    private JFXButton btnCalc;

    @FXML
    private JFXButton btnCalendar;

    /**
     * Описание панели с таблицей клиентов
     */

    @FXML
    private Pane baseClientsPane;

    @FXML
    public TableView<Client> tableObject;

    @FXML
    public TableColumn<Client, String> collumnStatus;

    @FXML
    public TableColumn<Client, String> collumNameClient;

    @FXML
    public TableColumn<Client, String> collumAddressClient;

    @FXML
    public TableColumn<Client, String> collumTelephoneNumber;

    @FXML
    public TableColumn<Client, String> collumContactUser;

    @FXML
    public TableColumn<Client, String> collumAreaSecurity;

    @FXML
    public TableColumn<Client, String> collumPriceToMonth;

    @FXML
    public TableColumn<Client, String> collumEmailClient;

    @FXML
    public TableColumn<Client, String> collumSimCards;

    @FXML
    public TableColumn<Client, String> collumNumberClients;

    @FXML
    public TableColumn<Client, String> collumTheNotes;

    @FXML
    public JFXButton addBtn;

    @FXML
    public JFXButton editBtn;

    @FXML
    public JFXButton delBtn;

    @FXML
    public Label labelCount;

    @FXML
    public JFXTextField searchJFXTextField;

    @FXML
    private Pane panelForTotalLabel;

    @FXML
    public Label totalLabel;

    @FXML
    public Pane incomePanel;

    @FXML
    public Pane debetorsTablePanel;

    /**
     * Описание панели с формированием отчета о доходах
     */

    @FXML
    public JFXTextField accruedСommonField;

    @FXML
    public JFXTextField nonСashAccruedField;

    @FXML
    public JFXTextField cashAccruedField;

    @FXML
    public JFXTextField arrivedField;

    @FXML
    public JFXTextField arrivedNonCashField;

    @FXML
    public JFXTextField arrivedCashField;

    @FXML
    public JFXTextField clientsAcceptedField;

    @FXML
    public JFXTextField clientsGoneField;

    @FXML
    public JFXTextField clientsAcceptedSumField;

    @FXML
    public JFXTextField clientsGoneSumField;

    @FXML
    public Label totalSumcCientsField;

    @FXML
    public JFXTextField oneFIOField;

    @FXML
    public JFXTextField twoFIOField;

    @FXML
    public JFXTextField threeFIOField;

    @FXML
    public JFXTextField fourFIOField;

    @FXML
    public JFXTextField oneSalaryField;

    @FXML
    public JFXTextField twoSalaryField;

    @FXML
    public JFXTextField threeSalaryField;

    @FXML
    public JFXTextField fourSalaryField;

    @FXML
    public JFXTextField fiveSalaryField;

    @FXML
    public JFXTextField fiveFIOField;

    @FXML
    public JFXTextField sixSalaryField;

    @FXML
    public JFXTextField sixFIOField;

    @FXML
    public JFXTextField sevenSalaryField;

    @FXML
    public JFXTextField sevenFIOField;

    @FXML
    public Label allSalaryTheSum;

    @FXML
    public JFXButton reportBtn;

    @FXML
    public JFXDatePicker dateStop;

    @FXML
    public JFXTextArea theNoteOfReportArea;

    @FXML
    public JFXButton assemblyWorkBtn;

    @FXML
    public JFXDatePicker dateStart;

    @FXML
    private JFXButton btnCloseTableClientsPanel;

    @FXML
    private JFXButton btnCloseIncomePanel;

    /**
     * Описание панели с таблицей дебеторской задолженности
     */
    @FXML
    public TableView<Debetors> tableDebetors;

    @FXML
    public TableColumn<Debetors, String> nameDebetorCollumn;

    @FXML
    public TableColumn<Debetors, String> totalDebtCollumn;

    @FXML
    public TableColumn<Debetors, String> lastPaymentCollumn;

    @FXML
    public TableColumn<Debetors, String> commentsCollumn;

    @FXML
    public TableColumn<Debetors, String> telephoneDebetorCollumn;

    @FXML
    public TableColumn<Debetors, String> emailDebetorCollumn;

    @FXML
    public Label totalDebtLabel;

    @FXML
    public JFXButton addDebetorBtn;

    @FXML
    public JFXButton editDebetorBtn;

    @FXML
    public JFXButton btnCloseTableDebetorsPanel;

    @FXML
    public JFXButton deleteDebetorBtn;

    @FXML
    public JFXButton unloadingBtn;



    @FXML
    void createTheNoteStartWindow(ActionEvent event) {

    }

    @FXML
    void editBtnThisTableObject(ActionEvent event) {

    }

    private JFXDatePicker datePicker;

    Main main = new Main();

    @FXML
    void initialize() {

        todayTheNoteToSample();

        btnClientBase.setOnAction(event -> {
            hideAllPanels();
            new ClientsTableController(this);
            baseClientsPane.setVisible(true);
        });

        btnIncome.setOnAction(event -> {
           hideAllPanels();
           new IncomeReportController(this);
           incomePanel.setVisible(true);
        });

        btnTheDebet.setOnAction(event -> {
            hideAllPanels();
            new DebetorsTableController(this);
            debetorsTablePanel.setVisible(true);
        });

        btnCalendar.setOnAction(event -> {
            main.showNewWindow("fxml/Calendar.fxml", "Календарь", 574, 300, Modality.NONE);
        });

        btnCalc.setOnAction(event -> {
            main.showNewWindow("fxml/Calculator.fxml", "Калькулятор", 300, 400, Modality.NONE);
        });

        btnCloseTableClientsPanel.setOnAction(event -> {
            hideAllPanels();
            theNotePane.setVisible(true);
        });

        btnCloseIncomePanel.setOnAction(event -> {
            hideAllPanels();
            theNotePane.setVisible(true);
        });

        btnCloseTableDebetorsPanel.setOnAction(event -> {
            hideAllPanels();
            theNotePane.setVisible(true);
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

    private void hideAllPanels() {
        incomePanel.setVisible(false);
        baseClientsPane.setVisible(false);
        theNotePane.setVisible(false);
        debetorsTablePanel.setVisible(false);
    }
}
