import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

class Movie implements Serializable {
    private static final long serialVersionUID = 1L;
    String title;
    String rating;
    String review;
    String lentTo;

    public Movie(String title, String rating, String review) {
        this.title = title;
        this.rating = rating;
        this.review = review;
        this.lentTo = "";
    }

    @Override
    public String toString() {
        String entry = title + " - Rating: " + rating;
        if (!lentTo.isEmpty()) {
            entry += " (Lent to: " + lentTo + ")";
        }
        return entry;
    }
}

public class MovieCollectionManager extends JFrame {
    private static final long serialVersionUID = 1L;
    private ArrayList<Movie> movies;
    private JList<Movie> movieList;
    private DefaultListModel<Movie> listModel;
    private JTextField titleField, ratingField, reviewField, lentToField;
    private JButton addButton, removeButton, lendButton, returnButton, saveButton;
    private static final String FILENAME = "movies.ser";

    public MovieCollectionManager() {
        movies = new ArrayList<>();
        listModel = new DefaultListModel<>();

        setTitle("Movie Collection Manager");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout());

        // Movie list
        movieList = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(movieList);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Input fields
        JPanel inputPanel = new JPanel(new GridLayout(4, 2));
        inputPanel.add(new JLabel("Title:"));
        titleField = new JTextField();
        inputPanel.add(titleField);
        inputPanel.add(new JLabel("Rating:"));
        ratingField = new JTextField();
        inputPanel.add(ratingField);
        inputPanel.add(new JLabel("Review:"));
        reviewField = new JTextField();
        inputPanel.add(reviewField);
        inputPanel.add(new JLabel("Lent To:"));
        lentToField = new JTextField();
        inputPanel.add(lentToField);
        mainPanel.add(inputPanel, BorderLayout.NORTH);

        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout());
        addButton = new JButton("Add Movie");
        removeButton = new JButton("Remove Movie");
        lendButton = new JButton("Lend Movie");
        returnButton = new JButton("Return Movie");
        saveButton = new JButton("Save Collection");

        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(lendButton);
        buttonPanel.add(returnButton);
        buttonPanel.add(saveButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);

        // Action listeners
        addButton.addActionListener(e -> addMovie());
        removeButton.addActionListener(e -> removeMovie());
        lendButton.addActionListener(e -> lendMovie());
        returnButton.addActionListener(e -> returnMovie());
        saveButton.addActionListener(e -> saveCollection());

        loadCollection();
        updateList();

        // Add window listener to save on close
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                saveCollection();
            }
        });
    }

    private void addMovie() {
        String title = titleField.getText();
        String rating = ratingField.getText();
        String review = reviewField.getText();
        if (!title.isEmpty()) {
            Movie movie = new Movie(title, rating, review);
            movies.add(movie);
            listModel.addElement(movie);
            clearFields();
        }
    }

    private void removeMovie() {
        int index = movieList.getSelectedIndex();
        if (index != -1) {
            movies.remove(index);
            listModel.remove(index);
        }
    }

    private void lendMovie() {
        int index = movieList.getSelectedIndex();
        if (index != -1) {
            String lentTo = lentToField.getText();
            if (!lentTo.isEmpty()) {
                Movie movie = movies.get(index);
                movie.lentTo = lentTo;
                updateList();
                clearFields();
            }
        }
    }

    private void returnMovie() {
        int index = movieList.getSelectedIndex();
        if (index != -1) {
            Movie movie = movies.get(index);
            movie.lentTo = "";
            updateList();
        }
    }

    private void updateList() {
        listModel.clear();
        for (Movie movie : movies) {
            listModel.addElement(movie);
        }
    }

    private void clearFields() {
        titleField.setText("");
        ratingField.setText("");
        reviewField.setText("");
        lentToField.setText("");
    }

    private void saveCollection() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILENAME))) {
            oos.writeObject(movies);
            JOptionPane.showMessageDialog(this, "Collection saved successfully!");
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error saving collection!");
        }
    }

    @SuppressWarnings("unchecked")
    private void loadCollection() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILENAME))) {
            movies = (ArrayList<Movie>) ois.readObject();
            updateList();
        } catch (FileNotFoundException e) {
            // File doesn't exist yet, start with an empty collection
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading collection!");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MovieCollectionManager().setVisible(true));
    }
}