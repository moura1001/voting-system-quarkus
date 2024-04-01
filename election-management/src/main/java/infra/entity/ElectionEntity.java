package infra.entity;

import domain.Election;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "election")
public class ElectionEntity {
    @Id private String id;

    public ElectionEntity() {}

    public ElectionEntity(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public static ElectionEntity toEntity(Election election) {
        ElectionEntity electionEntity = new ElectionEntity(
                election.id()
        );

        return electionEntity;
    }
}
