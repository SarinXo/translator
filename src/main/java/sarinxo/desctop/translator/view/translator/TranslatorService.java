package sarinxo.desctop.translator.view.translator;

import sarinxo.desctop.translator.dto.TranslateGoogleRequest;

import java.util.concurrent.CompletableFuture;

public interface TranslatorService {

    CompletableFuture<String> translate(TranslateGoogleRequest request);

}
