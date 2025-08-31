package sarinxo.desctop.translator.util;

import com.github.kwhat.jnativehook.NativeLibraryLocator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Collections;
import java.util.Iterator;
import java.util.Locale;

public class JNativeHookLibraryLocator implements NativeLibraryLocator {

    @Override
    public Iterator<File> getLibraries() {
        String os = detectOs();
        String arch = detectArch();

        String base = "/com/github/kwhat/jnativehook/lib/" + os + "/" + arch + "/";
        String lib = switch (os) {
            case "windows" -> "JNativeHook.dll";
            case "linux"   -> "libJNativeHook.so";
            default        -> "libJNativeHook.dylib";
        };
        String path = base + lib;

        try (InputStream in = getClass().getResourceAsStream(path)) {
            if (in == null)
                throw new FileNotFoundException(path);

            String suffix = lib.substring(lib.lastIndexOf('.'));
            Path tmp = Files.createTempFile("jnativehook-", suffix);
            Files.copy(in, tmp, StandardCopyOption.REPLACE_EXISTING);
            tmp.toFile().deleteOnExit();
            return Collections.singletonList(tmp.toFile()).iterator();
        } catch (IOException e) {
            throw new RuntimeException("Failed to extract native lib: " + path, e);
        }
    }

    private static String detectOs() {
        String os = System.getProperty("os.name").toLowerCase(Locale.ROOT);
        String prefix = os.length() >= 3 ? os.substring(0, 3) : os;

        return switch (prefix) {
            case "win" -> "windows";
            case "lin" -> "linux";
            case "mac", "dar" -> "darwin";
            default -> "UNSUPPORTED OS";
        };
    }

    private static String detectArch() {
        String arch = System.getProperty("os.arch")
                .toLowerCase(Locale.ROOT)
                .replace("amd", "x");

        return switch (arch) {
            case "aarch64", "arm64" -> "arm64";
            case "arm" -> "arm";
            case "x86_64", "x64", "x86-64" -> "x86_64";
            default -> "x86";
        };
    }

}
