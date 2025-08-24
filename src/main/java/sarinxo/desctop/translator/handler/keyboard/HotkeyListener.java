package sarinxo.desctop.translator.handler.keyboard;

import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HotkeyListener implements NativeKeyListener {

    private final KeyHistory history = new KeyHistory((byte) 3);

    @Override
    public void nativeKeyPressed(NativeKeyEvent e) {
        log.info("button" + e.getKeyChar() + " " + e.getKeyCode());
        history.addKey(e.getKeyCode());
        int[] keys = history.getKeys();
        if (keys[0] == NativeKeyEvent.VC_CONTROL
                && keys[1] == NativeKeyEvent.VC_Z
                && keys[2] == NativeKeyEvent.VC_Z) {
            log.info("YES!");
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
