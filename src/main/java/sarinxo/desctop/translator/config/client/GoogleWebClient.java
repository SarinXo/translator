package sarinxo.desctop.translator.config.client;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
import sarinxo.desctop.translator.config.property.GoogleProperties;

import java.time.Duration;


@Configuration
@RequiredArgsConstructor
@ConditionalOnProperty(prefix = "translator.google", name = "enabled")
public class GoogleWebClient {

    private final GoogleProperties properties;

    @Bean
    public WebClient googleClient() {
        HttpClient httpClient = HttpClient.create()
                .responseTimeout(Duration.ofSeconds(5))
                .doOnConnected(conn ->
                        conn.addHandlerLast(new io.netty.handler.timeout.ReadTimeoutHandler(50))
                                .addHandlerLast(new io.netty.handler.timeout.WriteTimeoutHandler(5))
                );

        return WebClient.builder()
                .baseUrl(properties.url())
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .defaultHeader("Accept", "application/json")
                .defaultHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
                .defaultHeader("User-Agent", "Mozilla/5.0")
                .build();
    }

}
