package sarinxo.desctop.translator.view.systemtray;

import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lombok.extern.slf4j.Slf4j;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

@Slf4j
public class TrayLoader {

    public static void init(Stage stage) {
        try {
            Toolkit.getDefaultToolkit();
            Platform.setImplicitExit(false);

            log.info("Tray initialization started");
            if (!SystemTray.isSupported()) {
                log.error("System tray is not supported");
                return;
            }

            MenuItem showItem = new MenuItem("Показать окно");
            showItem.addActionListener(e -> Platform.runLater(() -> {
                stage.show();
                stage.toFront();
                stage.requestFocus();
            }));

            SystemTray tray = SystemTray.getSystemTray();

            PopupMenu popupMenu = new PopupMenu();
            popupMenu.add(showItem);

            URL imageURL = TrayLoader.class.getResource("/assets/icons/clown.png");
            Image image = ImageIO.read(imageURL);
            TrayIcon trayIcon = new TrayIcon(image, "Переводчик", popupMenu);
            trayIcon.setImageAutoSize(true);
            tray.add(trayIcon);

            MenuItem exitItem = new MenuItem("Выйти");
            exitItem.addActionListener(it -> {
                Platform.exit();
                tray.remove(trayIcon);
                System.exit(0);
            });

            popupMenu.add(exitItem);

            stage.initStyle(StageStyle.UNDECORATED);
        } catch (AWTException | IOException e) {
            log.error("Не удалось добавить иконку в системный трей.", e);
        }
    }

}
