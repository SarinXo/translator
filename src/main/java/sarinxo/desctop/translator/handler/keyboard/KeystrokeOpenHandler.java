package sarinxo.desctop.translator.handler.keyboard;

import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

@Slf4j
public class KeystrokeOpenHandler {

    public static void init(Stage stage) {
        log.info("Initialization Manager key");
        try {
            String libResourcePath = "/com/github/kwhat/jnativehook/lib/windows/x86_64/JNativeHook.dll";
            // создаем временную папку
            Path tempDir = Files.createTempDirectory("jnativehook");
            tempDir.toFile().deleteOnExit();

            // создаем временный файл для DLL
            Path tempLib = tempDir.resolve("JNativeHook.dll");
            try (InputStream in = KeystrokeOpenHandler.class.getResourceAsStream(libResourcePath)) {
                if (in == null) {
                    throw new RuntimeException("Не найден ресурс DLL: " + libResourcePath);
                }
                Files.copy(in, tempLib, StandardCopyOption.REPLACE_EXISTING);
            }

            // указываем JNativeHook путь к временной папке
            System.setProperty("jnativehook.lib.path", tempDir.toAbsolutePath().toString());
            GlobalScreen.registerNativeHook();
            GlobalScreen.addNativeKeyListener(new OpenAppKeyListener(stage));
            log.info("Initialization key manager was successful");
        } catch (NativeHookException | IOException ex) {
            log.error("It was not possible to initialize the global keyboard interceptor");
            System.exit(1);
        }
    }
}
