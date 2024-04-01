package infra.entity;

import domain.Candidate;
import domain.Election;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity(name = "election_candidate")
public class ElectionCandidateEntity {
    @EmbeddedId private ElectionCandidateId id;

    private Integer votes;

    public ElectionCandidateId getId() {
        return id;
    }

    public void setId(ElectionCandidateId id) {
        this.id = id;
    }

    public Integer getVotes() {
        return votes;
    }

    public void setVotes(Integer votes) {
        this.votes = votes;
    }

    public static ElectionCandidateEntity toEntity(Election election, Candidate candidate, Integer votes) {
        ElectionCandidateEntity entity = new ElectionCandidateEntity();

        ElectionCandidateId id = new ElectionCandidateId();
        id.setElectionId(election.id());
        id.setCandidateId(candidate.id());

        entity.setId(id);
        entity.setVotes(votes);

        return entity;
    }
}
