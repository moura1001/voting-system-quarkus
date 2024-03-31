package api.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import domain.Candidate;

import java.util.Optional;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public record CandidateResponseDTO(
        String id,
        Optional<String> photo,
        String givenName,
        String familyName,
        String email,
        Optional<String> phone,
        Optional<String> jobTitle
) {

    public static CandidateResponseDTO toDTO(Candidate candidate) {
        return new CandidateResponseDTO(
                candidate.id(),
                candidate.photo(),
                candidate.givenName(),
                candidate.familyName(),
                candidate.email(),
                candidate.phone(),
                candidate.jobTitle()
        );
    }
}
