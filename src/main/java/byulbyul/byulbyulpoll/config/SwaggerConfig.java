package byulbyul.byulbyulpoll.config;


import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.List;

@Configuration
public class SwaggerConfig {
    @Bean
    @Profile("real")
    public OpenAPI openAPI() {

        Server server1 = new Server();
        server1.setUrl("https://api.byulbyul.store/");

        Server server2 = new Server();
        server2.setUrl("http://localhost:8080/");

        return new OpenAPI()
                .components(new Components())
                .info(apiInfo())
                .servers(List.of(server1, server2));
    }


    private Info apiInfo() {
        return new Info()
                .title("ByulByulPoll API Test")
                .description("ByulByulPoll API Test")
                .version("1.0.0");
    }
}
