package sarinxo.desctop.translator.view.translator;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sarinxo.desctop.translator.view.systemtray.TrayLoader;
import sarinxo.desctop.translator.view.util.ResizeHelper;
import sarinxo.desctop.translator.web.dto.LanguageCode;
import sarinxo.desctop.translator.web.dto.TranslateGoogleRequest;
import sarinxo.desctop.translator.web.service.TranslatorService;

import java.util.concurrent.CompletableFuture;

@Slf4j
@Setter
@Component
public class TranslatorMainPageController {

    @Setter(onMethod_ = @Autowired)
    private TranslatorService translatorService;

    @FXML
    private HBox titleBar;
    @FXML
    private Button minimizeButton;
    @FXML
    private Button fullscreenButton;
    @FXML
    private Button closeButton;
    private double dragOffsetX;
    private double dragOffsetY;

    @FXML
    private ComboBox<LanguageCode> sourceLang;
    @FXML
    private ComboBox<LanguageCode> targetLang;
    @FXML
    private ProgressIndicator progressIndicator;
    @FXML
    private TextArea inputTextArea;
    @FXML
    private TextArea outputTextArea;
    @FXML
    private Button translateButton;

    //todo отреафкторить и переместить классы
    @FXML
    public void initialize() {
        log.info("FXML controller initialized");

        ObservableList<LanguageCode> langs = FXCollections.observableArrayList(LanguageCode.values());
        sourceLang.setItems(langs);
        targetLang.setItems(langs.filtered(lang -> lang != LanguageCode.AUTO));

        sourceLang.getSelectionModel().select(LanguageCode.AUTO);
        targetLang.getSelectionModel().select(LanguageCode.EN);

        translateButton.setOnAction(event -> onTranslate());
    }

    public void stageInit(Stage stage) {
        minimizeButton.setOnAction(e -> stage.setIconified(true));
        fullscreenButton.setOnAction(e -> stage.setFullScreen(!stage.isFullScreen()));
        closeButton.setOnAction(e -> stage.hide());

        ResizeHelper.addResizeListener(stage, 6);

        titleBar.setFillHeight(true);

        titleBar.setOnMousePressed(event -> {
            dragOffsetX = stage.getX() - event.getScreenX();
            dragOffsetY = stage.getY() - event.getScreenY();
        });
        titleBar.setOnMouseDragged(event -> {
            stage.setX(event.getScreenX() + dragOffsetX);
            stage.setY(event.getScreenY() + dragOffsetY);
        });
        TrayLoader.init(stage);

    }

    private void onTranslate() {
        String textToTranslate = inputTextArea.getText();
        if (Strings.isBlank(textToTranslate)) {
            return;
        }

        String fromLanguageCode = sourceLang.getSelectionModel().getSelectedItem().getCode();
        String toLanguageCode = targetLang.getSelectionModel().getSelectedItem().getCode();

        TranslateGoogleRequest request = new TranslateGoogleRequest(textToTranslate, fromLanguageCode, toLanguageCode);

        progressIndicator.setVisible(true);
        outputTextArea.clear();

        CompletableFuture<String> translatedResponse = translatorService.translate(request);
        //todo сделать обработку ошибок
        translatedResponse.thenAccept(result ->
                Platform.runLater(() -> {
                    outputTextArea.setText(result);
                    progressIndicator.setVisible(false);
                })
        );
    }

}
