package configurations;

import com.kumuluz.ee.configuration.cdi.ConfigBundle;
import com.kumuluz.ee.configuration.cdi.ConfigValue;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
@ConfigBundle("skiprope-configs")
public class Configurations {

    @ConfigValue(watch = true)
    private Boolean showEmail;

    @ConfigValue("health.consul-url")
    private String consulHealthUrl;

    public Boolean getShowEmail() {
        return showEmail;
    }

    public void setShowEmail(Boolean showEmail) {
        this.showEmail = showEmail;
    }

    public String getConsulHealthUrl() {
        return consulHealthUrl;
    }

    public void setConsulHealthUrl(String consulHealthUrl) {
        this.consulHealthUrl = consulHealthUrl;
    }

}
