package org.example;

import javax.swing.SwingUtilities;
import org.example.ui.GameFrame;

public class GameApp {
    public static void main(String[] args) {

        System.setOut(new java.io.PrintStream(System.out, true,
                java.nio.charset.StandardCharsets.UTF_8));

        SwingUtilities.invokeLater(GameFrame::new);
    }
}
