package sample.controllers;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import sample.SampleController;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class IncomeReportController {
    private SampleController sampleController;

    public IncomeReportController(SampleController sampleController) {
        this.sampleController = sampleController;

        sampleController.reportBtn.setOnAction(event -> {
            createFileChooser();
        });
    }

    //метод сохраняет данные в файл и берёт выбранные даты из Pickera
    private void saveReportToFile(File file) {
        try {
            LocalDate startDate = sampleController.dateStart.getValue();
            LocalDate stopDate = sampleController.dateStop.getValue();
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
        Stage stage = (Stage) sampleController.reportBtn.getScene().getWindow();
        File file = fileChooser.showSaveDialog(stage);
        if (file != null) {
            saveReportToFile(file);
        }
    }

    //метод работает с полями зарплаты и формирует строки для записи в файл
    private String theSalaryOfMonth() {
        if (sampleController.oneSalaryField.getText().isEmpty()) sampleController.oneSalaryField.setText("0");
        if (sampleController.twoSalaryField.getText().isEmpty()) sampleController.twoSalaryField.setText("0");
        if (sampleController.threeSalaryField.getText().isEmpty()) sampleController.threeSalaryField.setText("0");
        if (sampleController.fourSalaryField.getText().isEmpty()) sampleController.fourSalaryField.setText("0");
        if (sampleController.fiveSalaryField.getText().isEmpty()) sampleController.fiveSalaryField.setText("0");
        if (sampleController.sixSalaryField.getText().isEmpty()) sampleController.sixSalaryField.setText("0");
        if (sampleController.sevenSalaryField.getText().isEmpty()) sampleController.sevenSalaryField.setText("0");

        double allSalary = Double.parseDouble(sampleController.oneSalaryField.getText()) + Double.parseDouble(sampleController.twoSalaryField.getText()) + Double.parseDouble(sampleController.threeSalaryField.getText()) +
                Double.parseDouble(sampleController.fourSalaryField.getText()) + Double.parseDouble(sampleController.fiveSalaryField.getText()) + Double.parseDouble(sampleController.sixSalaryField.getText()) + Double.parseDouble(sampleController.sevenSalaryField.getText());
        sampleController.allSalaryTheSum.setText(String.valueOf(allSalary));

        String oneFIOString = sampleController.oneFIOField.getText() + " - " + sampleController.oneSalaryField.getText() + System.lineSeparator();
        String twoFIOString = sampleController.twoFIOField.getText() + " - " + sampleController.twoSalaryField.getText() + System.lineSeparator();
        String threeFIOString = sampleController.threeFIOField.getText() + " - " + sampleController.threeSalaryField.getText() + System.lineSeparator();
        String fourFIOString = sampleController.fourFIOField.getText() + " - " + sampleController.fourSalaryField.getText() + System.lineSeparator();
        String fiveFIOString = sampleController.fiveFIOField.getText() + " - " + sampleController.fiveSalaryField.getText() + System.lineSeparator();
        String sixFIOString = sampleController.sixFIOField.getText() + " - " + sampleController.sixSalaryField.getText() + System.lineSeparator();
        String sevenFIOString = sampleController.sevenFIOField.getText() + " - " + sampleController.sevenSalaryField.getText() + System.lineSeparator();
        String allSalaty = "Общая сумма зарплат: " + sampleController.allSalaryTheSum.getText() + System.lineSeparator();

        String s = "Зарплатный фонд: " + "\n" + oneFIOString + twoFIOString + threeFIOString + fourFIOString + fiveFIOString + sixFIOString + sevenFIOString + allSalaty;
        return s;
    }

    //Берём заметку пользователя в строковую переменную
    private String theNoteOfReport() {
        String theNote = "Заметка к отчёту :" + System.lineSeparator() + sampleController.theNoteOfReportArea.getText() + System.lineSeparator();

        return theNote;
    }

    //работает с полями принятых и снятых объектов за выбранный период
    private String clientsResultOfTheMonth() {
        int clientsAccept;
        int clientsGone;

        if (!(sampleController.clientsAcceptedField.getText().isEmpty()))
            clientsAccept = Integer.parseInt(sampleController.clientsAcceptedField.getText());
        else clientsAccept = 0;
        if (!(sampleController.clientsGoneField.getText().isEmpty())) clientsGone = Integer.parseInt(sampleController.clientsGoneField.getText());
        else clientsGone = 0;

        double clientsAcceptSum;
        double clientsGoneSum;

        if (!(sampleController.clientsAcceptedSumField.getText().isEmpty()))
            clientsAcceptSum = Double.parseDouble(sampleController.clientsAcceptedSumField.getText());
        else clientsAcceptSum = 0;
        if (!(sampleController.clientsGoneSumField.getText().isEmpty()))
            clientsGoneSum = Double.parseDouble(sampleController.clientsGoneSumField.getText());
        else clientsGoneSum = 0;

        double totalSumClients = clientsAcceptSum - clientsGoneSum;
        sampleController.totalSumcCientsField.setText(String.valueOf(totalSumClients));

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

        if (!(sampleController.accruedСommonField.getText().isEmpty())) accrued = Double.parseDouble(sampleController.accruedСommonField.getText());
        else accrued = 0;
        if (!(sampleController.arrivedNonCashField.getText().isEmpty()))
            nonCashAccrued = Double.parseDouble(sampleController.nonСashAccruedField.getText());
        else nonCashAccrued = 0;
        if (!(sampleController.cashAccruedField.getText().isEmpty())) cashAccrued = Double.parseDouble(sampleController.cashAccruedField.getText());
        else cashAccrued = 0;

        String accruedString = "Общая сумма начислений: " + accrued + System.lineSeparator();
        String nonCashAccruedString = "Безналичный расчёт: " + nonCashAccrued + System.lineSeparator();
        String cashAccruedString = "Наличный расчёт: " + cashAccrued + System.lineSeparator();

        double arrived;
        double arrivedNonCash;
        double arrivedCash;

        if (!(sampleController.arrivedField.getText().isEmpty())) arrived = Double.parseDouble(sampleController.arrivedField.getText());
        else arrived = 0;
        if (!(sampleController.arrivedNonCashField.getText().isEmpty()))
            arrivedNonCash = Double.parseDouble(sampleController.arrivedNonCashField.getText());
        else arrivedNonCash = 0;
        if (!(sampleController.arrivedCashField.getText().isEmpty())) arrivedCash = Double.parseDouble(sampleController.arrivedCashField.getText());
        else arrivedCash = 0;

        String arrivedString = "Общая сумма поступлений: " + arrived + System.lineSeparator();
        String arrivedNonCashString = "Поступления на расчётный счёт: " + arrivedNonCash + System.lineSeparator();
        String arrivedCashString = "Поступления наличными: " + arrivedCash + System.lineSeparator();

        String s = "Начисления: " + System.lineSeparator() + accruedString + nonCashAccruedString + cashAccruedString + System.lineSeparator() + "Поступления: " + System.lineSeparator() + arrivedString +
                arrivedNonCashString + arrivedCashString;
        return s;
    }
}
