import java.util.List;

public class User extends Person {
    private List<String> borrowBooks;
    private int maxBookAllowed = 5;

    public User(String id, String name, String email, String phone, String password) {
        super(id, name, email, phone, password);
    }

    // Constructor
    public User(String id, String name, String email, String phone, String password, List<String> borrowBooks, int maxBookAllowed) {
        super(id, name, email, phone, password);
        this.borrowBooks = borrowBooks;
        this.maxBookAllowed = maxBookAllowed;
    }

    // Getter
    public List<String> getBorrowBooks() {
        return borrowBooks;
    }

    public int getMaxBookAllowed() {
        return maxBookAllowed;
    }

    //Setter
    public void setBorrowBooks(List<String> borrowBooks) {
        this.borrowBooks = borrowBooks;
    }

    public void setMaxBookAllowed(int maxBookAllowed) {
        this.maxBookAllowed = maxBookAllowed;
    }
}

