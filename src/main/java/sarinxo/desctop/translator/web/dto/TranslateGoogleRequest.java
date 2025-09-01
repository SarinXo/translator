package sarinxo.desctop.translator.web.dto;

public record TranslateGoogleRequest(
        String textToTranslate,
        String from,
        String to
) {

}
