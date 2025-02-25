import java.util.List;
import java.util.Scanner;

public class LibraryAppMenu {
    private static final Scanner scanner = new Scanner(System.in);
    private static final LibraryManager libraryManager = new LibraryManager();

    public static void displayMainMenu() {
        int choice;
        do {
            System.out.println("\n~~~~~~~~ LIBRARY MANAGEMENT SYSTEM ~~~~~~~~");
            System.out.println("1. Manage Books");
            System.out.println("2. Manage Users");
            System.out.println("3. Borrow & Return Books");
            System.out.println("4. View Statistics");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            choice = getUserChoice();

            switch (choice) {
                case 1 -> manageBooks();
                case 2 -> manageUsers();
                case 3 -> borrowReturnBooks();
                case 4 -> viewStatistics();
                case 5 -> System.out.println("Exiting program. Goodbye!");
                default -> System.out.println("Invalid choice, please try again.");
            }
        } while (choice != 5);
        scanner.close();
    }

    private static void manageBooks() {
        int choice;
        do {
            System.out.println("\n~~~ BOOK MANAGEMENT ~~~");
            System.out.println("1. Add a book");
            System.out.println("2. Update book details");
            System.out.println("3. Delete a book");
            System.out.println("4. List all books");
            System.out.println("5. Sort books by borrow count");
            System.out.println("6. Search books");
            System.out.println("7. Go back");
            System.out.print("Enter your choice: ");

            choice = getUserChoice();

            switch (choice) {
                case 1 -> addBook();
                case 2 -> updateBook();
                case 3 -> deleteBook();
                case 4 -> libraryManager.printBooks();
                case 5 -> sortBooksByBorrowedCount();
                case 6 -> searchBookByTitleAuthorGenre();
                case 7 -> System.out.println("Returning to main menu...");
                default -> System.out.println("Invalid choice, try again.");
            }
        } while (choice != 7);
    }

    private static void manageUsers() {
        int choice;
        do {
            System.out.println("\n~~~ USER MANAGEMENT ~~~");
            System.out.println("1. Register a user");
            System.out.println("2. Update user details");
            System.out.println("3. Delete a user");
            System.out.println("4. List all users");
            System.out.println("5. Go back");
            System.out.print("Enter your choice: ");

            choice = getUserChoice();

            switch (choice) {
                case 1 -> registerUser();
                case 2 -> updateUser();
                case 3 -> deleteUser();
                case 4 -> libraryManager.printUsers();
                case 5 -> System.out.println("Returning to main menu...");
                default -> System.out.println("Invalid choice, try again.");
            }
        } while (choice != 5);
    }

    private static void borrowReturnBooks() {
        int choice;
        do {
            System.out.println("\n~~~ BORROW & RETURN BOOKS ~~~");
            System.out.println("1. Borrow a book");
            System.out.println("2. Return a book");
            System.out.println("3. Go back");
            System.out.print("Enter your choice: ");

            choice = getUserChoice();

            switch (choice) {
                case 1 -> borrowBook();
                case 2 -> returnBook();
                case 3 -> System.out.println("Returning to main menu...");
                default -> System.out.println("Invalid choice, try again.");
            }
        } while (choice != 3);
    }

    private static void viewStatistics() {
        int choice;
        do {
            System.out.println("\n--- STATISTICS & REPORTS ---");
            System.out.println("1. Total number of books");
            System.out.println("2. Most borrowed books");
            System.out.println("3. Filter books by genre");
            System.out.println("4. Go back");
            System.out.print("Enter your choice: ");

            choice = getUserChoice();

            switch (choice) {
                case 1 -> System.out.println("Total books: " + libraryManager.countTotalBook());
                case 2 -> getMostPopularBooks();
                case 3 -> filterBooksByGenre();
                case 4 -> System.out.println("Returning to main menu...");
                default -> System.out.println("Invalid choice, try again.");
            }
        } while (choice != 4);
    }

    //  UTILITY FUNCTION
    private static int getUserChoice() {
        try {
            int choice = scanner.nextInt();
            scanner.nextLine();
            return choice;
        } catch (Exception e) {
            scanner.nextLine();
            return -1; // Return invalid choice
        }
    }

