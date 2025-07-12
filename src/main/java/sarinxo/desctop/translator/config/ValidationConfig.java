package sarinxo.desctop.translator.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

/**
 * Конфигурация валидации по JSR-303
 */
@Configuration
public class ValidationConfig {

    /**
     * Создает Валидатор, который можно инжектировать в код
     */
    @Bean
    public LocalValidatorFactoryBean validator() {
        return new LocalValidatorFactoryBean();
    }

    /**
     * Позволяет использовать аннотацию @Valid в методах и проверять корректоность сущностей
     */
    @Bean
    public static MethodValidationPostProcessor validationPostProcessor() {
        return new MethodValidationPostProcessor();
    }

}
