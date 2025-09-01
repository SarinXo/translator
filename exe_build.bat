& "C:\Users\rollo\.jdks\openjdk-21.0.2\bin\jpackage.exe" `
  --input build/libs `
  --main-jar translator-1.0-SNAPSHOT.jar `
  --name "Clown-translator" `
  --type app-image `
  --icon src/main/resources/assets/icons/clown.ico `
  --java-options "--module-path C:\Users\rollo\.jdks_fx\javafx-sdk-21.0.8\lib --add-modules=javafx.controls,javafx.fxml -Djava.awt.headless=false -Djnativehook.lib.locator=sarinxo.desctop.translator.util.JNativeHookLibraryLocator"