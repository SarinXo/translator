package sarinxo.desctop.translator.web.client;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import sarinxo.desctop.translator.web.config.property.GoogleProperties;
import sarinxo.desctop.translator.web.dto.TranslateGoogleRequest;
import sarinxo.desctop.translator.web.dto.TranslateGoogleResponse;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
public class GoogleTranslator implements GoogleApiClient {

    private final WebClient googleClient;
    private final GoogleProperties googleProperties;

    @Override
    public TranslateGoogleResponse translate(TranslateGoogleRequest request) {
        log.trace("start translate {}", request);
        try {
            ResponseEntity<TranslateGoogleResponse> response = googleClient.post()
                    .uri(googleProperties.api().translate())
                    .body(BodyInserters.fromFormData("q", request.textToTranslate())
                            .with("sl", request.from())
                            .with("tl", request.to()))
                    .retrieve()
                    .toEntity(TranslateGoogleResponse.class)
                    .block();

            return Optional
                    .ofNullable(response)
                    .orElseThrow(() -> new RuntimeException("IDK"))
                    .getBody();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

}
