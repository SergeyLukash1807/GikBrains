import java.util.List;

public interface NotebookView {
    void displayMessage(String message);
    void displayNotes(List<Note> notes);
    String getUserInput(String prompt);
}
