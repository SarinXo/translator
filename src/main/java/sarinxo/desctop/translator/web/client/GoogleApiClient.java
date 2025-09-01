package sarinxo.desctop.translator.web.client;

import sarinxo.desctop.translator.web.dto.TranslateGoogleRequest;
import sarinxo.desctop.translator.web.dto.TranslateGoogleResponse;

public interface GoogleApiClient {

    TranslateGoogleResponse translate(TranslateGoogleRequest request);

}
