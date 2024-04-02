package api;

import api.dto.response.ElectionResponseDTO;
import domain.ElectionService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class ElectionApi {
    @Inject
    private ElectionService electionService;

    public void submit() {
        electionService.submit();
    }

    public List<ElectionResponseDTO> getAllElections() {
        return electionService.getAllElections().stream()
                .map(ElectionResponseDTO::toDTO).collect(Collectors.toList());
    }
}
