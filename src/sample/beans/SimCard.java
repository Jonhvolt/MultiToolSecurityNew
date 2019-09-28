package sample.beans;

import javafx.beans.property.SimpleStringProperty;

public class SimCard {

    private int id;
    private SimpleStringProperty name_client;
    private SimpleStringProperty kit; //комплект
    private SimpleStringProperty number_one;
    private SimpleStringProperty number_two;
    private SimpleStringProperty the_note;
    private int client_id;

    public SimCard() {

    }

    public SimCard(int id, String name_client, String kit, String number_one, String number_two, String the_note, int client_id) {
        this.id = id;
        this.name_client = new SimpleStringProperty(name_client);
        this.kit = new SimpleStringProperty(kit);
        this.number_one = new SimpleStringProperty(number_one);
        this.number_two = new SimpleStringProperty(number_two);
        this.the_note = new SimpleStringProperty(the_note);
        this.client_id = client_id;
    }


    public SimCard(String name_client, String kit, String number_one, String number_two, String the_note, int client_id) {
        this.name_client = new SimpleStringProperty(name_client);
        this.kit = new SimpleStringProperty(kit);
        this.number_one = new SimpleStringProperty(number_one);
        this.number_two = new SimpleStringProperty(number_two);
        this.the_note = new SimpleStringProperty(the_note);
        this.client_id = client_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName_client() {
        return name_client.get();
    }

    public void setName_client(String name_client) {
        this.name_client.set(name_client);
    }

    public String getKit() {
        return kit.get();
    }

    public void setKit(String kit) {
        this.kit.set(kit);
    }

    public String getNumber_one() {
        return number_one.get();
    }

    public void setNumber_one(String number_one) {
        this.number_one.set(number_one);
    }

    public String getNumber_two() {
        return number_two.get();
    }

    public void setNumber_two(String number_two) {
        this.number_two.set(number_two);
    }

    public String getThe_note() {
        return the_note.get();
    }

    public void setThe_note(String the_note) {
        this.the_note.set(the_note);
    }

    public int getClient_id() {
        return client_id;
    }

    public void setClient_id(int client_id) {
        this.client_id = client_id;
    }

    public SimpleStringProperty getterName_client() {
        return name_client;
    }

    public SimpleStringProperty getterKit() {
        return kit;
    }

    public SimpleStringProperty getterNumberOne() {
        return number_one;
    }

    public SimpleStringProperty getterNumberTwo() {
        return number_two;
    }

    public SimpleStringProperty getterTheNote() {
        return the_note;
    }
}
