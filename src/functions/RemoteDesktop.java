package functions;

import layout.AuthenticationSocket;
import layout.SwitchPanel;
import utils.StringToSpeech;

import javax.swing.*;
import java.net.Socket;

public class RemoteDesktop
{
    private final int port = 4907;
    private SwitchPanel switchPanel = new SwitchPanel();
    private StringToSpeech stringToSpeech = new StringToSpeech();


    public void initialize()
    {
        try
        {
            Socket socket = new Socket(JOptionPane.showInputDialog("Enter server ip"), port);
            switchPanel.addCommand("Connecting...");
            stringToSpeech.speak("Connecting", false, true);
            AuthenticationSocket socketAuthentication = new AuthenticationSocket(socket);
        }
        catch (Exception ex)
        {
            switchPanel.addCommand("ERROR: " + ex.toString());
            stringToSpeech.speak("error",false, true);
            ex.printStackTrace();
        }
    }
}
