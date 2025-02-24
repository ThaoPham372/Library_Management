public class Book {
    // Attribute
    private String title;
    private String author;
    private String genre;
    private int quantity;
    private int borrowCount;

    // Constructor
    public Book(String title, String author, String genre, int quantity, int borrowCount) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.quantity = quantity;
        this.borrowCount = borrowCount;
    }

    // Getter
    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getGenre() {
        return genre;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getBorrowCount() {
        return borrowCount;
    }

    // Setter
    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setBorrowCount(int borrowCount) {
        this.borrowCount = borrowCount;
    }

    // Method
    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", genre='" + genre + '\'' +
                ", quantity=" + quantity +
                ", borrowCount=" + borrowCount +
                '}';
    }
}
