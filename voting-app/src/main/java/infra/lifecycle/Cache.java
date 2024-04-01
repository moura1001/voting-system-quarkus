package infra.lifecycle;

import infra.repository.ElectionRedisRepository;
import io.quarkus.runtime.Startup;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;

@Startup
@ApplicationScoped
public class Cache {
    private static final Logger LOGGER = Logger.getLogger(Cache.class);

    public Cache(ElectionRedisRepository repository) {
        LOGGER.info("Startup: Cache");
        repository.getAllEctions();
    }
}
