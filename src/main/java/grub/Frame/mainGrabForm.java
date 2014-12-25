package grub.Frame;

import grub.whithCasper.CasperAccessor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class mainGrabForm extends JFrame {

    public mainGrabForm(String title) {
        super(title);
        setContentPane(rootPanel);
        cmdTextArea.setBorder(BorderFactory.createCompoundBorder
                (BorderFactory.createEmptyBorder(20, 0, 0, 0), BorderFactory.createLineBorder(new Color(48, 24, 100))));
        scrubBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                try {
                    CasperAccessor accessor = new CasperAccessor();
                    accessor.google(requestTxt.getText());
                    ArrayList<String> list = accessor.getListFromCmd();
                    for (String s : list) {
                        cmdTextArea.append(s + "\n");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    private JButton scrubBtn;
    private JPanel rootPanel;
    private JTextArea cmdTextArea;
    private JTextField requestTxt;
}
