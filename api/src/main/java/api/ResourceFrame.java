package api;

import com.kumuluz.ee.cors.annotations.CrossOrigin;
import com.kumuluz.ee.discovery.annotations.RegisterService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

import javax.ws.rs.ApplicationPath;

@OpenAPIDefinition(info = @Info(title = "SkipRope® API", version = "v1", contact = @Contact, license = @License))
@ApplicationPath("api")
@CrossOrigin(supportedMethods = "GET, POST, PUT, DELETE, HEAD, OPTIONS", exposedHeaders = "X-Total-Count, Content-Disposition, X-Export-Filename")
@RegisterService(value = "profile-service", ttl = 20, pingInterval = 15, environment = "dev", version = "1.0.0", singleton = false)
public class ResourceFrame extends javax.ws.rs.core.Application { }