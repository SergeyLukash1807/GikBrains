import java.util.List;
import java.util.Scanner;

public class ConsoleNotebookView implements NotebookView {
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public void displayMessage(String message) {
        System.out.println(message);
    }

    @Override
    public void displayNotes(List<Note> notes) {
        if (notes.isEmpty()) {
            System.out.println("Записей нет.");
        } else {
            notes.forEach(System.out::println);
        }
    }

    @Override
    public String getUserInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }
}