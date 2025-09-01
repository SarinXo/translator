package sarinxo.desctop.translator.event;

import javafx.stage.Stage;
import lombok.Getter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Getter
public class OpenAppEvent {

    private final Stage stage;

    public OpenAppEvent(Stage stage) {
        this.stage = stage;
    }

    ExecutorService executor = Executors.newSingleThreadExecutor();

    public void shutdown() {
        executor.shutdown();
    }

}
