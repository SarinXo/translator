package sarinxo.desctop.translator.keyboardhook;

import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;
import javafx.application.Platform;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OpenAppKeyListener implements NativeKeyListener {

    private final KeyHistory history = new KeyHistory((byte) 3);
    private final Stage stage;

    public OpenAppKeyListener(Stage stage) {
        this.stage = stage;
    }

    @Override
    public void nativeKeyPressed(NativeKeyEvent e) {
        log.debug("button {} {}", e.getKeyChar(), e.getKeyCode());
        history.addKey(e.getKeyCode());
        int[] keys = history.getKeys();
        if (keys[0] == NativeKeyEvent.VC_SLASH //  /'] - последовательность для активации
                && keys[1] == NativeKeyEvent.VC_QUOTE
                && keys[2] == NativeKeyEvent.VC_CLOSE_BRACKET) {
            Platform.runLater(() -> {
                stage.show();
                stage.toFront();
                stage.requestFocus();
            });
        }
    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent e) {
        //no impl
    }

    @Override
    public void nativeKeyTyped(NativeKeyEvent e) {
        // no impl
    }

}
