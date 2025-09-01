package sarinxo.desctop.translator.keyboardhook;

import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class KeystrokeOpenHandler {

    public static void init(Stage stage) {
        log.info("Initialization Manager key");
        try {
            GlobalScreen.registerNativeHook();
            GlobalScreen.addNativeKeyListener(new OpenAppKeyListener(stage));
            log.info("Initialization key manager was successful");
        } catch (NativeHookException e) {
            log.error("It was not possible to initialize the global keyboard interceptor");
            System.exit(1);
        }
    }
}
