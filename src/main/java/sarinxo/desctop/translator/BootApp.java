package sarinxo.desctop.translator;

import javafx.application.Application;

import javafx.stage.Stage;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class BootApp extends Application {

    public static void main(String[] args) {
        new SpringApplicationBuilder(BootApp.class)
                .web(WebApplicationType.NONE)
                .run(args);

        launch(args);
    }

    /**
     * Запуск UI
     * @param stage
     * @throws Exception
     */
    @Override
    public void start(Stage stage) throws Exception {

    }

}
