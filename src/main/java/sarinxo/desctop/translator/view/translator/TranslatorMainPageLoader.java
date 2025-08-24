package sarinxo.desctop.translator.view.translator;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import sarinxo.desctop.translator.event.OpenAppEvent;
import sarinxo.desctop.translator.handler.keyboard.KeystrokeHandler;

import java.io.IOException;
import java.net.URL;

@Slf4j
@Component
@RequiredArgsConstructor
public class TranslatorMainPageLoader {

    private final ApplicationContext ctxt;

    @EventListener
    public void onApplicationEvent(OpenAppEvent event) {
        try {
            log.info("Create TranslatorMainPage screen");
            URL screen = getClass().getResource("translator.fxml");

            FXMLLoader loader = new FXMLLoader(screen);
            loader.setControllerFactory(ctxt::getBean);

            Parent root = loader.load();
            Scene scene = new Scene(root, 600, 400);

            Stage stage = event.getStage();
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setTitle("Translator");
            stage.setScene(scene);
            stage.setResizable(true);

            TranslatorMainPageController controller = loader.getController();
            controller.stageInit(stage);

            stage.show();
            KeystrokeHandler.init();
        } catch (IOException e) {
            log.error("Fail to create Translator screen!", e);
            throw new RuntimeException(e);
        }
    }

}
