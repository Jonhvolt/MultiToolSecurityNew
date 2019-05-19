package sample;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;

public class CalendarController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btn_29;

    @FXML
    private Button btn_30;

    @FXML
    private Button btn_31;

    @FXML
    private Button btn_1_1;

    @FXML
    private Button btn_2_2;

    @FXML
    private Button btn_3_3;

    @FXML
    private Button btn_4_4;

    @FXML
    private Button btn_5_5;

    @FXML
    private Button btn_6_6;

    @FXML
    private Button btn_7_7;

    @FXML
    private Button btn_8_8;

    @FXML
    private Button btn_9_9;

    @FXML
    private Button btn_10_10;

    @FXML
    private Button btn_11_11;

    @FXML
    private TextField dateTextField;

    @FXML
    private TitledPane userTheNote;

    @FXML
    private Button userTheNoteSaveBtn;

    @FXML
    private TextArea userInputTextArea;

    @FXML
    private Button btnLeft;

    @FXML
    private Button btnRigth;

    Calendar todayDate = new GregorianCalendar();
    SimpleDateFormat formatForDateNow = new SimpleDateFormat("dd.MM.yyyy");

    @FXML
    void initialize() throws Exception{
        currectCalendar();
        createTheNoteAction();
    }

    public void createTheNoteAction() {
        //по нажатию кнопки "Сохранить", берём текст заметки и дату, формируем statement и передаём в БД в нужные поля
        userTheNoteSaveBtn.setOnAction(actionEvent -> {
            String userTheNote = userInputTextArea.getText();
            String userTheNoteDate = dateTextField.getText();
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

    public void currectCalendar() {
        String dateString = formatForDateNow.format(todayDate.getTime());
        dateTextField.setText(dateString);
        SimpleDateFormat monthForDateNow = new SimpleDateFormat("MM");
        String todayMonth = monthForDateNow.format(todayDate.getTime());
        //проверяем какой месяц текущий, в зависимости от этого будут активными кнопки равным количеству дней
        switch (todayMonth) {
            case "01": this.forDay31();
                break;
            case "02": //проверка на високосный год
                SimpleDateFormat yearForDateNow = new SimpleDateFormat("yyyy");
                String fsfs = yearForDateNow.format(todayDate.getTime());
                int x = Integer.parseInt(fsfs);
                if(((GregorianCalendar) todayDate).isLeapYear(x) == true) {
                    this.forDay29();
                } else {
                    this.forDay28();
                }
                break;
            case "03": this.forDay31();
                break;
            case "04": this.forDay30();
                break;
            case "05": this.forDay31();
                break;
            case "06": this.forDay30();
                break;
            case "07": this.forDay31();
                break;
            case "08": this.forDay31();
                break;
            case "09": this.forDay30();
                break;
            case "10": this.forDay31();
                break;
            case "11": this.forDay30();
                break;
            case "12": this.forDay31();
                break;
        }
    }
    //далее идут методы, которые оставляют активными количество кнопок равное количеству дней в текущем месяце
    public void forDay28 () {
        //цвет неактивных кнопок #46423d
        btn_29.setDisable(true);
        btn_30.setDisable(true);
        btn_31.setDisable(true);
        btn_1_1.setDisable(true);
        btn_2_2.setDisable(true);
        btn_3_3.setDisable(true);
        btn_4_4.setDisable(true);
        btn_5_5.setDisable(true);
        btn_6_6.setDisable(true);
        btn_7_7.setDisable(true);
        btn_8_8.setDisable(true);
        btn_9_9.setDisable(true);
        btn_10_10.setDisable(true);
        btn_11_11.setDisable(true);
    }
    public void forDay29 () {
        //цвет неактивных кнопок #46423d
        btn_30.setDisable(true);
        btn_31.setDisable(true);
        btn_1_1.setDisable(true);
        btn_2_2.setDisable(true);
        btn_3_3.setDisable(true);
        btn_4_4.setDisable(true);
        btn_5_5.setDisable(true);
        btn_6_6.setDisable(true);
        btn_7_7.setDisable(true);
        btn_8_8.setDisable(true);
        btn_9_9.setDisable(true);
        btn_10_10.setDisable(true);
        btn_11_11.setDisable(true);
    }
    public void forDay30 () {
        //цвет неактивных кнопок #46423d
        btn_31.setDisable(true);
        btn_1_1.setDisable(true);
        btn_2_2.setDisable(true);
        btn_3_3.setDisable(true);
        btn_4_4.setDisable(true);
        btn_5_5.setDisable(true);
        btn_6_6.setDisable(true);
        btn_7_7.setDisable(true);
        btn_8_8.setDisable(true);
        btn_9_9.setDisable(true);
        btn_10_10.setDisable(true);
        btn_11_11.setDisable(true);
    }
    public void forDay31 () {
        //цвет неактивных кнопок #46423d
        btn_1_1.setDisable(true);
        btn_2_2.setDisable(true);
        btn_3_3.setDisable(true);
        btn_4_4.setDisable(true);
        btn_5_5.setDisable(true);
        btn_6_6.setDisable(true);
        btn_7_7.setDisable(true);
        btn_8_8.setDisable(true);
        btn_9_9.setDisable(true);
        btn_10_10.setDisable(true);
        btn_11_11.setDisable(true);
    }

    public void rigthLeftBtnAction () {
        //по кнопкам вправо и влево меняем месяцы, год и делаем активными или неактивными кнопки календаря

        btnLeft.setOnAction(actionEvent -> {

        });

        btnRigth.setOnAction(actionEvent -> {

        });
    }
}
