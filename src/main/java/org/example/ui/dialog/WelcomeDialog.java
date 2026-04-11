package org.example.ui.dialog;

import java.awt.BorderLayout;
import java.awt.Frame;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class WelcomeDialog extends JDialog {

    public WelcomeDialog(Frame owner) {
        super(owner, "Вітаємо", true);

        setSize(400, 150);
        setLayout(new BorderLayout());

        JLabel text = new JLabel(
                "Вітаємо вас у грі дитинства і всіх розумників!",
                SwingConstants.CENTER
        );

        JButton ok = new JButton("OK");
        ok.addActionListener(e -> dispose());

        add(text, BorderLayout.CENTER);
        add(ok, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
    }
}
