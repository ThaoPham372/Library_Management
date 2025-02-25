import java.util.Scanner;

public class InvalidInputHandle {
    private static final Scanner scanner = new Scanner(System.in);

    // Kiểm tra xem chuỗi có chứa số không
    public static boolean containsNumber(String str) {
        return str.matches(".*\\d.*");
    }

    // Nhập chuỗi không rỗng và không chứa số (dùng cho title, author, genre)
    public static String getValidString(String prompt) {
        String input;
        do {
            System.out.print(prompt);
            input = scanner.nextLine().trim();
            if (input.isEmpty() || containsNumber(input)) {
                System.out.println("Invalid input! It cannot be empty or contain numbers.");
            }
        } while (input.isEmpty() || containsNumber(input));
        return input;
    }

    // Nhập số nguyên dương (dùng cho quantity)
    public static int getPositiveInt(String prompt) {
        int value;
        do {
            System.out.print(prompt);
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input! Must be a number.");
                System.out.print(prompt);
                scanner.nextLine(); // Xóa dữ liệu sai
            }
            value = scanner.nextInt();
            scanner.nextLine(); // Xóa dòng thừa
            if (value <= 0) {
                System.out.println("Value must be greater than 0.");
            }
        } while (value <= 0);
        return value;
    }

    // Nhập số nguyên không âm (dùng cho borrowed count)
    public static int getNonNegativeInt(String prompt) {
        int value;
        do {
            System.out.print(prompt);
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input! Must be a number.");
                System.out.print(prompt);
                scanner.nextLine(); // Xóa dữ liệu sai
            }
            value = scanner.nextInt();
            scanner.nextLine(); // Xóa dòng thừa
            if (value < 0) {
                System.out.println("Value cannot be negative.");
            }
        } while (value < 0);
        return value;
    }
    // Kiểm tra email có hợp lệ không
    public static boolean isValidEmail(String email) {
        return email.matches("^[\\w.-]+@[a-zA-Z\\d.-]+\\.[a-zA-Z]{2,6}$");
    }

    // Nhập email hợp lệ
    public static String getValidEmail(String prompt) {
        String email;
        do {
            System.out.print(prompt);
            email = scanner.nextLine().trim();
            if (!isValidEmail(email)) {
                System.out.println("Invalid email format! Example: example@gmail.com");
            }
        } while (!isValidEmail(email));
        return email;
    }

    // Nhập số điện thoại hợp lệ (10 số)
    public static String getValidPhone(String prompt) {
        String phone;
        do {
            System.out.print(prompt);
            phone = scanner.nextLine().trim();
            if (!phone.matches("\\d{10}")) {
                System.out.println("Invalid phone number! Must be 10 digits.");
            }
        } while (!phone.matches("\\d{10}"));
        return phone;
    }

    // Nhập mật khẩu (ít nhất 6 ký tự)
    public static String getValidPassword(String prompt) {
        String password;
        do {
            System.out.print(prompt);
            password = scanner.nextLine().trim();
            if (password.length() < 6) {
                System.out.println("Password too short! Must be at least 6 characters.");
            }
        } while (password.length() < 6);
        return password;
    }

    // Nhập ID (Chỉ chứa chữ & số, ít nhất 4 ký tự)
    public static String getValidID(String prompt) {
        String id;
        do {
            System.out.print(prompt);
            id = scanner.nextLine().trim();
            if (!id.matches("[a-zA-Z0-9]{4,}")) {
                System.out.println("Invalid ID! Must be at least 4 characters and contain only letters & numbers.");
            }
        } while (!id.matches("[a-zA-Z0-9]{4,}"));
        return id;
    }
    public static String getValidInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();

            // Cho phép bỏ qua nếu input rỗng
            if (input.isEmpty()) {
                return "";
            }

            // Kiểm tra ký tự hợp lệ (chỉ cho phép chữ, số, khoảng trắng và một số dấu câu)
            if (input.matches("^[a-zA-Z .,!?'-]+$")) {
                return input;
            } else {
                System.out.println("Invalid input! Please enter again.");
            }
        }
    }
}
