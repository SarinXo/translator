package sarinxo.desctop.translator.client;

import sarinxo.desctop.translator.dto.TranslateYandexRequest;
import sarinxo.desctop.translator.dto.TranslateGoogleResponse;

public interface GoogleApiClient {

    TranslateGoogleResponse translate(TranslateGoogleRequest request);
}
