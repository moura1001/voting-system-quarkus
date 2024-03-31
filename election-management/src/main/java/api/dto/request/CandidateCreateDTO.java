package api.dto.request;

import domain.Candidate;

import java.util.Optional;

public record CandidateCreateDTO(
        Optional<String> photo,
        String givenName,
        String familyName,
        String email,
        Optional<String> phone,
        Optional<String> jobTitle
) {
    public Candidate toDomain() {
        return Candidate.create(
                this.photo,
                this.givenName,
                this.familyName,
                this.email,
                this.phone,
                this.jobTitle
        );
    }
}
