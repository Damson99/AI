package layout;

import model.remoteDesktop.client.CreateFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class AuthenticationSocket extends JFrame implements ActionListener
{
    private Socket socket;
    private DataOutputStream passCheck;
    private DataInputStream verification;
    private String verify = "";
    private JTextField jTextField;
    private JPasswordField passwordField;
    private JLabel passLabel, badCredentials;
    private String width = "", height = "";
    private SwitchPanel switchPanel = new SwitchPanel();


    public AuthenticationSocket(Socket socket)
    {
        setSize(300, 80);
        setTitle("Remote Desktop");
        getContentPane().setBackground(Color.BLACK);
        setIconImage(new ImageIcon("/resources/Icon1.png").getImage());

        jTextField = new JPasswordField();
        jTextField.setBounds(100, 10, 100, 25);
        jTextField.setBackground(Color.BLACK);
        jTextField.setForeground(Color.GREEN);

        passwordField = new JPasswordField();
        passwordField.setBounds(100,50,100,30);
        passwordField.setBackground(Color.BLACK);
        passwordField.setForeground(Color.GREEN);

        passLabel = new JLabel("Password:");
        passLabel.setBounds(20,40, 80,30);
        passLabel.setForeground(Color.GREEN);

        badCredentials = new JLabel("Bad credentials");
        badCredentials.setBounds(20,80, 100,30);
        badCredentials.setForeground(Color.RED);
        badCredentials.setVisible(false);

        add(passwordField);
        add(passLabel);
        add(badCredentials);
        setLocation(getX() / 2, getY() / 2);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        try
        {
            passCheck = new DataOutputStream(socket.getOutputStream());
            verification = new DataInputStream(socket.getInputStream());
            passCheck.writeUTF(jTextField.getText());
            verify = verification.readUTF();
        }
        catch (Exception ex)
        {
            switchPanel.addCommand("ERROR: " + ex.toString());
            ex.printStackTrace();
        }

        if(verify.equals("valid"))
        {
            try
            {
                width = verification.readUTF();
                height = verification.readUTF();
            }
            catch (Exception ex)
            {
                switchPanel.addCommand("ERROR: " + ex.toString());
                ex.printStackTrace();
            }

            CreateFrame createFrame = new CreateFrame();
            dispose();
        }
        else
        {
            JOptionPane.showInputDialog(this, "Incorrect password");
        }
    }
}
