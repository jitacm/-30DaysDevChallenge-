import java.io.Serializable;

public class Voter implements Serializable {
    private String name;
    private String voterId;
    private boolean hasVoted;

    public Voter(String name, String voterId) {
        this.name = name;
        this.voterId = voterId;
        this.hasVoted = false;
    }

    public String getName() {
        return name;
    }

    public String getVoterId() {
        return voterId;
    }

    public boolean hasVoted() {
        return hasVoted;
    }

    public void setHasVoted(boolean hasVoted) {
        this.hasVoted = hasVoted;
    }
}
