import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class VotingSystem {
    private Map<String, Voter> voters;
    private Map<Chairperson, Integer> votes;

    public VotingSystem() {
        this.voters = loadVoters();
        this.votes = loadVotes();
    }

    public boolean registerVoter(String name, String voterId) {
        if (voters.containsKey(voterId)) {
            return false; // Voter already registered
        }
        Voter voter = new Voter(name, voterId);
        voters.put(voterId, voter);
        saveVoters();
        return true;
    }

    public boolean castVote(String voterId, Chairperson chairperson) {
        Voter voter = voters.get(voterId);
        if (voter == null || voter.hasVoted()) {
            return false; // Invalid voter or already voted
        }
        voter.setHasVoted(true);
        votes.put(chairperson, votes.getOrDefault(chairperson, 0) + 1);
        saveVoters();
        saveVotes();
        return true;
    }

    public Map<Chairperson, Integer> getResults() {
        return votes;
    }

    private void saveVoters() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("data/voters.dat"))) {
            oos.writeObject(voters);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveVotes() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("data/votes.dat"))) {
            oos.writeObject(votes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    private Map<String, Voter> loadVoters() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("data/voters.dat"))) {
            return (Map<String, Voter>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return new HashMap<>();
        }
    }

    @SuppressWarnings("unchecked")
    private Map<Chairperson, Integer> loadVotes() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("data/votes.dat"))) {
            return (Map<Chairperson, Integer>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return new HashMap<>();
        }
    }
}
