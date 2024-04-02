package api.web.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;
import java.util.Optional;

public record ElectionDTO(
        String id,
        List<InnerCandidateDTO> candidates
) {
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    record InnerCandidateDTO(
            String id,
            Optional<String> photo,
            String givenName,
            String familyName,
            String email,
            Optional<String> phone,
            Optional<String> jobTitle,
            Integer votes
    ) {}
}
