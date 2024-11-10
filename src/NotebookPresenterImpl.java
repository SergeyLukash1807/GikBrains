import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class NotebookPresenterImpl implements NotebookPresenter {
    private NotebookModel model;
    private NotebookView view;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public NotebookPresenterImpl(NotebookModel model, NotebookView view) {
        this.model = model;
        this.view = view;
    }

    @Override
    public void addNote() {
        String dateInput = view.getUserInput("Введите дату и время записи (yyyy-MM-dd HH:mm): ");
        String description = view.getUserInput("Введите описание записи: ");

        try {
            LocalDateTime dateTime = LocalDateTime.parse(dateInput, formatter);
            model.addNote(new Note(dateTime, description));
            view.displayMessage("Запись добавлена.");
        } catch (Exception e) {
            view.displayMessage("Ошибка ввода даты. Убедитесь, что формат верен.");
        }
    }

    @Override
    public void displayNotesForDay() {
        String dateInput = view.getUserInput("Введите дату (yyyy-MM-dd): ");
        try {
            LocalDateTime date = LocalDateTime.parse(dateInput + " 00:00", formatter);
            List<Note> notes = model.getNotesForDay(date);
            view.displayNotes(notes);
        } catch (Exception e) {
            view.displayMessage("Ошибка ввода даты. Убедитесь, что формат верен.");
        }
    }

    @Override
    public void displayNotesForWeek() {
        String dateInput = view.getUserInput("Введите начало недели (yyyy-MM-dd): ");
        try {
            LocalDateTime date = LocalDateTime.parse(dateInput + " 00:00", formatter);
            List<Note> notes = model.getNotesForWeek(date);
            view.displayNotes(notes);
        } catch (Exception e) {
            view.displayMessage("Ошибка ввода даты. Убедитесь, что формат верен.");
        }
    }

    @Override
    public void saveNotes() {
        model.saveToFile("notes.dat");
        view.displayMessage("Записи сохранены.");
    }

    @Override
    public void loadNotes() {
        model.loadFromFile("notes.dat");
        view.displayMessage("Записи загружены.");
    }
}