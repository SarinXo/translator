package sarinxo.desctop.translator.config;

import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Configuration;
import sarinxo.desctop.translator.web.config.property.GoogleProperties;

@Configuration
@ConfigurationPropertiesScan(basePackageClasses = {
        GoogleProperties.class
})
public class PropertiesConfig {

}
