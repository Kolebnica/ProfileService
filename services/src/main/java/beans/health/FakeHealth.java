package beans.health;

import configurations.Configurations;
import org.eclipse.microprofile.health.Health;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@Health
@ApplicationScoped
public class FakeHealth implements HealthCheck {

    @Inject
    private Configurations configurations;

    @Override
    public HealthCheckResponse call() {

        if (configurations.isUnhealthy()) {
            return HealthCheckResponse.named(FakeHealth.class.getSimpleName()).down().build();
        } else {
            return HealthCheckResponse.named(FakeHealth.class.getSimpleName()).up().build();
        }

    }
}