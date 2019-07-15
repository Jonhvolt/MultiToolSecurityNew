package sample.beans;

import javafx.beans.property.SimpleStringProperty;

public class Client {
    private int id;
    private SimpleStringProperty name ;
    private SimpleStringProperty address;
    private SimpleStringProperty contact_user;
    private SimpleStringProperty telephone_number;
    private SimpleStringProperty email;
    private SimpleStringProperty area_security;
    private SimpleStringProperty price_to_month;
    private SimpleStringProperty sim_cards;
    private SimpleStringProperty number_clients;
    private SimpleStringProperty notes;

    public Client(int id, String name, String address, String contact_user, String telephone_number, String email, String area_security, String price_to_month, String sim_cards, String number_clients , String notes){
        this.id = id;
        this.name = new SimpleStringProperty(name);
        this.address = new SimpleStringProperty(address);
        this.contact_user = new SimpleStringProperty(contact_user);
        this.telephone_number = new SimpleStringProperty(telephone_number);
        this.email = new SimpleStringProperty(email);
        this.area_security = new SimpleStringProperty(area_security);
        this.price_to_month = new SimpleStringProperty(price_to_month);
        this.sim_cards = new SimpleStringProperty(sim_cards);
        this.number_clients = new SimpleStringProperty(number_clients);
        this.notes = new SimpleStringProperty(notes);
    }

    public Client(String name, String address, String contact_user, String telephone_number, String email, String area_security, String price_to_month, String sim_cards, String number_clients , String notes){
        this.name = new SimpleStringProperty(name);
        this.address = new SimpleStringProperty(address);
        this.contact_user = new SimpleStringProperty(contact_user);
        this.telephone_number = new SimpleStringProperty(telephone_number);
        this.email = new SimpleStringProperty(email);
        this.area_security = new SimpleStringProperty(area_security);
        this.price_to_month = new SimpleStringProperty(price_to_month);
        this.sim_cards = new SimpleStringProperty(sim_cards);
        this.number_clients = new SimpleStringProperty(number_clients);
        this.notes = new SimpleStringProperty(notes);

    }

    public Client() {
        this.name = new SimpleStringProperty();
        this.address = new SimpleStringProperty();
        this.contact_user = new SimpleStringProperty();
        this.telephone_number = new SimpleStringProperty();
        this.email = new SimpleStringProperty();
        this.area_security = new SimpleStringProperty();
        this.price_to_month = new SimpleStringProperty();
        this.sim_cards = new SimpleStringProperty();
        this.number_clients = new SimpleStringProperty();
        this.notes = new SimpleStringProperty();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getAddress() {
        return address.get();
    }

    public void setAddress(String address) {
        this.address.set(address);
    }

    public String getContact_user() {
        return contact_user.get();
    }

    public void setContact_user(String contact_user) {
        this.contact_user.set(contact_user);
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

    public String getArea_security() {
        return area_security.get();
    }

    public void setArea_security(String area_security) {
        this.area_security.set(area_security);
    }

    public String getPrice_to_month() {
        return price_to_month.get();
    }

    public void setPrice_to_month(String price_to_month) {
        this.price_to_month.set(price_to_month);
    }

    public String getSim_cards() {
        return sim_cards.get();
    }

    public void setSim_cards(String sim_cards) {
        this.sim_cards.set(sim_cards);
    }

    public String getNumber_clients() {
        return number_clients.get();
    }

    public void setNumber_clients(String number_clients) {
        this.number_clients.set(number_clients);
    }

    public String getNotes() {
        return notes.get();
    }

    public void setNotes(String notes) {
        this.notes.set(notes);
    }

    public SimpleStringProperty getterName() {
        return name;
    }

    public SimpleStringProperty getterAddress() {
        return address;
    }

    public SimpleStringProperty getterContactUser() {
        return contact_user;
    }

    public SimpleStringProperty getterTelephoneNumber() {
        return telephone_number;
    }

    public SimpleStringProperty getterEmail() {
        return email;
    }

    public SimpleStringProperty getterAreaSecutiry() {
        return area_security;
    }

    public SimpleStringProperty getterPriceToMonth() {
        return price_to_month;
    }

    public SimpleStringProperty getterNotes() {
        return notes;
    }

    public SimpleStringProperty getterSimCards() {
        return sim_cards;
    }

    public SimpleStringProperty getterNumberClients() {
        return number_clients;
    }
}
