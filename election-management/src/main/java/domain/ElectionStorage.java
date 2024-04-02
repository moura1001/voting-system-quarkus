package domain;

import java.util.List;

public interface ElectionStorage {
    void submit(Election election);

    List<Election> getAllElections();
}
