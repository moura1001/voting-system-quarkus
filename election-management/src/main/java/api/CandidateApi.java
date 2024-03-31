package api;

import api.dto.request.CandidateCreateDTO;
import api.dto.request.CandidateUpdateDTO;
import api.dto.response.CandidateResponseDTO;
import domain.CandidateService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class CandidateApi {
    @Inject
    private CandidateService candidateService;

    public void create(CandidateCreateDTO candidateDTO) {
        candidateService.save(candidateDTO.toDomain());
    }

    public CandidateResponseDTO update(String id, CandidateUpdateDTO candidateDTO) {
        candidateService.save(candidateDTO.toDomain(id));
        return CandidateResponseDTO.toDTO(candidateService.getCandidateById(id));
    }
}
