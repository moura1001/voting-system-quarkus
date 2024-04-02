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

    public List<ElectionResponseDTO> getAllEctions() {
        return electionService.getAllEctions().stream()
                .map(ElectionResponseDTO::toDTO).collect(Collectors.toList());
    }
}
