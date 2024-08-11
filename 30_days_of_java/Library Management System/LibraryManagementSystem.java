import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class Book {
    private String isbn;
    private String title;
    private String author;
    private boolean isAvailable;
    private Member borrower;

    public Book(String isbn, String title, String author) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.isAvailable = true;
    }

    // Getters and setters
    public String getIsbn() { return isbn; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public boolean isAvailable() { return isAvailable; }
    public void setAvailable(boolean available) { isAvailable = available; }
    public Member getBorrower() { return borrower; }
    public void setBorrower(Member borrower) { this.borrower = borrower; }

    @Override
    public String toString() {
        return title + " by " + author + " (ISBN: " + isbn + ")";
    }
}

class Member {
    private int memberId;
    private String name;
    private List<Book> borrowedBooks;

    public Member(int memberId, String name) {
        this.memberId = memberId;
        this.name = name;
        this.borrowedBooks = new ArrayList<>();
    }

    // Getters and setters
    public int getMemberId() { return memberId; }
    public String getName() { return name; }
    public List<Book> getBorrowedBooks() { return borrowedBooks; }

    public void borrowBook(Book book) {
        borrowedBooks.add(book);
    }

    public void returnBook(Book book) {
        borrowedBooks.remove(book);
    }

    @Override
    public String toString() {
        return name + " (ID: " + memberId + ")";
    }
}

class Library {
    private List<Book> books;
    private List<Member> members;

    public Library() {
        this.books = new ArrayList<>();
        this.members = new ArrayList<>();
    }

    public String addBook(Book newBook) {
        for (Book book : books) {
            if (book.getIsbn().equals(newBook.getIsbn()) &&
                book.getTitle().equals(newBook.getTitle()) &&
                book.getAuthor().equals(newBook.getAuthor())) {
                return "A book with the same ISBN, title, and author already exists.";
            }
        }
        books.add(newBook);
        return "Book added successfully.";
    }

    public String addMember(Member newMember) {
        for (Member member : members) {
            if (member.getMemberId() == newMember.getMemberId()) {
                return "A member with this ID already exists.";
            }
        }
        members.add(newMember);
        return "Member added successfully.";
    }

    public Book findBook(String isbn) {
        return books.stream()
                .filter(book -> book.getIsbn().equals(isbn))
                .findFirst()
                .orElse(null);
    }

    public Member findMember(int memberId) {
        return members.stream()
                .filter(member -> member.getMemberId() == memberId)
                .findFirst()
                .orElse(null);
    }

    public String lendBook(String isbn, int memberId) {
        Book book = findBook(isbn);
        Member member = findMember(memberId);

        if (book != null && member != null && book.isAvailable()) {
            book.setAvailable(false);
            book.setBorrower(member);
            member.borrowBook(book);
            return "Book lent successfully.";
        } else {
            return "Unable to lend book.";
        }
    }

    public String returnBook(String isbn, int memberId) {
        Book book = findBook(isbn);
        Member member = findMember(memberId);

        if (book != null && member != null && member.getBorrowedBooks().contains(book)) {
            book.setAvailable(true);
            book.setBorrower(null);
            member.returnBook(book);
            return "Book returned successfully.";
        } else {
            return "Unable to return book.";
        }
    }

    public List<Book> getAvailableBooks() {
        return books.stream()
                .filter(Book::isAvailable)
                .collect(Collectors.toList());
    }

    public List<Book> getIssuedBooks() {
        return books.stream()
                .filter(book -> !book.isAvailable())
                .collect(Collectors.toList());
    }

    public List<Member> getMembers() {
        return new ArrayList<>(members);
    }
}

class LibraryGUI extends JFrame {
    private Library library;
    private JTextField isbnField, titleField, authorField, memberIdField, memberNameField;
    private JTextArea outputArea;

