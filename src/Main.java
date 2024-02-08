import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;
import factories.ButtonFactory;
import factories.WindowFactory;
import utils.ScreenSaver;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {

    static ScreenSaver screenSaver;
    static JFrame window;
    public static void main(String[] args) {

        screenSaver = new ScreenSaver();
        screenSaver.start();

        window = WindowFactory.getDummyWindow();
        window.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        JButton oldB = ButtonFactory.getButton("Old", new Rectangle(0, 0, 100, 100));
        oldB.addActionListener(e -> { screenSaver.setScreenSaver(ScreenSaver.getOldScreenSaver()); });

        JButton minB = ButtonFactory.getButton("Minimal", new Rectangle(100, 0, 100, 100));
        minB.addActionListener(e -> { screenSaver.setScreenSaver(ScreenSaver.getMinimalScreenSaver()); });

        JButton closeB = ButtonFactory.getButton("Close", new Rectangle(200, 0, 100, 100));
        closeB.addActionListener(e -> { System.exit(0); });

        JButton invisB = ButtonFactory.getButton("Close", new Rectangle(300, 0, 100, 100));
        invisB.setVisible(false);

        window.add(oldB);
        window.add(minB);
        window.add(closeB);
        window.add(invisB);

        try {
            GlobalScreen.registerNativeHook();
        }
        catch (NativeHookException ex) {
            System.err.println("There was a problem registering the native hook.");
            System.err.println(ex.getMessage());
            System.exit(1);
        }
        GlobalScreen.addNativeKeyListener(new KeyListener());
    }

    static class KeyListener implements NativeKeyListener{
        public void nativeKeyPressed(NativeKeyEvent e) {
            switch(NativeKeyEvent.getKeyText(e.getKeyCode())) {
                case "Escape":
                    screenSaver.toggle();
                    break;
                case "F2":
                    window.setVisible(true);
                    break;
            }
        }
    }

}