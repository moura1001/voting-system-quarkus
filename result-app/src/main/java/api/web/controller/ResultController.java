package api.web.controller;

import api.web.dto.ElectionDTO;
import infra.rest.ElectionManagement;
import io.smallrye.mutiny.Multi;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.resteasy.reactive.RestStreamElementType;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Path("/")
public class ResultController {
    @Inject
    @RestClient
    private ElectionManagement electionManagement;

    @GET
    @RestStreamElementType(MediaType.APPLICATION_JSON)
    public Multi<List<ElectionDTO>> results() {
        return Multi.createFrom()
                .ticks()
                .every(Duration.of(5, ChronoUnit.SECONDS))
                .onItem()
                .transformToMultiAndMerge(m -> electionManagement.getElections().toMulti());
    }
}
