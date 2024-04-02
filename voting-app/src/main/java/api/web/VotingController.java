package api.web;

import api.ElectionApi;
import api.dto.response.ElectionResponseDTO;
import org.jboss.resteasy.reactive.ResponseStatus;
import org.jboss.resteasy.reactive.RestResponse;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Produces(MediaType.APPLICATION_JSON)
@Path("/api/v1/voting")
public class VotingController {
    @Inject
    private ElectionApi electionApi;

    @GET
    public List<ElectionResponseDTO> elections() {
        return electionApi.getAllEctions();
    }

    @POST
    @Path("/elections/{electionId}/candidates/{candidateId}")
    @ResponseStatus(RestResponse.StatusCode.ACCEPTED)
    @Transactional
    public void vote(
            @PathParam("electionId") String electionId,
            @PathParam("candidateId") String candidateId
    ) {
        electionApi.vote(electionId, candidateId);
    }
}
