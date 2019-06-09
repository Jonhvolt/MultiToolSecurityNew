package sample;

import javafx.beans.property.SimpleStringProperty;

public class Client {
    private int id;
    private SimpleStringProperty name = new SimpleStringProperty("");
    private SimpleStringProperty address = new SimpleStringProperty("");
    private SimpleStringProperty contactUser = new SimpleStringProperty("");
    private SimpleStringProperty telephoneNumber = new SimpleStringProperty("");
    private SimpleStringProperty email = new SimpleStringProperty("");
    private SimpleStringProperty areaSecurity = new SimpleStringProperty("");
    private SimpleStringProperty priceToMonth = new SimpleStringProperty("");
    private SimpleStringProperty simCards = new SimpleStringProperty("");
    private SimpleStringProperty numberClients = new SimpleStringProperty("");
    private SimpleStringProperty notes = new SimpleStringProperty("");

    public Client(String name, String address, String contactUser, String telephoneNumber, String email, String areaSecurity, String priceToMonth, String notes){
        this.name = new SimpleStringProperty(name);
        this.address = new SimpleStringProperty(address);
        this.contactUser = new SimpleStringProperty(contactUser);
        this.telephoneNumber = new SimpleStringProperty(telephoneNumber);
        this.email = new SimpleStringProperty(email);
        this.areaSecurity = new SimpleStringProperty(areaSecurity);
        this.priceToMonth = new SimpleStringProperty(priceToMonth);
        this.notes = new SimpleStringProperty(notes);

    }

    public Client() {
        this.name = new SimpleStringProperty();
        this.address = new SimpleStringProperty();
        this.contactUser = new SimpleStringProperty();
        this.telephoneNumber = new SimpleStringProperty();
        this.email = new SimpleStringProperty();
        this.areaSecurity = new SimpleStringProperty();
        this.priceToMonth = new SimpleStringProperty();
        this.simCards = new SimpleStringProperty();
        this.numberClients = new SimpleStringProperty();
        this.notes = new SimpleStringProperty();
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

    public String getContactUser() {
        return contactUser.get();
    }

    public void setContactUser(String contactUser) {
        this.contactUser.set(contactUser);
    }

    public String getTelephoneNumber() {
        return telephoneNumber.get();
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber.set(telephoneNumber);
    }

    public String getEmail() {
        return email.get();
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public String getAreaSecurity() {
        return areaSecurity.get();
    }

    public void setAreaSecurity(String areaSecurity) {
        this.areaSecurity.set(areaSecurity);
    }

    public String getPriceToMonth() {
        return priceToMonth.get();
    }

    public void setPriceToMonth(String priceToMonth) {
        this.priceToMonth.set(priceToMonth);
    }

    public String getSimCards() {
        return simCards.get();
    }

    public SimpleStringProperty simCardsProperty() {
        return simCards;
    }

    public void setSimCards(String simCards) {
        this.simCards.set(simCards);
    }

    public String getNumberClients() {
        return numberClients.get();
    }

    public SimpleStringProperty numberClientsProperty() {
        return numberClients;
    }

    public void setNumberClients(String numberClients) {
        this.numberClients.set(numberClients);
    }

    public String getNotes() {
        return notes.get();
    }

    public void setNotes(String notes) {
        this.notes.set(notes);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public SimpleStringProperty getterName() {
        return name;
    }

    public SimpleStringProperty getterAddress() {
        return address;
    }

    public SimpleStringProperty getterContactUser() {
        return contactUser;
    }

    public SimpleStringProperty getterTelephoneNumber() {
        return telephoneNumber;
    }

    public SimpleStringProperty getterEmail() {
        return email;
    }

    public SimpleStringProperty getterAreaSecutiry() {
        return areaSecurity;
    }

    public SimpleStringProperty getterPriceToMonth() {
        return priceToMonth;
    }

    public SimpleStringProperty getterNotes() {
        return notes;
    }

    public SimpleStringProperty getterSimCards() {
        return simCards;
    }

    public SimpleStringProperty getterNumberClients() {
        return numberClients;
    }
}
