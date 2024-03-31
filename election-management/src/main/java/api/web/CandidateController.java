package api.web;

import api.CandidateApi;
import api.dto.request.CandidateCreateDTO;
import api.dto.request.CandidateUpdateDTO;
import api.dto.response.CandidateResponseDTO;
import org.jboss.resteasy.reactive.ResponseStatus;
import org.jboss.resteasy.reactive.RestResponse;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/api/v1/candidates")
public class CandidateController {
    @Inject
    private CandidateApi candidateApi;

    @POST
    @ResponseStatus(RestResponse.StatusCode.CREATED)
    @Transactional
    public void create(CandidateCreateDTO candidateDTO) {
        candidateApi.create(candidateDTO);
    }

    @GET
    public List<CandidateResponseDTO> list() {
        return candidateApi.list();
    }

    @PUT
    @Path("/{id}")
    @ResponseStatus(RestResponse.StatusCode.ACCEPTED)
    @Transactional
    public CandidateResponseDTO update(
            @PathParam("id") String id,
            CandidateUpdateDTO candidateDTO
    ) {
        return candidateApi.update(id, candidateDTO);
    }
}
