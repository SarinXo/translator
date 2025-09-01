package sarinxo.desctop.translator.web.dto;

import lombok.Getter;

/**
 * Языки доступные для перевода
 */
@Getter
public enum LanguageCode {
    AUTO("Автоматически", "auto"),
    RU("Русский", "ru"),
    EN("Английский", "en"),
    DE("Немецкий", "de");

    private final String code;
    private final String text;

    LanguageCode(String text, String code) {
        this.code = code;
        this.text = text;
    }


    @Override
    public String toString() {
        return text;
    }
}
