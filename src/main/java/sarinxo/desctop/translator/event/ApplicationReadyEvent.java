package sarinxo.desctop.translator.event;

import javafx.stage.Stage;
import lombok.Getter;

@Getter
public class ApplicationReadyEvent {

    private final Stage stage;

    public ApplicationReadyEvent(Stage stage) {
        this.stage = stage;
    }

}
