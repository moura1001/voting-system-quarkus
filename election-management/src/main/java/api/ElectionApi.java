package api;

import domain.ElectionService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class ElectionApi {
    @Inject
    private ElectionService electionService;

    public void submit() {
        electionService.submit();
    }
}
