package sarinxo.desctop.translator.client;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import sarinxo.desctop.translator.dto.TranslateYandexRequest;
import sarinxo.desctop.translator.dto.TranslateGoogleResponse;

import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
@ConditionalOnProperty(prefix = "translator.api.yandex", name = "enabled")
public class GoogleTranslator implements GoogleApiClient {

    private final WebClient yandexClient;

    @Override
    public TranslateGoogleResponse translate(TranslateYandexRequest request) {
        log.trace("start translate {}", request);
        try {
            ResponseEntity<TranslateGoogleResponse> block = yandexClient.post()
                    .uri("/translate/v2/translate")
                    .body(Mono.just(request), TranslateYandexRequest.class)
                    .retrieve()
                    .toEntity(TranslateGoogleResponse.class)
                    .block();

            return Optional
                    .ofNullable(block)
                    .orElseThrow(() -> new RuntimeException("IDK"))
                    .getBody();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

}
