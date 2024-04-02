package api.dto.response;

import domain.Candidate;
import domain.Election;

import java.util.List;
import java.util.stream.Collectors;

public record ElectionResponseDTO(
        String id,
        List<String> candidates
) {
    public static ElectionResponseDTO toDTO(Election election) {
        List<String> candidates = election.candidates().stream()
                .map(Candidate::id).collect(Collectors.toList());
        return new ElectionResponseDTO(election.id(), candidates);
    }
}
