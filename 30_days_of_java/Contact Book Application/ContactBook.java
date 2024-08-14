import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ContactBook implements Serializable {
    private List<Contact> contacts;

    public ContactBook() {
        contacts = new ArrayList<>();
    }

    public boolean addContact(Contact contact) {
        if (contact.getPhone().isEmpty()) {
            return false;
        }
        if (getContactByPhone(contact.getPhone()) == null) {
            contacts.add(contact);
            return true;
        }
        return false;
    }

    public boolean editContact(int index, Contact newContact) {
        if (newContact.getPhone().isEmpty()) {
            return false;
        }
        Contact existingContact = getContactByPhone(newContact.getPhone());
        if (existingContact != null && contacts.indexOf(existingContact) != index) {
            return false;
        }
        contacts.set(index, newContact);
        return true;
    }

    public void deleteContact(int index) {
        contacts.remove(index);
    }

    public List<Contact> searchContacts(String query) {
        List<Contact> results = new ArrayList<>();
        for (Contact contact : contacts) {
            if (contact.getName().toLowerCase().contains(query.toLowerCase()) ||
                contact.getEmail().toLowerCase().contains(query.toLowerCase()) ||
                contact.getPhone().equals(query)) {
                results.add(contact);
            }
        }
        return results;
    }

    public Contact getContactByPhone(String phone) {
        for (Contact contact : contacts) {
            if (contact.getPhone().equals(phone)) {
                return contact;
            }
        }
        return null;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public void saveToFile(String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ContactBook loadFromFile(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            return (ContactBook) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return new ContactBook();
        }
    }
}