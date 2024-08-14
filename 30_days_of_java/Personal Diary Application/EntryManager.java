import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class EntryManager implements Serializable {
    private List<Entry> entries;

    public EntryManager() {
        this.entries = new ArrayList<>();
    }

    public void addEntry(Entry entry) {
        entries.add(entry);
    }

    public List<Entry> getEntries() {
        return entries;
    }

    public List<Entry> searchByTag(String tag) {
        List<Entry> result = new ArrayList<>();
        for (Entry entry : entries) {
            if (entry.getTags().contains(tag)) {
                result.add(entry);
            }
        }
        return result;
    }

    public List<Entry> searchByMood(String mood) {
        List<Entry> result = new ArrayList<>();
        for (Entry entry : entries) {
            if (entry.getMood().equalsIgnoreCase(mood)) {
                result.add(entry);
            }
        }
        return result;
    }
}
