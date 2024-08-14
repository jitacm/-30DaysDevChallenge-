import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class LibraryGUI extends JFrame {
    private static final String SAVE_FILE = "library.dat";
    private Library library;
    private JList<Book> bookList;
    private DefaultListModel<Book> listModel;
    private JTextField titleField, authorField, genreField, searchField;
    private JButton addButton, removeButton, borrowButton, returnButton, searchButton;
    private JComboBox<String> searchTypeComboBox;
    private JSpinner ratingSpinner;
    private JTextArea reviewArea;

    public LibraryGUI() {
        loadLibrary();
        setupUI();
    }

    private void loadLibrary() {
        File file = new File(SAVE_FILE);
        if (file.exists()) {
            try {
                library = Library.loadFromFile(SAVE_FILE);
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
                library = new Library();
            }
        } else {
            library = new Library();
        }
    }

    private void saveLibrary() {
        try {
            library.saveToFile(SAVE_FILE);
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error saving library: " + e.getMessage(), "Save Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void setupUI() {
        setTitle("Personal Library Catalog");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        listModel = new DefaultListModel<>();
        bookList = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(bookList);
        add(scrollPane, BorderLayout.CENTER);

        JPanel inputPanel = new JPanel(new GridLayout(5, 2));
        titleField = new JTextField();
        authorField = new JTextField();
        genreField = new JTextField();
        addButton = new JButton("Add Book");
        removeButton = new JButton("Remove Book");
        borrowButton = new JButton("Borrow Book");
        returnButton = new JButton("Return Book");
        ratingSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 5, 1));
        reviewArea = new JTextArea(3, 20);

        inputPanel.add(new JLabel("Title:"));
        inputPanel.add(titleField);
        inputPanel.add(new JLabel("Author:"));
        inputPanel.add(authorField);
        inputPanel.add(new JLabel("Genre:"));
        inputPanel.add(genreField);
        inputPanel.add(new JLabel("Rating:"));
        inputPanel.add(ratingSpinner);
        inputPanel.add(new JLabel("Review:"));
        inputPanel.add(new JScrollPane(reviewArea));

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(borrowButton);
        buttonPanel.add(returnButton);

        JPanel southPanel = new JPanel(new BorderLayout());
        southPanel.add(inputPanel, BorderLayout.CENTER);
        southPanel.add(buttonPanel, BorderLayout.SOUTH);
        add(southPanel, BorderLayout.SOUTH);

        JPanel searchPanel = new JPanel();
        searchField = new JTextField(20);
        searchButton = new JButton("Search");
        searchTypeComboBox = new JComboBox<>(new String[]{"Title", "Author", "Genre"});
        searchPanel.add(new JLabel("Search:"));
        searchPanel.add(searchField);
        searchPanel.add(searchTypeComboBox);
        searchPanel.add(searchButton);
        add(searchPanel, BorderLayout.NORTH);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addBook();
            }
        });

        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeBook();
            }
        });

        borrowButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                borrowBook();
            }
        });

        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                returnBook();
            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchBooks();
            }
        });

        bookList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            @Override
            public void valueChanged(javax.swing.event.ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    updateBookDetails();
                }
            }
        });

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                saveLibrary();
            }
        });

        updateBookList();
    }

    private void addBook() {
        String title = titleField.getText();
        String author = authorField.getText();
        String genre = genreField.getText();
        if (!title.isEmpty() && !author.isEmpty() && !genre.isEmpty()) {
            Book book = new Book(title, author, genre);
            book.setRating((Integer) ratingSpinner.getValue());
            book.setReview(reviewArea.getText());
            library.addBook(book);
            updateBookList();
            clearInputFields();
            saveLibrary();
        }
    }

    private void removeBook() {
        Book selectedBook = bookList.getSelectedValue();
        if (selectedBook != null) {
            library.removeBook(selectedBook);
            updateBookList();
            saveLibrary();
        }
    }

    private void borrowBook() {
        Book selectedBook = bookList.getSelectedValue();
        if (selectedBook != null && !selectedBook.isBorrowed()) {
            selectedBook.setBorrowed(true);
            updateBookList();
            saveLibrary();
        }
    }

    private void returnBook() {
        Book selectedBook = bookList.getSelectedValue();
        if (selectedBook != null && selectedBook.isBorrowed()) {
            selectedBook.setBorrowed(false);
            updateBookList();
            saveLibrary();
        }
    }

    private void searchBooks() {
        String searchTerm = searchField.getText();
        String searchType = (String) searchTypeComboBox.getSelectedItem();
        List<Book> results;

        if (searchType.equals("Title")) {
            results = library.searchByTitle(searchTerm);
        } else if (searchType.equals("Author")) {
            results = library.searchByAuthor(searchTerm);
        } else {
            results = library.searchByGenre(searchTerm);
        }

        updateBookList(results);
    }

    private void updateBookList() {
        updateBookList(library.getAllBooks());
    }

    private void updateBookList(List<Book> books) {
        listModel.clear();
        for (Book book : books) {
            listModel.addElement(book);
        }
    }

    private void updateBookDetails() {
        Book selectedBook = bookList.getSelectedValue();
        if (selectedBook != null) {
            titleField.setText(selectedBook.getTitle());
            authorField.setText(selectedBook.getAuthor());
            genreField.setText(selectedBook.getGenre());
            ratingSpinner.setValue(selectedBook.getRating());
            reviewArea.setText(selectedBook.getReview());
        }
    }

    private void clearInputFields() {
        titleField.setText("");
        authorField.setText("");
        genreField.setText("");
        ratingSpinner.setValue(0);
        reviewArea.setText("");
    }
}