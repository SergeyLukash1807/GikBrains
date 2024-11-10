import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        NotebookModel model = new Notebook();
        NotebookView view = new ConsoleNotebookView();
        NotebookPresenter presenter = new NotebookPresenterImpl(model, view);

        presenter.loadNotes();

        boolean running = true;
        Scanner scanner = new Scanner(System.in);

        while (running) {
            System.out.println("\n1. Добавить запись\n2. Показать записи на день\n3. Показать записи на неделю\n4. Сохранить записи\n5. Загрузить записи\n6. Выйти");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    presenter.addNote();
                    break;
                case "2":
                    presenter.displayNotesForDay();
                    break;
                case "3":
                    presenter.displayNotesForWeek();
                    break;
                case "4":
                    presenter.saveNotes();
                    break;
                case "5":
                    presenter.loadNotes();
                    break;
                case "6":
                    running = false;
                    break;
                default:
                    System.out.println("Неверный выбор, попробуйте снова.");
            }
        }
    }
}