package org.example.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import org.example.game.GameService;
import org.example.game.MoveResult;
import org.example.util.WordsFormatter;

public class GamePanel extends JPanel {

    private static final int GAP = 10;
    private static final int PADDING = 15;
    private final GameService game;
    private final JTextArea history = new JTextArea();
    private final JTextField input = new JTextField();
    private final JLabel movesLabel = new JLabel();
    private final JLabel status = new JLabel("Введіть назву міста:");
    private final JLabel computerLabel = new JLabel("Комп'ютер: ");
    private final JButton submitBtn = new JButton("Зробити хід");
    private final JButton giveUpBtn = new JButton("Здаюсь");
    private Runnable onGameOver;

    public GamePanel(GameService game) {
        this.game = game;
        initUI();
    }

    public JButton getSubmitButton() {
        return submitBtn;
    }

    public void setOnGameOver(Runnable onGameOver) {
        this.onGameOver = onGameOver;
    }

    private void initUI() {

        setLayout(new BorderLayout(GAP, GAP));
        //empty spaces from general border
        setBorder(BorderFactory.createEmptyBorder(PADDING, PADDING, PADDING, PADDING));

        add(createTopPanel(), BorderLayout.NORTH);
        add(createHistoryPanel(), BorderLayout.CENTER);
        add(createBottomPanel(), BorderLayout.SOUTH);

        initHandlers();
        updateMovesLabel();
    }

    private JPanel createInputPanel() {
        JPanel panel = new JPanel(new BorderLayout(GAP, GAP));
        panel.add(input, BorderLayout.CENTER);
        panel.add(submitBtn, BorderLayout.EAST);
        return panel;
    }

    private JPanel createInfoPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(computerLabel, BorderLayout.WEST);
        panel.add(movesLabel, BorderLayout.EAST);
        return panel;
    }

    private JComponent createHistoryPanel() {
        history.setEditable(false);
        history.setLineWrap(true);
        history.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(history);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        return scrollPane;
    }

    private JPanel createBottomPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JPanel leftControls = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        leftControls.add(giveUpBtn);
        panel.add(leftControls, BorderLayout.WEST);
        return panel;
    }

    private JPanel createTopPanel() {
        JPanel panel = new JPanel(new BorderLayout(GAP, GAP));
        panel.add(status, BorderLayout.NORTH);
        panel.add(createInputPanel(), BorderLayout.CENTER);
        panel.add(createInfoPanel(), BorderLayout.SOUTH);
        return panel;
    }

    private void initHandlers() {
        submitBtn.addActionListener(e -> makeMove());
        input.addActionListener(e -> makeMove());
        giveUpBtn.addActionListener(e -> giveUp());
    }

    private void makeMove() {
        MoveResult player = game.playerMove(input.getText());
        if (!player.isValid()) {
            status.setText(player.getMessage());
            return;
        }
        history.append("Ви: " + WordsFormatter.format(player.getCity()) + "\n");
        if (game.isGameOver()) {
            finish();
            return;
        }
        MoveResult comp = game.computerMove();
        if (comp.getCity() != null) {
            history.append("Комп'ютер: " + WordsFormatter.format(comp.getCity()) + "\n");
            computerLabel.setText("Комп'ютер: " + WordsFormatter.format(comp.getCity()));
        }
        if (!comp.isValid()) {
            finish();
            return;
        }
        input.setText("");
        String msg = comp.getMessage();
        if (msg != null && !msg.isBlank()) {
            status.setText("<html>"
                    + msg.replace("\n", "<br>")
                    + "Наступна літера: " + comp.getNextLetter()
                    + "</html>");
        } else {
            status.setText("Наступна літера: " + comp.getNextLetter());
        }
        updateMovesLabel();
        history.setCaretPosition(history.getDocument().getLength());
        if (game.isGameOver()) {
            finish();
        }
    }

    private void updateMovesLabel() {

        int current = game.getState().getCurrentMove();
        int total = game.getState().getTotalMoves();
        String totalText = (total == Integer.MAX_VALUE) ? "∞" : String.valueOf(total);
        movesLabel.setText("Кількість ходів: " + current + " / " + totalText);
    }

    private void giveUp() {
        game.giveUpService();
        finish();
    }

    private void finish() {
        input.setEnabled(false);
        submitBtn.setEnabled(false);

        if (onGameOver != null) {
            onGameOver.run(); //run showResult()
        }
    }

}
