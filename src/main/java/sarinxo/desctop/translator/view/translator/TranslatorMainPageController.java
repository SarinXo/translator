package sarinxo.desctop.translator.view.translator;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextArea;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sarinxo.desctop.translator.dto.LanguageCode;
import sarinxo.desctop.translator.dto.TranslateGoogleRequest;

import java.util.concurrent.CompletableFuture;

@Slf4j
@Setter
@Component
public class TranslatorMainPageController {

    @Setter(onMethod_ = @Autowired)
    private TranslatorService translatorService;

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

    private void onTranslate() {
        String textToTranslate = inputTextArea.getText();
        String fromLanguageCode = sourceLang.getSelectionModel().getSelectedItem().getCode();
        String toLanguageCode = targetLang.getSelectionModel().getSelectedItem().getCode();

        TranslateGoogleRequest request = new TranslateGoogleRequest(textToTranslate, fromLanguageCode, toLanguageCode);

        progressIndicator.setVisible(true);
        outputTextArea.clear();

        CompletableFuture<String> translatedResponse = translatorService.translate(request);

        translatedResponse.thenAccept(result ->
                Platform.runLater(() -> {
                    outputTextArea.setText(result);
                    progressIndicator.setVisible(false);
                })
        );
    }

}
