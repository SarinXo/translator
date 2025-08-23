package sarinxo.desctop.translator.event;

import javafx.stage.Stage;
import lombok.Getter;

@Getter
public class OpenAppEvent {

    private final Stage stage;

    public OpenAppEvent(Stage stage) {
        this.stage = stage;
    }

}
