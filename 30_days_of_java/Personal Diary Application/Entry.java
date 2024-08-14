import java.io.Serializable;
import java.util.List;

public class Entry implements Serializable {
    private String date;
    private String content;
    private List<String> tags;
    private String mood;

    public Entry(String date, String content, List<String> tags, String mood) {
        this.date = date;
        this.content = content;
        this.tags = tags;
        this.mood = mood;
    }

    public String getDate() {
        return date;
    }

    public String getContent() {
        return content;
    }

    public List<String> getTags() {
        return tags;
    }

    public String getMood() {
        return mood;
    }

    @Override
    public String toString() {
        return "Date: " + date + ", Mood: " + mood + ", Tags: " + tags + "\n" + content;
    }
}
