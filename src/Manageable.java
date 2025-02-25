import java.util.List;

public interface Manageable {
    // Manage Book
    void addBook(Book book);
    void updateBook(String title, String author,  String newGenre, int newQuantity, int newBorrowedCount);
    void deleteBook(String title, String author);

    // Manage User
    void registerUser (User user);
    void updateUser (String email, String name, String newPhone, String newPassword);
    void deleteUser (String email);

    // Borrow and Return Book
    void borrowBook (String title, String author, String email, String name);
    void returnBook (String title, String author, String email, String name);
    boolean isBookAvailable(String title, String author);
    boolean canBorrowMoreBooks(User user);

    // StreamAPI
    List<Book> filterBookByGenre(String genre);
    List<Book> sortBookByBorrowedCountDescending();

    //Search and Statistic
    List<Book> searchBookByTitleAuthorGenre(String title, String author, String genre);
    int countTotalBook();
    List<Book> getMostPopularBook(int top);
}

