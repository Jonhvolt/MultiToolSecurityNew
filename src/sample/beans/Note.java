package sample.beans;

public class Note {
    private int id;
    private String the_note;
    private String date;

    public Note(int id, String the_note, String date) {
        this.id = id;
        this.the_note = the_note;
        this.date = date;
    }

    public Note(String the_note, String date) {
        this.the_note = the_note;
        this.date = date;
    }

    public Note() {

    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getThe_note() {
        return the_note;
    }

    public void setThe_note(String the_note) {
        this.the_note = the_note;
    }

    public String getDate() {
        return date;
    }

    public void setDateMilliseconds(String date) {
        this.date = date;
    }
}
