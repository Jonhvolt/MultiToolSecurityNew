package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.ResourceBundle;

public class IncomeController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label totalSumcCientsField;

    @FXML
    private JFXTextField accruedСommonField;

    @FXML
    private JFXTextField nonСashAccruedField;

    @FXML
    private JFXTextField cashAccruedField;

    @FXML
    private JFXTextField arrivedField;

    @FXML
    private JFXTextField arrivedNonCashField;

    @FXML
    private JFXTextField arrivedCashField;

    @FXML
    private JFXTextField clientsAcceptedField;

    @FXML
    private JFXTextField clientsGoneField;

    @FXML
    private JFXTextField clientsAcceptedSumField;

    @FXML
    private JFXTextField clientsGoneSumField;

    @FXML
    private JFXTextField oneFIOField;

    @FXML
    private JFXTextField twoFIOField;

    @FXML
    private JFXTextField threeFIOField;

    @FXML
    private JFXTextField fourFIOField;

    @FXML
    private JFXTextField oneSalaryField;

    @FXML
    private JFXTextField twoSalaryField;

    @FXML
    private JFXTextField threeSalaryField;

    @FXML
    private JFXTextField fourSalaryField;

    @FXML
    private JFXTextField fiveSalaryField;

    @FXML
    private JFXTextField fiveFIOField;

    @FXML
    private JFXTextField sixSalaryField;

    @FXML
    private JFXTextField sixFIOField;

    @FXML
    private JFXTextField sevenSalaryField;

    @FXML
    private JFXTextField sevenFIOField;

    @FXML
    private Label allSalaryTheSum;

    @FXML
    private JFXButton reportBtn;

    @FXML
    private JFXDatePicker dateStart;

    @FXML
    private JFXDatePicker dateStop;

    @FXML
    private JFXTextArea theNoteOfReportArea;

    @FXML
    private JFXButton assemblyWorkBtn;

    @FXML
    void initialize() {
        assemblyWorkBtn.setOnAction(event -> {
            //открываем окно с данными о монтажных работах
        });

        reportBtn.setOnAction(event -> {
            createFileChooser();
        });
    }

    //метод сохраняет данные в файл и берёт выбранные даты из Pickera
    private void saveReportToFile(File file) {
        try {
            LocalDate startDate = dateStart.getValue();
            LocalDate stopDate = dateStop.getValue();
            //обработка ошибки о не выбранной дате
            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            String currectDateStart = startDate.format(dateFormat);
            String currectDateStop = stopDate.format(dateFormat);
            String startAndStopDate = "Начало периода отчёта: " + currectDateStart + " окончание периода отчёта: " + currectDateStop + System.lineSeparator();
            //запись данных в файл

            FileWriter writer;
            writer = new FileWriter(file);
            writer.write(startAndStopDate + System.lineSeparator() + accruedCommon() + System.lineSeparator() + clientsResultOfTheMonth() + System.lineSeparator() + System.lineSeparator() + theSalaryOfMonth() + System.lineSeparator() + System.lineSeparator() + System.lineSeparator() + System.lineSeparator() + theNoteOfReport());
            writer.close();
        } catch (IOException e) {

        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Внимание");
            alert.setHeaderText("Выберите даты периода отчёта");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                alert.close();
            } else {
                alert.close();
            }
        }
    }

    private void createFileChooser() {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);
        fileChooser.setTitle("Сохранить отчёт");
        Stage stage = (Stage) reportBtn.getScene().getWindow();
        File file = fileChooser.showSaveDialog(stage);
        if (file != null) {
            saveReportToFile(file);
        }
    }

    //метод работает с полями зарплаты и формирует строки для записи в файл
    private String theSalaryOfMonth() {
        if (oneSalaryField.getText().isEmpty()) oneSalaryField.setText("0");
        if (twoSalaryField.getText().isEmpty()) twoSalaryField.setText("0");
        if (threeSalaryField.getText().isEmpty()) threeSalaryField.setText("0");
        if (fourSalaryField.getText().isEmpty()) fourSalaryField.setText("0");
        if (fiveSalaryField.getText().isEmpty()) fiveSalaryField.setText("0");
        if (sixSalaryField.getText().isEmpty()) sixSalaryField.setText("0");
        if (sevenSalaryField.getText().isEmpty()) sevenSalaryField.setText("0");

        double allSalary = Double.parseDouble(oneSalaryField.getText()) + Double.parseDouble(twoSalaryField.getText()) + Double.parseDouble(threeSalaryField.getText()) +
                Double.parseDouble(fourSalaryField.getText()) + Double.parseDouble(fiveSalaryField.getText()) + Double.parseDouble(sixSalaryField.getText()) + Double.parseDouble(sevenSalaryField.getText());
        allSalaryTheSum.setText(String.valueOf(allSalary));

        String oneFIOString = oneFIOField.getText() + " - " + oneSalaryField.getText() + System.lineSeparator();
        String twoFIOString = twoFIOField.getText() + " - " + twoSalaryField.getText() + System.lineSeparator();
        String threeFIOString = threeFIOField.getText() + " - " + threeSalaryField.getText() + System.lineSeparator();
        String fourFIOString = fourFIOField.getText() + " - " + fourSalaryField.getText() + System.lineSeparator();
        String fiveFIOString = fiveFIOField.getText() + " - " + fiveSalaryField.getText() + System.lineSeparator();
        String sixFIOString = sixFIOField.getText() + " - " + sixSalaryField.getText() + System.lineSeparator();
        String sevenFIOString = sevenFIOField.getText() + " - " + sevenSalaryField.getText() + System.lineSeparator();
        String allSalaty = "Общая сумма зарплат: " + allSalaryTheSum.getText() + System.lineSeparator();

        String s = "Зарплатный фонд: " + "\n" + oneFIOString + twoFIOString + threeFIOString + fourFIOString + fiveFIOString + sixFIOString + sevenFIOString + allSalaty;
        return s;
    }

    //Берём заметку пользователя в строковую переменную
    private String theNoteOfReport() {
        String theNote = "Заметка к отчёту :" + System.lineSeparator() + theNoteOfReportArea.getText() + System.lineSeparator();

        return theNote;
    }

    //работает с полями принятых и снятых объектов за выбранный период
    private String clientsResultOfTheMonth() {
        int clientsAccept;
        int clientsGone;

        if (!(clientsAcceptedField.getText().isEmpty()))
            clientsAccept = Integer.parseInt(clientsAcceptedField.getText());
        else clientsAccept = 0;
        if (!(clientsGoneField.getText().isEmpty())) clientsGone = Integer.parseInt(clientsGoneField.getText());
        else clientsGone = 0;

        double clientsAcceptSum;
        double clientsGoneSum;

        if (!(clientsAcceptedSumField.getText().isEmpty()))
            clientsAcceptSum = Double.parseDouble(clientsAcceptedSumField.getText());
        else clientsAcceptSum = 0;
        if (!(clientsGoneSumField.getText().isEmpty()))
            clientsGoneSum = Double.parseDouble(clientsGoneSumField.getText());
        else clientsGoneSum = 0;

        double totalSumClients = clientsAcceptSum - clientsGoneSum;
        totalSumcCientsField.setText(String.valueOf(totalSumClients)); //вывели сумму за новые объекты в поле Итог
        //готовим строки для добавления в файл
        String clientsAcceptString = "Принято объектов: " + clientsAccept + " на сумму: " + clientsAcceptSum + System.lineSeparator();
        String clientsGoneString = "Снято объектов: " + clientsGone + " на сумму: " + clientsGoneSum + System.lineSeparator();
        String totalSumString = "Итог: " + totalSumClients + System.lineSeparator();

        String s = "Итог по объектам на конец месяца: " + System.lineSeparator() + clientsAcceptString + clientsGoneString + totalSumString;
        return s;
    }

    //работает с начислениями и поступлениями. Метод берёт данные из полей, формирует готовые строки для добавления в текстовый файл
    private String accruedCommon() {
        double accrued;
        double nonCashAccrued;
        double cashAccrued;

        if (!(accruedСommonField.getText().isEmpty())) accrued = Double.parseDouble(accruedСommonField.getText());
        else accrued = 0;
        if (!(arrivedNonCashField.getText().isEmpty()))
            nonCashAccrued = Double.parseDouble(nonСashAccruedField.getText());
        else nonCashAccrued = 0;
        if (!(cashAccruedField.getText().isEmpty())) cashAccrued = Double.parseDouble(cashAccruedField.getText());
        else cashAccrued = 0;

        String accruedString = "Общая сумма начислений: " + accrued + System.lineSeparator();
        String nonCashAccruedString = "Безналичный расчёт: " + nonCashAccrued + System.lineSeparator();
        String cashAccruedString = "Наличный расчёт: " + cashAccrued + System.lineSeparator();

        double arrived;
        double arrivedNonCash;
        double arrivedCash;

        if (!(arrivedField.getText().isEmpty())) arrived = Double.parseDouble(arrivedField.getText());
        else arrived = 0;
        if (!(arrivedNonCashField.getText().isEmpty()))
            arrivedNonCash = Double.parseDouble(arrivedNonCashField.getText());
        else arrivedNonCash = 0;
        if (!(arrivedCashField.getText().isEmpty())) arrivedCash = Double.parseDouble(arrivedCashField.getText());
        else arrivedCash = 0;

        String arrivedString = "Общая сумма поступлений: " + arrived + System.lineSeparator();
        String arrivedNonCashString = "Поступления на расчётный счёт: " + arrivedNonCash + System.lineSeparator();
        String arrivedCashString = "Поступления наличными: " + arrivedCash + System.lineSeparator();

        String s = "Начисления: " + System.lineSeparator() + accruedString + nonCashAccruedString + cashAccruedString + System.lineSeparator() + "Поступления: " + System.lineSeparator() + arrivedString +
                arrivedNonCashString + arrivedCashString;
        return s;
    }


}
