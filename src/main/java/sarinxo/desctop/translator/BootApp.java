package sarinxo.desctop.translator;

import javafx.application.Application;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import sarinxo.desctop.translator.event.ApplicationReadyEvent;

@Slf4j
@SpringBootApplication
public class BootApp extends Application {

    private static ConfigurableApplicationContext context;

    public static void main(String[] args) {
        context = new SpringApplicationBuilder(BootApp.class)
                .web(WebApplicationType.NONE)
                .run(args);

        log.info("Start to launch interface");
        launch(args);
        log.info("Interface was successfully load");
    }

    /**
     * Запуск UI
     * @param stage
     * @throws Exception
     */
    @Override
    public void start(Stage stage) {
        ApplicationReadyEvent event = new ApplicationReadyEvent(this, stage);

        context.publishEvent(event);
    }

    @Override
    public void stop() {
        context.stop();
    }

}
