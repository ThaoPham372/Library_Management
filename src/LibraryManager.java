import java.util.ArrayList;
import java.util.List;

public class LibraryManager implements Manageable{
    private List<Book> books;
    private List<User> users;

    // Constructor

    public LibraryManager(List<Book> books, List<User> users) {
        this.books = new ArrayList<>();
        this.users = new ArrayList<>();
    }

    @Override
    public void addBook() {

    }

    @Override
    public void updateBook() {

    }

    @Override
    public void deleteBook() {
    }
}
