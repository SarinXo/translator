package sarinxo.desctop.translator.handler.keyboard;

import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Set;

@Slf4j
public class KeystrokeHandler {

    public static void  init() {
        log.info("Инициализация обработчика клавиш");
        try {
            GlobalScreen.registerNativeHook();
            GlobalScreen.addNativeKeyListener(new HotkeyListener());
        } catch (NativeHookException ex) {
            log.error("Не удалось инициализировать глобальный перехватчик клавиатуры.");
            System.exit(1);
        }
    }
}
