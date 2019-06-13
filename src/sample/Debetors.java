package sample;

import javafx.beans.property.SimpleStringProperty;

public class Debetors {
    private int id;
    private SimpleStringProperty nameDebetor = new SimpleStringProperty();
    private SimpleStringProperty totalDebt = new SimpleStringProperty();
    private SimpleStringProperty lastPayment = new SimpleStringProperty();
    private SimpleStringProperty comments = new SimpleStringProperty();
    private SimpleStringProperty telephonNumber = new SimpleStringProperty();
    private SimpleStringProperty email = new SimpleStringProperty();

    public Debetors() {
        this.nameDebetor = new SimpleStringProperty();
        this.totalDebt = new SimpleStringProperty();
        this.lastPayment = new SimpleStringProperty();
        this.comments = new SimpleStringProperty();
        this.telephonNumber = new SimpleStringProperty();
        this.email = new SimpleStringProperty();
    }

    public Debetors(String nameDebetor, String totalDebt, String lastPayment, String comments, String telephoneNumber, String email) {
        this.nameDebetor = new SimpleStringProperty(nameDebetor);
        this.totalDebt = new SimpleStringProperty(totalDebt);
        this.lastPayment = new SimpleStringProperty(lastPayment);
        this.comments = new SimpleStringProperty(comments);
        this.telephonNumber = new SimpleStringProperty(telephoneNumber);
        this.email = new SimpleStringProperty(email);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameDebetor() {
        return nameDebetor.get();
    }

    public SimpleStringProperty nameDebetorProperty() {
        return nameDebetor;
    }

    public void setNameDebetor(String nameDebetor) {
        this.nameDebetor.set(nameDebetor);
    }

    public String getTotalDebt() {
        return totalDebt.get();
    }

    public SimpleStringProperty totalDebtProperty() {
        return totalDebt;
    }

    public void setTotalDebt(String totalDebt) {
        this.totalDebt.set(totalDebt);
    }

    public String getLastPayment() {
        return lastPayment.get();
    }

    public SimpleStringProperty lastPaymentProperty() {
        return lastPayment;
    }

    public void setLastPayment(String lastPayment) {
        this.lastPayment.set(lastPayment);
    }

    public String getComments() {
        return comments.get();
    }

    public SimpleStringProperty commentsProperty() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments.set(comments);
    }

    public String getTelephonNumber() {
        return telephonNumber.get();
    }

    public SimpleStringProperty telephonNumberProperty() {
        return telephonNumber;
    }

    public void setTelephonNumber(String telephonNumber) {
        this.telephonNumber.set(telephonNumber);
    }

    public String getEmail() {
        return email.get();
    }

    public SimpleStringProperty emailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public SimpleStringProperty getterNameDebetor() {
        return nameDebetor;
    }

    public SimpleStringProperty getterTotalDebt() {
        return totalDebt;
    }

    public SimpleStringProperty getterLastPayment() {
        return lastPayment;
    }

    public SimpleStringProperty getterComments() {
        return comments;
    }

    public SimpleStringProperty getterTelephoneNumber() {
        return telephonNumber;
    }

    public SimpleStringProperty getterEmail() {
        return email;
    }
}
