package sarinxo.desctop.translator.event;

import javafx.stage.Stage;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class ApplicationReadyEvent extends ApplicationEvent {

    private final Stage stage;

    public ApplicationReadyEvent(Object source, Stage stage) {
        super(source);
        this.stage = stage;
    }

}
