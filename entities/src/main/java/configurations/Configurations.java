package configurations;

import com.kumuluz.ee.configuration.cdi.ConfigBundle;
import com.kumuluz.ee.configuration.cdi.ConfigValue;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
@ConfigBundle("skiprope-configs")
public class Configurations {

    @ConfigValue(value="show-email", watch = true)
    private Boolean showEmail;

    @ConfigValue("health.etcd-url")
    private String etcdHealthUrl;

    @ConfigValue(value = "health.unhealthy", watch = true)
    private boolean unhealthy;

    public Boolean getShowEmail() {
        return showEmail;
    }

    public void setShowEmail(Boolean showEmail) {
        this.showEmail = showEmail;
    }

    public String getEtcdHealthUrl() {
        return etcdHealthUrl;
    }

    public void setEtcdHealthUrl(String etcdHealthUrl) {
        this.etcdHealthUrl = etcdHealthUrl;
    }

    public boolean isUnhealthy() {
        return unhealthy;
    }

    public void setUnhealthy(boolean unhealthy) {
        this.unhealthy = unhealthy;
    }
}
