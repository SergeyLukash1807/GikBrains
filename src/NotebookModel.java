import java.time.LocalDateTime;
import java.util.List;

public interface NotebookModel {
    void addNote(Note note);
    List<Note> getNotesForDay(LocalDateTime date);
    List<Note> getNotesForWeek(LocalDateTime startOfWeek);
    List<Note> getAllNotes();
    void saveToFile(String filePath);
    void loadFromFile(String filePath);
}