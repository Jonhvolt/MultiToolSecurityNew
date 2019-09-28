package sample.service;

import sample.beans.Note;

import java.util.List;

public interface NoteService {

    List<Note> getNote();

    void saveNote(Note note);

    void deleteNote(Note note);
}
