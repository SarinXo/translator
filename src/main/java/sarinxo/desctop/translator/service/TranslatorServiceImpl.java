package sarinxo.desctop.translator.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import sarinxo.desctop.translator.client.GoogleApiClient;
import sarinxo.desctop.translator.dto.TranslateGoogleRequest;
import sarinxo.desctop.translator.dto.TranslateGoogleResponse;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
@ConditionalOnProperty("translator.google.enabled")
public class TranslatorServiceImpl implements TranslatorService{

    private final ExecutorService asyncAppTaskExecutor;
    private final GoogleApiClient googleApiClient;


    @Override
    public CompletableFuture<String> translate(TranslateGoogleRequest request) {
        return CompletableFuture.supplyAsync(
                () -> {
                    try {
                        TranslateGoogleResponse response = googleApiClient.translate(request);

                        return response.sentences().stream()
                                .map(TranslateGoogleResponse.Sentence::trans)
                                .collect(Collectors.joining());

                    } catch (Exception e) {
                        log.warn(e.getMessage());
                        return "Возникла ошибка! " +  e.getMessage();
                    }
                },
                asyncAppTaskExecutor
        );
    }

}
