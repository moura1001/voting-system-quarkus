package api.web;

import api.ElectionApi;
import api.dto.response.ElectionResponseDTO;

import javax.inject.Inject;
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
}
