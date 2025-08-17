package sarinxo.desctop.translator.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record TranslateGoogleResponse(
        List<Sentence> sentences,
        /**
         * Код языка, который переводился
         */
        String src
) {

    /**
     * Предложение из текста, которое было переведено.
     */
    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    public record Sentence(
            /**
             * Переведенное предложение
             */
            String trans,
            /**
             * Оригинальное предложение
             */
            String orig
    ) {

    }

}
