import java.time.LocalDateTime;
import java.util.List;

public class Admin extends Person {
    private String role;
    public List<String> permissions;
    private LocalDateTime createAt;

    public Admin(String id, String name, String email, String phone, String password) {
        super(id, name, email, phone, password);
    }

    // Construtor
    public Admin(String id, String name, String email, String phone, String password, String role, List<String> permissions, LocalDateTime createAt) {
        super(id, name, email, phone, password);
        this.role = role;
        this.permissions = permissions;
        this.createAt = createAt;
    }

    // Getter
    public String getRole() {
        return role;
    }

    public List<String> getPermissions() {
        return permissions;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    // Setter
    public void setRole(String role) {
        this.role = role;
    }

    public void setPermissions(List<String> permissions) {
        this.permissions = permissions;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }
}
