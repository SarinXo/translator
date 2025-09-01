package sarinxo.desctop.translator.web.config.property;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Setter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Setter
@Validated
@ConfigurationProperties("translator.google")
@ConditionalOnProperty("translator.google.enabled")
public class GoogleProperties {

    @NotBlank(message = "Google API url can't be blank")
    String url;
    @Valid
    GoogleApiProperties api;

    public String url() {
        return url;
    }

    public GoogleApiProperties api() {
        return api;
    }

    public record GoogleApiProperties(
            @NotBlank(message = "Google translate endpoint can't be blank")
            String translate
    ) {

    }

}
