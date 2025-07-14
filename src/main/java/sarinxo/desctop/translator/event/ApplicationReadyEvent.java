package sarinxo.desctop.translator.event;

import javafx.stage.Stage;
import org.springframework.context.ApplicationEvent;

public class ApplicationReadyEvent extends ApplicationEvent {

    private final Stage stage;

    public ApplicationReadyEvent(Object source, Stage stage) {
        super(source);
        this.stage = stage;
    }

    public Stage getStage() {
        return stage;
    }
}
