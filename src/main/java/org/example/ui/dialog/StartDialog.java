package org.example.ui.dialog;

import java.awt.Frame;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import org.example.game.GameMode;

public class StartDialog extends JDialog {

    private GameMode selectedMode;

    public StartDialog(Frame owner) {
        super(owner, "Меню", true);

        setSize(400, 250);
        setLayout(new GridLayout(6, 1));

        JButton rules = new JButton("Правила гри");
        rules.addActionListener(e -> showRules());
        add(rules);
        add(createButton("20 слів", GameMode.FIXED_20));
        add(createButton("40 слів", GameMode.FIXED_40));
        add(createButton("80 слів", GameMode.FIXED_80));
        add(createButton("Знаю всі міста", GameMode.UNTIL_END));

        setLocationRelativeTo(null);
    }

    private JButton createButton(String text, GameMode mode) {
        JButton btn = new JButton(text);
        btn.addActionListener(e -> {
            selectedMode = mode;
            dispose();
        });
        return btn;
    }

    private void showRules() {
        JOptionPane.showMessageDialog(this,
                """
                        Правила гри:
                        Вводьте назви міст по останній букві.
                        Не повторюйте назви.
                        Перемагає той, хто назвав міста з
                        більшею кількістью літер.
                        """,
                "Правила",
                JOptionPane.INFORMATION_MESSAGE);
    }

    public GameMode getSelectedMode() {
        return selectedMode;
    }
}
