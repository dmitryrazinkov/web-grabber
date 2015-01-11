package grub.Frame;

import javax.swing.*;
import java.awt.event.*;
import java.util.Date;

public class ChangeAlertDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JLabel txt;
    private JLabel siteTxt;
    private JLabel scriptTxt;
    private JLabel dateTxt;

    public ChangeAlertDialog(String scriptName, String siteName, Date date) {
        setContentPane(contentPane);
//        setModal(true);
        setLocation(450, 150);
        getRootPane().setDefaultButton(buttonOK);

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

// call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

// call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

//
        scriptTxt.setText(scriptTxt.getText() + scriptName);
        siteTxt.setText(siteTxt.getText() + siteName);
        dateTxt.setText(dateTxt.getText() + date);
    }

    private void onCancel() {
// add your code here if necessary
        dispose();
    }

}