    // BOOK FUNCTIONS
    private static void addBook() {
        String title = InvalidInputHandle.getValidString("Enter book title: ");
        String author = InvalidInputHandle.getValidString("Enter book author: ");
        String genre = InvalidInputHandle.getValidString("Enter genre: ");
        int quantity = InvalidInputHandle.getPositiveInt("Enter quantity: ");
        int borrowedCount = InvalidInputHandle.getNonNegativeInt("Enter borrowed count: ");

        libraryManager.addBook(new Book(title, author, genre, quantity, borrowedCount));
        System.out.println("Book added successfully!");
    }

    private static void updateBook() {
        String title = InvalidInputHandle.getValidString("Enter book title: ");
        String author = InvalidInputHandle.getValidString("Enter book author: ");
        String genre = InvalidInputHandle.getValidString("Enter new genre: ");
        int quantity = InvalidInputHandle.getPositiveInt("Enter new quantity: ");
        int borrowedCount = InvalidInputHandle.getNonNegativeInt("Enter new borrowed count: ");

        libraryManager.updateBook(title, author, genre, quantity, borrowedCount);
        System.out.println("Book updated successfully!");
    }

    private static void deleteBook() {
        String title = InvalidInputHandle.getValidString("Enter book title: ");
        String author = InvalidInputHandle.getValidString("Enter book author: ");
        libraryManager.deleteBook(title, author);
    }

    private static void borrowBook() {
        String title = InvalidInputHandle.getValidString("Enter book title: ");
        String author = InvalidInputHandle.getValidString("Enter book author: ");
        String email = InvalidInputHandle.getValidEmail("Enter user email: ");
        String name = InvalidInputHandle.getValidString("Enter user name: ");

        libraryManager.borrowBook(title, author, email, name);
    }

    private static void returnBook() {
        String title = InvalidInputHandle.getValidString("Enter book title: ");
        String author = InvalidInputHandle.getValidString("Enter book author: ");
        String email = InvalidInputHandle.getValidEmail("Enter user email: ");
        String name = InvalidInputHandle.getValidString("Enter user name: ");

        libraryManager.returnBook(title, author, email, name);
    }

    private static void filterBooksByGenre() {
        String genre = InvalidInputHandle.getValidString("Enter new genre: ");
        List<Book> books = libraryManager.filterBookByGenre(genre);
        books.forEach(System.out::println);
    }

    private static void getMostPopularBooks() {
        int top = InvalidInputHandle.getPositiveInt("Enter number of top books to display: ");
        scanner.nextLine();
        List<Book> books = libraryManager.getMostPopularBook(top);
        books.forEach(System.out::println);
    }

    private static void sortBooksByBorrowedCount() {
        List<Book> sortedBooks = libraryManager.sortBookByBorrowedCountDescending();
        sortedBooks.forEach(System.out::println);
    }

    private static void searchBookByTitleAuthorGenre() {
        String title = InvalidInputHandle.getValidInput("Enter book title (or press Enter to skip): ");
        String author = InvalidInputHandle.getValidInput("Enter book author (or press Enter to skip): ");
        String genre = InvalidInputHandle.getValidInput("Enter book genre (or press Enter to skip): ");

        List<Book> foundBooks = libraryManager.searchBookByTitleAuthorGenre(title, author, genre);

        if (foundBooks.isEmpty()) {
            System.out.println("No books found matching the search criteria.");
        } else {
            System.out.println("\n~~~ SEARCH RESULTS ~~~");
            foundBooks.forEach(System.out::println);
        }
    }
    private static void registerUser() {
        String id = InvalidInputHandle.getValidID("Enter user ID:" );
        String name = InvalidInputHandle.getValidString("Enter user name: ");
        String email = InvalidInputHandle.getValidEmail("Enter user email: ");
        String phone = InvalidInputHandle.getValidPhone( "Enter phone: ");
        String password = InvalidInputHandle.getValidPassword("Enter password: ");

        User newUser = new User(id, name, email, phone, password );

        libraryManager.registerUser(newUser);
        System.out.println("User registration request processed.");
    }

    private static void updateUser() {
        String name = InvalidInputHandle.getValidString("Enter user name to update: ");
        String email = InvalidInputHandle.getValidEmail("Enter user email to update: ");
        String newName = InvalidInputHandle.getValidString("Enter new name: ");
        String newEmail = InvalidInputHandle.getValidEmail("Enter new email: ");

        libraryManager.updateUser(name, email, newName, newEmail);

        System.out.println("User update request processed.");
    }

    private static void deleteUser() {
        String email = InvalidInputHandle.getValidEmail("Enter user email to delete: ");

        libraryManager.deleteUser(email);
        System.out.println("User deletion request processed.");
    }

}

