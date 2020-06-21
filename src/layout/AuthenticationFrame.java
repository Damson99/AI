package layout;

import model.Main;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class AuthenticationFrame extends JFrame
{

    public AuthenticationFrame()
    {
        setTitle("OMEGA ACCESS");
        getContentPane().setBackground(Color.BLACK);
        setSize(300, 150);
        setIconImage(new ImageIcon("/resources/Icon1.png").getImage());

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(100,40,100,30);
        passwordField.setBackground(Color.BLACK);
        passwordField.setForeground(Color.GREEN);

        JLabel passLabel = new JLabel("Password:");
        passLabel.setBounds(20,40, 80,30);
        passLabel.setForeground(Color.GREEN);

        JLabel badCredentials = new JLabel("Bad credentials");
        badCredentials.setBounds(20,80, 100,30);
        badCredentials.setForeground(Color.RED);
        badCredentials.setVisible(false);

        add(passwordField);
        add(passLabel);
        add(badCredentials);
        setLayout(null);
        setVisible(true);

        passwordField.addActionListener(event ->
        {
            if(getAccess(passwordField.getPassword()))
            {
                setVisible(false);
                dispose();
                Main.start();
            }
            else
            {
                badCredentials.setVisible(true);
            }
        });
    }

    private static boolean getAccess(char[] password)
    {
        char[] correctPass = { 'a', 's', 'd'};

        return password.length == correctPass.length && Arrays.equals(password, correctPass);
    }
}
