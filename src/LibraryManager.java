import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class LibraryManager implements Manageable {
    private List<Book> books;
    private List<User> users;

    // Constructor

    public LibraryManager(List<Book> books, List<User> users) {
        this.books = new ArrayList<>();
        this.users = new ArrayList<>();
    }

    public LibraryManager() {
        this.books = new ArrayList<>();
    }

    @Override
    public void addBook(Book book) {
        books.add(book);
        System.out.println("Book added: " + book);
    }

    @Override
    public void updateBook(String title, String author, String newGenre, int newQuantity, int newBorrowedCount) {
        if (title == null || title.isEmpty() || author == null || author.isEmpty()) {
            System.out.println("Invalid input. Title or author are empty");
            return;
        }
        if (newQuantity < 0 || newBorrowedCount < 0) {
            System.out.println("Invalid input. Quantity or Borrowed count cannot be negative");
            return;
        }
        for (Book book : books) {
            if (book.getTitle().equals(title) && book.getAuthor().equals(author)) {
                book.setGenre(newGenre);
                book.setQuantity(newQuantity);
                book.setBorrowCount(newBorrowedCount);
                System.out.println("Book updated: " + book);
                return;
            }
        }
        System.out.println("Book not found");
    }

    @Override
    public void deleteBook(String title, String author) {
        boolean removeBook = books.removeIf(book -> book.getTitle().equals(title) && book.getAuthor().equals(author));
        System.out.println(removeBook ? "Book deleted successfully" : "Book not found");
    }

    @Override
    public void registerUser(User user) {
        users.add(user);
        System.out.println("User registered: " + user);
    }

    @Override
    public void updateUser(String email, String name, String newPhone, String newPassword) {
        if (email == null || email.isEmpty() || name == null || name.isEmpty()) {
            System.out.println("Invalid input. Email or name are empty");
            return;
        }
        if (newPhone == null || newPhone.isEmpty() || newPassword == null || newPassword.isEmpty()) {
            System.out.println("Invalid input. Phone or password are empty");
            return;
        }
        for (User user : users) {
            if (user.getEmail().equals(email) && user.getName().equals(name) && user.getPassword().equals(newPassword)) {
                user.setPhone(newPhone);
                user.setPassword(newPassword);
                System.out.println("User updated: " + user);
                return;
            }
        }
        System.out.println("User not found");
    }

    @Override
    public void deleteUser(String email) {
        boolean removeUser = users.removeIf(user -> user.getEmail().equals(email));
        System.out.println(removeUser ? "User deleted successfully" : "User not found");
    }

    @Override
    public void borrowBook(String title, String author, String email, String name) {
        if (title == null || title.isEmpty() || author == null || author.isEmpty() ||
                email == null || email.isEmpty() || name == null || name.isEmpty()) {
            System.out.println("Invalid input! Title, author, email, and name cannot be empty.");
            return;
        }
        User user = findUserByEmailAndName(email, name);
        Book book = findBookByTitleAndAuthor(title, author);

        if (user == null || book == null ) {
            System.out.println("User or book not found!");
            return;
        }

        if (!canBorrowMoreBooks(user)) {
            System.out.println("User has reached the borrowing limit!");
            return;
        }

        if (book.getQuantity() > 0) {
            book.setQuantity(book.getQuantity() - 1);
            book.setBorrowCount(book.getBorrowCount() + 1);
            user.getBorrowBooks().add(title);
            System.out.println("Book borrowed: " + title + " by " + user.getName());
        } else {
            System.out.println("Book is out of stock!");
        }
    }
    @Override
    public void returnBook(String title, String author, String email, String name) {
        if (title == null || title.isEmpty() || author == null || author.isEmpty() ||
                email == null || email.isEmpty() || name == null || name.isEmpty()) {
            System.out.println("Invalid input! Title, author, email, and name cannot be empty.");
            return;
        }
        User user = findUserByEmailAndName(email, name);
        Book book = findBookByTitleAndAuthor(title, author);

        if (user == null || book == null ){
            System.out.println("User or book not found!");
            return;
        }
        if (user.getBorrowBooks().remove(title)) {
            book.setQuantity(book.getQuantity() + 1);
            System.out.println("Book returned: " + title + " by " + user.getName());
        }else {
            System.out.println("Book is out of stock!");
        }
    }

    @Override
    public boolean isBookAvailable(String title, String author) {
        Book book = findBookByTitleAndAuthor(title, author);
        return book != null && book.getQuantity() > 0;
    }

    @Override
    public boolean canBorrowMoreBooks(User user) {
        return user.getBorrowBooks().size() < user.getMaxBookAllowed();
    }

    @Override
    public List<Book> filterBookByGenre(String genre) {
        if(genre == null || genre.isEmpty()) {
            System.out.println("Invalid input. Genre is empty");
            return new ArrayList<>();
        }
        return books.stream()
                .filter(book -> book.getGenre().equalsIgnoreCase(genre))
                .collect(Collectors.toList());
    }
    @Override
    public List<Book> sortBookByBorrowedCountDescending() {
        return books.stream()
                .sorted(Comparator.comparingInt(Book::getBorrowCount).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public List<Book> searchBookByTitleAuthorGenre(String title, String author, String genre) {
        if ((title == null || title.isEmpty()) &&
                (author == null || author.isEmpty()) &&
                (genre == null || genre.isEmpty())){
                System.out.println("Please provide at least one search criteria.");
        }
        List<Book> result = new ArrayList<>();
        for (Book book : books) {
                      if ((title == null || title.isEmpty() || book.getTitle().equalsIgnoreCase(title)) &&
                         (author == null || author.isEmpty() || book.getAuthor().equalsIgnoreCase(author)) &&
                         (genre == null || genre.isEmpty() || book.getGenre().equalsIgnoreCase(genre))){
                          result.add(book);
            }
        }
        return result;
    }

    @Override
    public int countTotalBook() {
        if (books.isEmpty()){
            System.out.println("No books available in the library.");
            return 0;
        }
        int total = 0;
        for (Book book : books) {
            total += book.getQuantity();
        }
        return total;
    }

    @Override
    public List<Book> getMostPopularBook(int top) {
        if (top <= 0) {
            System.out.println("Invalid input! The number of top books must be greater than 0.");
            return new ArrayList<>();
        }
        if (books.isEmpty()) {
            System.out.println("No books available in the library.");
            return new ArrayList<>();
        }
        return books.stream()
                .sorted(Comparator.comparingInt(Book::getBorrowCount).reversed())
                .limit(top)
                .collect(Collectors.toList());
    }

    public List<Book> findMostBorrowedBooks() {
        int maxBorrowCount = books.stream()
                .mapToInt(Book::getBorrowCount)
                .max()
                .orElse(0);

        return books.stream()
                .filter(book -> book.getBorrowCount() == maxBorrowCount)
                .collect(Collectors.toList());
    }

    public void printBooks() {
        for (Book book : books) {
            System.out.println(book);
        }
    }

    public void printUsers() {
        for (User user : users) {
            System.out.println(user);
        }
    }

    private User findUserByEmailAndName(String email, String name) {
        for (User user : users) {
            if (user.getEmail().equals(email) && user.getName().equals(name)) {
                return user;
            }
        }
        return null;
    }
    private Book findBookByTitleAndAuthor(String title, String author) {
        for (Book book : books) {
            if (book.getTitle().equals(title) && book.getAuthor().equals(author)) {
                return book;
            }
        }
        return null;

    }
}
