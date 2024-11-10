import java.io.*;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class Notebook implements NotebookModel {
    private List<Note> notes = new ArrayList<>();

    @Override
    public void addNote(Note note) {
        notes.add(note);
        notes.sort(Comparator.comparing(Note::getDateTime));
    }

    @Override
    public List<Note> getNotesForDay(LocalDateTime date) {
        return notes.stream()
                .filter(note -> note.getDateTime().toLocalDate().equals(date.toLocalDate()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Note> getNotesForWeek(LocalDateTime startOfWeek) {
        LocalDateTime endOfWeek = startOfWeek.plusDays(7);
        return notes.stream()
                .filter(note -> !note.getDateTime().isBefore(startOfWeek) && note.getDateTime().isBefore(endOfWeek))
                .collect(Collectors.toList());
    }

    @Override
    public List<Note> getAllNotes() {
        return notes;
    }

    @Override
    public void saveToFile(String filePath) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(notes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void loadFromFile(String filePath) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            notes = (List<Note>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}