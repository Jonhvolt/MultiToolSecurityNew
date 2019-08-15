package sample.beans;

public class ClientIntegrator {

    private int id;
    private String name;
    private String address;
    private String contact_user;
    private String telephone_number;
    private String email;
    private String area_security;
    private String price_to_month;
    private String sim_cards;
    private String number_clients;
    private String notes;
   // private boolean sign_contract;

    public ClientIntegrator(Client client) {
        this.id = client.getId();
            this.name = client.getName();
            this.address = client.getAddress();
            this.contact_user = client.getContact_user();
            this.telephone_number = client.getTelephone_number();
            this.email = client.getEmail();
            this.area_security = client.getArea_security();
            this.price_to_month = client.getPrice_to_month();
            this.sim_cards = client.getSim_cards();
            this.number_clients = client.getNumber_clients();
            this.notes = client.getNotes();
           // this.sign_contract = client.getSign_contract();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact_user() {
        return contact_user;
    }

    public void setContact_user(String contact_user) {
        this.contact_user = contact_user;
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

    public String getArea_security() {
        return area_security;
    }

    public void setArea_security(String area_security) {
        this.area_security = area_security;
    }

    public String getPrice_to_month() {
        return price_to_month;
    }

    public void setPrice_to_month(String price_to_month) {
        this.price_to_month = price_to_month;
    }

    public String getSim_cards() {
        return sim_cards;
    }

    public void setSim_cards(String sim_cards) {
        this.sim_cards = sim_cards;
    }

    public String getNumber_clients() {
        return number_clients;
    }

    public void setNumber_clients(String number_clients) {
        this.number_clients = number_clients;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }


    /*
    public boolean isSign_contract() {
        return sign_contract;
    }

    public void setSign_contract(boolean sign_contract) {
        this.sign_contract = sign_contract;
    }
     */

}
