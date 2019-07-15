package sample.beans;

import javafx.beans.property.SimpleStringProperty;

public class Debetors {
    private int id;
    private SimpleStringProperty name_debetor;
    private SimpleStringProperty total_debt;
    private SimpleStringProperty last_payment;
    private SimpleStringProperty comments;
    private SimpleStringProperty telephone_number;
    private SimpleStringProperty email;

    public Debetors(int id, String name_debetor, String total_debt, String last_payment, String comments, String telephone_number, String email) {
        this.id = id;
        this.name_debetor = new SimpleStringProperty(name_debetor);
        this.total_debt = new SimpleStringProperty(total_debt);
        this.last_payment = new SimpleStringProperty(last_payment);
        this.comments = new SimpleStringProperty(comments);
        this.telephone_number = new SimpleStringProperty(telephone_number);
        this.email = new SimpleStringProperty(email);
    }

    public Debetors(String name_debetor, String total_debt, String last_payment, String comments, String telephone_number, String email) {
        this.name_debetor = new SimpleStringProperty(name_debetor);
        this.total_debt = new SimpleStringProperty(total_debt);
        this.last_payment = new SimpleStringProperty(last_payment);
        this.comments = new SimpleStringProperty(comments);
        this.telephone_number = new SimpleStringProperty(telephone_number);
        this.email = new SimpleStringProperty(email);
    }

    public Debetors() {
        this.name_debetor = new SimpleStringProperty();
        this.total_debt = new SimpleStringProperty();
        this.last_payment = new SimpleStringProperty();
        this.comments = new SimpleStringProperty();
        this.telephone_number = new SimpleStringProperty();
        this.email = new SimpleStringProperty();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName_debetor() {
        return name_debetor.get();
    }

    public void setName_debetor(String name_debetor) {
        this.name_debetor.set(name_debetor);
    }

    public String getTotal_debt() {
        return total_debt.get();
    }

    public void setTotal_debt(String total_debt) {
        this.total_debt.set(total_debt);
    }

    public String getLast_payment() {
        return last_payment.get();
    }

    public void setLast_payment(String last_payment) {
        this.last_payment.set(last_payment);
    }

    public String getComments() {
        return comments.get();
    }

    public void setComments(String comments) {
        this.comments.set(comments);
    }

    public String getTelephone_number() {
        return telephone_number.get();
    }

    public void setTelephone_number(String telephone_number) {
        this.telephone_number.set(telephone_number);
    }

    public String getEmail() {
        return email.get();
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public SimpleStringProperty getterNameDebetor() {
        return name_debetor;
    }

    public SimpleStringProperty getterTotalDebt() {
        return total_debt;
    }

    public SimpleStringProperty getterLastPayment() {
        return last_payment;
    }

    public SimpleStringProperty getterComments() {
        return comments;
    }

    public SimpleStringProperty getterTelephoneNumber() {
        return telephone_number;
    }

    public SimpleStringProperty getterEmail() {
        return email;
    }
}
