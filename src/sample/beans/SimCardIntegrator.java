package sample.beans;

public class SimCardIntegrator {
    private int id;
    private String name_client;
    private String kit;
    private String number_one;
    private String number_two;
    private String the_note;
    private int client_id;

    public SimCardIntegrator(SimCard simCard) {
        this.id = simCard.getId();
        this.name_client = simCard.getName_client();
        this.kit = simCard.getKit();
        this.number_one = simCard.getNumber_one();
        this.number_two = simCard.getNumber_two();
        this.the_note = simCard.getThe_note();
        this.client_id = simCard.getClient_id();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName_client() {
        return name_client;
    }

    public void setName_client(String name_client) {
        this.name_client = name_client;
    }

    public String getKit() {
        return kit;
    }

    public void setKit(String kit) {
        this.kit = kit;
    }

    public String getNumber_one() {
        return number_one;
    }

    public void setNumber_one(String number_one) {
        this.number_one = number_one;
    }

    public String getNumber_two() {
        return number_two;
    }

    public void setNumber_two(String number_two) {
        this.number_two = number_two;
    }

    public String getThe_note() {
        return the_note;
    }

    public void setThe_note(String the_note) {
        this.the_note = the_note;
    }

    public int getClient_id() {
        return client_id;
    }

    public void setClient_id(int client_id) {
        this.client_id = client_id;
    }
}
