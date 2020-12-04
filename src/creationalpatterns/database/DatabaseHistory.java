package creationalpatterns.database;

import java.util.HashMap;
import java.util.Map;

public class DatabaseHistory {

    Map<Integer, DatabaseState> history;
    private static int lastVersion;

    public DatabaseHistory() {
        this.history = new HashMap<>();
    }

    public void setState(DatabaseState db) {
        history.put(lastVersion++, db);
    }

    public DatabaseState getState(int version) {
        return history.get(version);
    }
}
