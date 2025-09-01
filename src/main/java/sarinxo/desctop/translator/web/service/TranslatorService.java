package sarinxo.desctop.translator.web.service;

import sarinxo.desctop.translator.web.dto.TranslateGoogleRequest;

import java.util.concurrent.CompletableFuture;

public interface TranslatorService {

    CompletableFuture<String> translate(TranslateGoogleRequest request);

}
