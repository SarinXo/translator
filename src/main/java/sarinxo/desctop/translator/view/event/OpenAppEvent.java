package sarinxo.desctop.translator.view.event;

import javafx.stage.Stage;
import lombok.Getter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Getter
public class OpenAppEvent {

    private final Stage stage;
    ExecutorService executor = Executors.newSingleThreadExecutor();

    public OpenAppEvent(Stage stage) {
        this.stage = stage;
    }

    public void shutdown() {
        executor.shutdown();
    }

}
