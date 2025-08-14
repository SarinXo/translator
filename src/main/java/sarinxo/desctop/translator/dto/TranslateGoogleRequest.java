package sarinxo.desctop.translator.dto;

public record TranslateGoogleRequest(
        String textToTranslate,
        LanguageCode from,
        LanguageCode to
) {

}
