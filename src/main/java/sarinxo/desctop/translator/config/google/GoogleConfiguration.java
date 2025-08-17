package sarinxo.desctop.translator.config.google;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
import sarinxo.desctop.translator.client.GoogleApiClient;
import sarinxo.desctop.translator.client.GoogleTranslator;
import sarinxo.desctop.translator.config.property.GoogleProperties;

import java.time.Duration;

@Configuration
@ConditionalOnProperty("translator.google.enabled")
public class GoogleConfiguration {

    @Bean
    public GoogleApiClient googleApiClient(WebClient googleWebClient, GoogleProperties googleProperties) {
        return new GoogleTranslator(googleWebClient, googleProperties);
    }

    @Bean
    public WebClient googleWebClient(GoogleProperties properties) {
        HttpClient httpClient = HttpClient.create()
                .responseTimeout(Duration.ofSeconds(5))
                .doOnConnected(conn ->
                        conn.addHandlerLast(new io.netty.handler.timeout.ReadTimeoutHandler(10))
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
