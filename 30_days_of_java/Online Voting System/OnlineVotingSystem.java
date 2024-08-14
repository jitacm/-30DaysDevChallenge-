import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

public class OnlineVotingSystem {
    private VotingSystem votingSystem;

    public OnlineVotingSystem() {
        this.votingSystem = new VotingSystem();
    }

    public void createGUI() {
        JFrame frame = new JFrame("Online Voting System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1));

        JLabel nameLabel = new JLabel("Enter your name:");
        JTextField nameField = new JTextField();

        JLabel voterIdLabel = new JLabel("Enter your Voter ID:");
        JTextField voterIdField = new JTextField();

        JButton registerButton = new JButton("Register");
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String voterId = voterIdField.getText();
                if (votingSystem.registerVoter(name, voterId)) {
                    JOptionPane.showMessageDialog(frame, "Voter registered successfully!");
                } else {
                    JOptionPane.showMessageDialog(frame, "Voter ID already registered!");
                }
            }
        });

        JButton voteButton = new JButton("Vote");
        voteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String voterId = voterIdField.getText();
                Chairperson chairperson = (Chairperson) JOptionPane.showInputDialog(
                        frame,
                        "Choose a chairperson to vote for:",
                        "Cast Your Vote",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        Chairperson.values(),
                        Chairperson.NARENDRA_MODI
                );
                if (votingSystem.castVote(voterId, chairperson)) {
                    JOptionPane.showMessageDialog(frame, "Vote cast successfully!");
                } else {
                    JOptionPane.showMessageDialog(frame, "Invalid Voter ID or vote already cast!");
                }
            }
        });

        JButton resultButton = new JButton("Show Results");
        resultButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Map<Chairperson, Integer> results = votingSystem.getResults();
                StringBuilder resultMessage = new StringBuilder("Election Results:\n");
                for (Chairperson chairperson : Chairperson.values()) {
                    resultMessage.append(chairperson.getName()).append(": ").append(results.getOrDefault(chairperson, 0)).append(" votes\n");
                }
                JOptionPane.showMessageDialog(frame, resultMessage.toString());
            }
        });

        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(voterIdLabel);
        panel.add(voterIdField);
        panel.add(registerButton);
        panel.add(voteButton);
        panel.add(resultButton);

        frame.add(panel);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        OnlineVotingSystem app = new OnlineVotingSystem();
        app.createGUI();
    }
}
