package infra.rest;

import api.web.dto.ElectionDTO;
import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.List;

@RegisterRestClient(configKey = "election-management")
public interface ElectionManagement {
    @GET
    @Path("/api/v1/elections")
    Uni<List<ElectionDTO>> getElections();
}
