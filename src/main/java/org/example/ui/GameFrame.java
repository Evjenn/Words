package org.example.ui;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import org.example.game.GameMode;
import org.example.game.GameService;
import org.example.ui.dialog.StartDialog;
import org.example.ui.dialog.WelcomeDialog;

public class GameFrame extends JFrame {

    private GameService game;

    public GameFrame() {
        new WelcomeDialog(this).setVisible(true);
        startNewGame();
    }

    private void startNewGame() {
        StartDialog start = new StartDialog(this);
        start.setVisible(true);
        GameMode mode = start.getSelectedMode();
        if (mode == null) {
            System.exit(0);
        }
        game = new GameService(mode);
        setTitle("Міста");
        setSize(500, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        GamePanel panel = new GamePanel(game);
        panel.setOnGameOver(this::showResult);
        setContentPane(panel);
        getRootPane().setDefaultButton(panel.getSubmitButton());
        setVisible(true);
    }

    private void showResult() {

        int playerScore = game.getState().getPlayerScore();
        int compScore = game.getState().getComputerScore();
        String message = "Гра завершена!\n\n"
                + "Ваш рахунок: " + playerScore + "\n"
                + "Комп'ютер: " + compScore + "\n\n"
                + game.getWinner();

        Object[] options = {"Продовжити", "Покинути гру"};

        int choice = JOptionPane.showOptionDialog(
                this,
                message,
                "Результат",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                options,
                options[0]
        );
        if (choice == 0) {
            dispose();
            startNewGame();
        } else {
            System.exit(0);
        }
    }
}
