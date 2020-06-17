package layout;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import functions.PassCracker.PasswordCracker;

import javax.swing.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class SwitchPanel
{
    private JPanel panel1;
    private JTabbedPane tabbedPane1;
    private JTextField textField1;
    private JTextField textField2;
    private JTextArea textArea1;
    private JTextArea textArea2;
    private JTextPane textPane1;
    private JTextPane textPane2;


    public void showSwitchPanel()
    {
        JFrame frame = new JFrame();
        frame.setContentPane(panel1);
        frame.setIconImage(new ImageIcon("/resources/Icon1.png").getImage());
        frame.setVisible(true);
        frame.pack();
    }

    public String getTextField1()
    {
        return textField1.getText();
    }

    public void setTextField2(String s)
    {
        textField2.setText(s);
    }

    public void addLineToCracker(String s)
    {
        textArea2.append(s);
    }

    public void addCommand(String s)
    {
        textArea1.append(s);
    }

    public void saveCommandTrack()
    {
        try
        {
            Document document = new Document(PageSize.A4);

            File file = new File("command.pdf");
            PdfWriter.getInstance(document, new FileOutputStream(file));
            Font font = FontFactory.getFont(FontFactory.HELVETICA, 14);

            document.open();
            Chunk chunk = new Chunk(textArea1.getText(), font);
            document.add(chunk);
            document.close();
            System.out.println(file.getAbsolutePath());
        }
        catch (IOException | DocumentException ex)
        {
            addCommand("ERROR : " + ex.toString() + "\n");
            ex.printStackTrace();
        }
    }
}
