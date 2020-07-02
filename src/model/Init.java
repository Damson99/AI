package model;

import layout.SwitchPanel;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Init
{
    public void init()
    {
        final TrayIcon trayIcon;

        if(SystemTray.isSupported())
        {
            SystemTray tray = SystemTray.getSystemTray();
            Image image = new ImageIcon(getClass().getResource("/resources/Icon1.png")).getImage();

            trayIcon = new TrayIcon(image);
            trayIcon.setImageAutoSize(true);
//        trayIcon.addActionListener(event ->
//        {
//            trayIcon.displayMessage("Jak to wyłączyć?", "Kliknij ikonę prawym przyciskiem myszy i wybierz Zakończ.",
//                    TrayIcon.MessageType.INFO);
//        });

            try
            {
                tray.add(trayIcon);
            }
            catch(AWTException ex)
            {
                SwitchPanel.getInstance().addCommand("ERROR : " + ex.toString() + "\n" +
                        "Nie udało się dodać ikony do zasobnika systemowego \n");
                ex.printStackTrace();
            }
        }
        else
        {
            SwitchPanel.getInstance().addCommand("OMEGA: No access to the system tray");
        }
    }

    public void saveCommandTrack()
    {
        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage(page);

        try
        {
            PDPageContentStream contentStream = new PDPageContentStream(document, page);
            contentStream.setFont(PDType1Font.TIMES_ROMAN, 14);
            contentStream.beginText();
            contentStream.showText("");
            contentStream.endText();
            contentStream.close();

            document.save(File.createTempFile("", ".pdf"));
            document.close();
        }
        catch (IOException ex)
        {
            SwitchPanel.getInstance().addCommand("ERROR : " + ex.toString() + "\n");
            ex.printStackTrace();
        }
    }
}
