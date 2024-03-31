package api.web;

import api.CandidateApi;
import api.dto.request.CandidateCreateDTO;
import org.jboss.resteasy.reactive.ResponseStatus;
import org.jboss.resteasy.reactive.RestResponse;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

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
}
