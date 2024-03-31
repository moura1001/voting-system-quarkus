package api.dto.request;

import domain.Candidate;

import java.util.Optional;

public record CandidateUpdateDTO(
        Optional<String> photo,
        String givenName,
        String familyName,
        String email,
        Optional<String> phone,
        Optional<String> jobTitle
) {

    public Candidate toDomain(String id) {
        return new Candidate(
                id,
                this.photo,
                this.givenName,
                this.familyName,
                this.email,
                this.phone,
                this.jobTitle
        );
    }
}
