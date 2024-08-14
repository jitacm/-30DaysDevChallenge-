import java.io.Serializable;

public class Contact implements Serializable {
    private String name;
    private String phone;
    private String email;
    private String category;

    public Contact(String name, String phone, String email, String category) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.category = category;
    }

    // Getters and setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    @Override
    public String toString() {
        return name + " (" + category + ")";
    }
}
