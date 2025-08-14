package sarinxo.desctop.translator.dto;

import lombok.Getter;

/**
 * Язвки доступные для перевода. Определение языка должно быть еще и AUTO("Автоматически", "auto")
 */
@Getter
public enum LanguageCode {
    RU("Русский", "ru"),
    EN("Английский", "en");

    private final String code;
    private final String text;

    LanguageCode(String text, String code) {
        this.code = code;
        this.text = text;
    }
}
