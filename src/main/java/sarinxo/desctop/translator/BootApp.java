package sarinxo.desctop.translator;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class BootApp extends Application {

    public static void main(String[] args) {
        new SpringApplicationBuilder(BootApp.class)
                .web(WebApplicationType.NONE)
                .run(args);

        launch(args);
    }

    /**
     * Запуск UI
     * @param stage
     * @throws Exception
     */
    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Главное окно приложения");

        Button btn = new Button("Нажми меня");
        btn.setOnAction(event -> System.out.println("Кнопка нажата!"));

        StackPane root = new StackPane();
        root.getChildren().add(btn);

        Scene scene = new Scene(root, 400, 300);

        stage.setScene(scene);
        stage.show();
    }

}
