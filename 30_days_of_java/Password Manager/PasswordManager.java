import javax.crypto.*;
import javax.crypto.spec.*;
import java.util.*;
import java.io.*;

public class PasswordManager implements Serializable {
    private static final long serialVersionUID = 1L;
    private Map<String, Map<String, String>> passwords;
    private transient String masterPassword;
    private static final String SALT = "RandomSaltValue";
    private static final String FILENAME = "passwords.dat";

    public PasswordManager(String masterPassword) {
        this.masterPassword = masterPassword;
        this.passwords = new HashMap<>();
        loadPasswords();
    }

    public void addPassword(String category, String account, String password) {
        String encryptedPassword = encrypt(password);
        passwords.computeIfAbsent(category, k -> new HashMap<>()).put(account, encryptedPassword);
        savePasswords();
    }

    public String getPassword(String category, String account) {
        Map<String, String> categoryPasswords = passwords.get(category);
        if (categoryPasswords != null) {
            String encryptedPassword = categoryPasswords.get(account);
            if (encryptedPassword != null) {
                return decrypt(encryptedPassword);
            }
        }
        return null;
    }

    public String generatePassword(int length) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()_+";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(chars.charAt(random.nextInt(chars.length())));
        }
        return sb.toString();
    }

    private String encrypt(String password) {
        try {
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            PBEKeySpec spec = new PBEKeySpec(masterPassword.toCharArray(), SALT.getBytes(), 65536, 256);
            SecretKey tmp = factory.generateSecret(spec);
            SecretKey secret = new SecretKeySpec(tmp.getEncoded(), "AES");

            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secret);
            return Base64.getEncoder().encodeToString(cipher.doFinal(password.getBytes("UTF-8")));
        } catch (Exception e) {
            throw new RuntimeException("Error encrypting password", e);
        }
    }

    private String decrypt(String encryptedPassword) {
        try {
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            PBEKeySpec spec = new PBEKeySpec(masterPassword.toCharArray(), SALT.getBytes(), 65536, 256);
            SecretKey tmp = factory.generateSecret(spec);
            SecretKey secret = new SecretKeySpec(tmp.getEncoded(), "AES");

            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, secret);
            return new String(cipher.doFinal(Base64.getDecoder().decode(encryptedPassword)));
        } catch (Exception e) {
            throw new RuntimeException("Error decrypting password", e);
        }
    }

    public Set<String> getCategories() {
        return passwords.keySet();
    }

    public Set<String> getAccountsInCategory(String category) {
        Map<String, String> categoryPasswords = passwords.get(category);
        return categoryPasswords != null ? categoryPasswords.keySet() : new HashSet<>();
    }

    private void savePasswords() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILENAME))) {
            oos.writeObject(passwords);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    private void loadPasswords() {
        File file = new File(FILENAME);
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                passwords = (Map<String, Map<String, String>>) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public void setMasterPassword(String masterPassword) {
        this.masterPassword = masterPassword;
    }
}