    public LibraryGUI() {
        library = new Library();
        setTitle("Library Management System");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Input panel
        JPanel inputPanel = new JPanel(new GridLayout(6, 2));
        inputPanel.add(new JLabel("ISBN:"));
        isbnField = new JTextField();
        inputPanel.add(isbnField);
        inputPanel.add(new JLabel("Title:"));
        titleField = new JTextField();
        inputPanel.add(titleField);
        inputPanel.add(new JLabel("Author:"));
        authorField = new JTextField();
        inputPanel.add(authorField);
        inputPanel.add(new JLabel("Member ID:"));
        memberIdField = new JTextField();
        inputPanel.add(memberIdField);
        inputPanel.add(new JLabel("Member Name:"));
        memberNameField = new JTextField();
        inputPanel.add(memberNameField);

        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton addBookButton = new JButton("Add Book");
        JButton addMemberButton = new JButton("Add Member");
        JButton lendBookButton = new JButton("Lend Book");
        JButton returnBookButton = new JButton("Return Book");
        JButton availableBooksButton = new JButton("Available Books");
        JButton issuedBooksButton = new JButton("Issued Books");
        JButton memberListButton = new JButton("Member List");
        buttonPanel.add(addBookButton);
        buttonPanel.add(addMemberButton);
        buttonPanel.add(lendBookButton);
        buttonPanel.add(returnBookButton);
        buttonPanel.add(availableBooksButton);
        buttonPanel.add(issuedBooksButton);
        buttonPanel.add(memberListButton);

        // Output area
        outputArea = new JTextArea();
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);

        // Add components to frame
        add(inputPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        add(scrollPane, BorderLayout.SOUTH);

        // Add action listeners
        addBookButton.addActionListener(e -> addBook());
        addMemberButton.addActionListener(e -> addMember());
        lendBookButton.addActionListener(e -> lendBook());
        returnBookButton.addActionListener(e -> returnBook());
        availableBooksButton.addActionListener(e -> showAvailableBooks());
        issuedBooksButton.addActionListener(e -> showIssuedBooks());
        memberListButton.addActionListener(e -> showMemberList());
    }

    private void addBook() {
        String isbn = isbnField.getText();
        String title = titleField.getText();
        String author = authorField.getText();
        if (!isbn.isEmpty() && !title.isEmpty() && !author.isEmpty()) {
            String result = library.addBook(new Book(isbn, title, author));
            outputArea.append(result + "\n");
        } else {
            outputArea.append("Please fill all book details\n");
        }
    }

    private void addMember() {
        String idStr = memberIdField.getText();
        String name = memberNameField.getText();
        if (!idStr.isEmpty() && !name.isEmpty()) {
            try {
                int id = Integer.parseInt(idStr);
                String result = library.addMember(new Member(id, name));
                outputArea.append(result + "\n");
            } catch (NumberFormatException e) {
                outputArea.append("Invalid member ID\n");
            }
        } else {
            outputArea.append("Please fill all member details\n");
        }
    }

    private void lendBook() {
        String isbn = isbnField.getText();
        String idStr = memberIdField.getText();
        if (!isbn.isEmpty() && !idStr.isEmpty()) {
            try {
                int id = Integer.parseInt(idStr);
                String result = library.lendBook(isbn, id);
                outputArea.append(result + "\n");
            } catch (NumberFormatException e) {
                outputArea.append("Invalid member ID\n");
            }
        } else {
            outputArea.append("Please enter ISBN and member ID\n");
        }
    }

    private void returnBook() {
        String isbn = isbnField.getText();
        String idStr = memberIdField.getText();
        if (!isbn.isEmpty() && !idStr.isEmpty()) {
            try {
                int id = Integer.parseInt(idStr);
                String result = library.returnBook(isbn, id);
                outputArea.append(result + "\n");
            } catch (NumberFormatException e) {
                outputArea.append("Invalid member ID\n");
            }
        } else {
            outputArea.append("Please enter ISBN and member ID\n");
        }
    }

    private void showAvailableBooks() {
        List<Book> availableBooks = library.getAvailableBooks();
        outputArea.append("Available Books:\n");
        for (Book book : availableBooks) {
            outputArea.append(book.toString() + "\n");
        }
    }

    private void showIssuedBooks() {
        List<Book> issuedBooks = library.getIssuedBooks();
        outputArea.append("Issued Books:\n");
        for (Book book : issuedBooks) {
            outputArea.append(book.toString() + " - Borrowed by: " + book.getBorrower().getName() + "\n");
        }
    }

    private void showMemberList() {
        List<Member> members = library.getMembers();
        outputArea.append("Member List:\n");
        for (Member member : members) {
            outputArea.append(member.toString() + "\n");
        }
    }
}

public class LibraryManagementSystem {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LibraryGUI gui = new LibraryGUI();
            gui.setVisible(true);
        });
    }
}
