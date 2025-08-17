package sarinxo.desctop.translator.dto;

public record TranslateGoogleRequest(
        String textToTranslate,
        String from,
        String to
) {

}
