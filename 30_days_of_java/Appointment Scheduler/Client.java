import java.io.Serializable;

public class Client implements Serializable {
    private String name;
    private String contactNumber;
    private String email;

    public Client(String name, String contactNumber, String email) {
        this.name = name;
        this.contactNumber = contactNumber;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public String getEmail() {
        return email;
    }

    // Add setters if needed
}
