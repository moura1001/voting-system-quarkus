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

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/api/v1/elections")
public class ElectionController {
    @Inject
    private ElectionApi electionApi;

    @POST
    @ResponseStatus(RestResponse.StatusCode.CREATED)
    @Transactional
    public void submit() {
        electionApi.submit();
    }

    @GET
    public List<ElectionResponseDTO> list() {
        return electionApi.getAllElections();
    }
}
