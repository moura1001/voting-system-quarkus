package api.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import domain.Candidate;
import domain.Election;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public record ElectionResponseDTO(
        String id,
        List<InnerCandidateResponseDTO> candidates
) {
    public static ElectionResponseDTO toDTO(Election election) {
        var candidates = election.votes().entrySet().stream()
                .map(entry -> InnerCandidateResponseDTO.toDTO(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());

        return new ElectionResponseDTO(election.id(), candidates);
    }

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    record InnerCandidateResponseDTO(
            String id,
            Optional<String> photo,
            String givenName,
            String familyName,
            String email,
            Optional<String> phone,
            Optional<String> jobTitle,
            Integer votes
    ) {

        static InnerCandidateResponseDTO toDTO(Candidate candidate, Integer votes) {
            return new InnerCandidateResponseDTO(
                    candidate.id(),
                    candidate.photo(),
                    candidate.givenName(),
                    candidate.familyName(),
                    candidate.email(),
                    candidate.phone(),
                    candidate.jobTitle(),
                    votes
            );
        }
    }
}
