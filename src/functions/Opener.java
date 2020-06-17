package functions;


import layout.SwitchPanel;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class Opener
{
    private SwitchPanel switchPanel = new SwitchPanel();

    public String openBrowser(String website)
    {
        String string = "Browser is unavailable";
        String address;

        if(website.equalsIgnoreCase("browser"))
        {
            address = "https://www.google.com/";
        }
        else if(website.startsWith("http"))
        {
            address = website;
        }
        else
        {
            address = "https://www." + website + ".com";
        }

        if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE))
        {
            try
            {
                Desktop.getDesktop().browse(new URI(address));
                string = website + " opened";
            }
            catch (URISyntaxException | IOException ex)
            {
                switchPanel.addCommand("ERROR : " + ex.toString() + "\n");
                ex.printStackTrace();
            }
        }
        else
        {
            return string;
        }

        return string;
    }

    public String openFileExplorer()
    {
        String string = "File explorer is unavailable";
        if(Desktop.isDesktopSupported())
        {
            try
            {
                Desktop.getDesktop().open(new File("C:/"));
                string = "File explorer opened";
            }
            catch (IOException ex)
            {
                switchPanel.addCommand("ERROR : " + ex.toString() + "\n");
                ex.printStackTrace();
            }
        }
        else
        {
            return string;
        }

        return string;
    }
}
