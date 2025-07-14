package sarinxo.desctop.translator.view.translator;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import sarinxo.desctop.translator.event.ApplicationReadyEvent;

import java.io.IOException;
import java.net.URL;

@Slf4j
@Component
public class TranslatorAppMainPage implements ApplicationListener<ApplicationReadyEvent> {

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        try {
            log.info("Create Translator screen");
            URL screen = getClass().getResource("translator.fxml");
            Parent root = FXMLLoader.load(screen);
            Scene scene = new Scene(root, 1200, 800);

            Stage stage = event.getStage();
            stage.setTitle("Переводчик");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            log.error("Fail to create Translator screen!", e);
            throw new RuntimeException(e);
        }
        log.info("Translator screen successfully load!");
    }


}
