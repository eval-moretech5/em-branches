package org.eval.moretech.twogis.branches.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI openApiConfiguration() {
        return new OpenAPI()
            .addServersItem(new Server().url("https://viewplace.ru/"))
            .addServersItem(new Server().url("https://localhost"))
            .info(new Info()
                .title("Сервис Отделений и банкоматов")
                .version("1.0")
            );
    }
}
