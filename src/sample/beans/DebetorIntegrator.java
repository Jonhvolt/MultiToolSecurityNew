package sample.beans;

public class DebetorIntegrator {

    private int id;
    private String name_debetor;
    private String total_debt;
    private String last_payment;
    private String comments;
    private String telephone_number;
    private String email;

    public DebetorIntegrator(Debetors debetor) {
        this.id = debetor.getId();
        this.name_debetor = debetor.getName_debetor();
        this.total_debt = debetor.getTotal_debt();
        this.last_payment = debetor.getLast_payment();
        this.comments = debetor.getComments();
        this.telephone_number = debetor.getTelephone_number();
        this.email = debetor.getEmail();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName_debetor() {
        return name_debetor;
    }

    public void setName_debetor(String name_debetor) {
        this.name_debetor = name_debetor;
    }

    public String getTotal_debt() {
        return total_debt;
    }

    public void setTotal_debt(String total_debt) {
        this.total_debt = total_debt;
    }

    public String getLast_payment() {
        return last_payment;
    }

    public void setLast_payment(String last_payment) {
        this.last_payment = last_payment;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getTelephone_number() {
        return telephone_number;
    }

    public void setTelephone_number(String telephone_number) {
        this.telephone_number = telephone_number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
