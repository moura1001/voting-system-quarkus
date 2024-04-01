package domain;

import java.util.List;

public interface ElectionStorage {
    Election getElectionById(String id);

    List<Election> getAllEctions();
}
