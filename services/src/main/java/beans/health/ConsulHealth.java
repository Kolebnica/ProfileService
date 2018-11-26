package beans.health;

import configurations.Configurations;
import org.eclipse.microprofile.health.Health;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Logger;

@Health
@ApplicationScoped
public class ConsulHealth implements HealthCheck {

    @Inject
    private Configurations configurations;

    private static final Logger LOG = Logger.getLogger(ConsulHealth.class.getSimpleName());

    @Override
    public HealthCheckResponse call() {
        try {

            HttpURLConnection connection = (HttpURLConnection) new URL(configurations.getConsulHealthUrl()).openConnection();
            connection.setRequestMethod("GET");

            if (connection.getResponseCode() == 200) {
                return HealthCheckResponse.named(ConsulHealth.class.getSimpleName()).up().build();
            }
        } catch (Exception exception) {
            LOG.severe(exception.getMessage());
        }
        return HealthCheckResponse.named(ConsulHealth.class.getSimpleName()).down().build();
    }
}