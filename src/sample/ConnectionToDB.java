package sample;

import java.sql.*;
import java.util.ArrayList;

public class ConnectionToDB {
    Connection cnt;
    ArrayList<String> dateResultList = new ArrayList<>();
    ArrayList<String> noteResultList = new ArrayList<>();

    public Connection connectionToDBnote() throws  Exception {
        //подключение к БД с таблицей с заметками. В таблице столбцы "date" и "note" в формате String
        String user = "root";
        String pass = "root";
        //?useSSL=false эта часть добавлена для избавления от ошибки при попытки закртыть connection
        String url = "jdbc:mysql://localhost:3306/multitooldb?useSSL=false";
        Class.forName("com.mysql.jdbc.Driver");
        try {
            cnt = DriverManager.getConnection(url, user, pass);
            System.out.println("Произошло соедение к базе данных");
        }
        catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Ошибка соединения с базой данных");
        }
        return cnt;
    }


    public void createStatementExecuteUserTheNote(String userTheNote, String userTheNoteDate) {
        //метод добавляет в БД в таблицу заметок дату и текст заметки полученные от пользователя
        String insertthenote = userTheNote;
        String insertthedate = userTheNoteDate;
        try {
            Statement statement = cnt.createStatement();
            statement.execute("insert into thenotetable (thenote, date) value ('"+insertthenote+"', '"+insertthedate+"')");
            cnt.close();
            System.out.println("Соединение с БД закрыто");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createStatementSelectTheNoteToday () {
        //метод достаёт данные из столбца date и записывает их все в коллекцию LinkedList
        String queryDateResult;
        String queryNoteResult;
        noteResultList.clear();
        dateResultList.clear();
        try (Statement statement = cnt.createStatement()) {
            ResultSet resultSet = statement.executeQuery("select id, thenote, date  from thenotetable");
            if(resultSet != null) {
                while (resultSet.next()) {
                    queryDateResult = resultSet.getString(3);
                    dateResultList.add(queryDateResult);
                }
                resultSet.close();
                System.out.println("Соединение с БД закрыто");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try (Statement statement = cnt.createStatement()) {
            ResultSet resultSet = statement.executeQuery("select id, thenote, date  from thenotetable");
            if (resultSet != null) {
                while (resultSet.next()) {
                    queryNoteResult = resultSet.getString(2);
                    noteResultList.add(queryNoteResult);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
