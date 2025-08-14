package sarinxo.desctop.translator.config.property;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Validated
@ConfigurationProperties(prefix = "translator.google")
@ConditionalOnProperty(prefix = "translator.google", name = "enabled")
public record GoogleProperties(
        @NotBlank(message = "Google API url can't be blank")
        String url,
        @Valid
        GoogleApiProperties api
) {

    record GoogleApiProperties(
            @NotBlank(message = "Google translate endpoint can't be blank")
            String translate
    ) {

    }

}
