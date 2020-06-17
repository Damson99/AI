package model;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Logger;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Port;
import javax.swing.*;

import functions.*;
import functions.PassCracker.PasswordCracker;
import layout.SwitchPanel;
import utils.StringToNumberEng;
import utils.StringToSpeech;
import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.LiveSpeechRecognizer;
import edu.cmu.sphinx.api.SpeechResult;


public class Main
{

    private Logger logger = Logger.getLogger(getClass().getName());

    private boolean enableWriting;

    private String task;

    private Thread speechThread;

    private Thread resourcesThread;

    private LiveSpeechRecognizer recognizer;

    private PCAttributes pcAttributes = new PCAttributes();

    private StringToSpeech stringToSpeech = new StringToSpeech();

    private StringToNumberEng stringToNumberEng = new StringToNumberEng();

    private DayInfo dayInfo = new DayInfo();

    private Opener opener = new Opener();

    private Jokes jokes = new Jokes();

    private Init init = new Init();

    private Calculator calculator = new Calculator();

    private PressKeys pressKeys = new PressKeys();

    private SwitchPanel switchPanel = new SwitchPanel();



    private Main()
    {
//        logger.log(Level.INFO, "OMEGA : Loading...\n");
        switchPanel.addCommand("OMEGA : Loading...\n");

        Configuration configuration = new Configuration();
        configuration.setAcousticModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us");
        configuration.setDictionaryPath("resource:/edu/cmu/sphinx/models/en-us/cmudict-en-us.dict");
        configuration.setLanguageModelPath("resource:/edu/cmu/sphinx/models/language/en-us.lm.dmp");
        configuration.setGrammarPath("file:/C:\\Java\\AI\\omega\\src\\resources\\grammar");
        configuration.setGrammarName("grammar");
        configuration.setUseGrammar(true);

        try
        {
            recognizer = new LiveSpeechRecognizer(configuration);
        }
        catch (IOException ex)
        {
            switchPanel.addCommand("ERROR : " + ex.toString() + "\n");
            ex.printStackTrace();
        }

        recognizer.startRecognition(true);


        startSpeechThread();
        startResourcesThread();
    }

    private void startSpeechThread()
    {
        if(speechThread != null && speechThread.isAlive())
        {
            return;
        }

        speechThread = new Thread(() ->
        {
            stringToSpeech.speak("Hello sir", false, true);

            try
            {
                while (true)
                {
                    SpeechResult speechResult = recognizer.getResult();

                    if(speechResult != null)
                    {
                        task = speechResult.getHypothesis();
//                        System.out.println("Me : " + task + "\n");
                        switchPanel.addCommand("Me : " + task + "\n");
                        task(task);
                    }
                    else
                    {
//                        System.out.println("OMEGA : I can't understand what you said.\n");
                        switchPanel.addCommand("OMEGA : I can't understand what you said.\n");
                    }
                }
            }
            catch (Exception ex)
            {
                switchPanel.addCommand("ERROR : " + ex.toString() + "\n");
                ex.printStackTrace();
            }

            switchPanel.addCommand("OMEGA : Speech thread has exited...");

        });

        speechThread.start();
    }

    private void startResourcesThread()
    {
        if(resourcesThread != null && resourcesThread.isAlive())
            return;

        resourcesThread = new Thread(() ->
        {

               while(true)
               {
                   if(!AudioSystem.isLineSupported(Port.Info.MICROPHONE))
                   {
//                       System.out.println("OMEGA : Microphone is unavailable.\n");
                       switchPanel.addCommand("OMEGA : Microphone is unavailable.\n");
                   }

//                   Thread.sleep(350);
               }

        });

        resourcesThread.start();
    }

    private void task(String task)
    {
        String[] array = task.split(" ");

        if(enableWriting)
        {
            if ("disable writing".equalsIgnoreCase(task))
            {
                enableWriting = false;
            }

            if ("delete word".equalsIgnoreCase(task))
            {
                pressKeys.pressKeyCombination(KeyEvent.VK_CONTROL, KeyEvent.VK_BACK_SPACE);
            }

            if("question mark".equalsIgnoreCase(task))
            {
                pressKeys.pressKeyCombination(KeyEvent.VK_SHIFT, KeyEvent.VK_DEAD_ACUTE);
            }

            if ("delete sentence".equalsIgnoreCase(task))
            {
                pressKeys.pressKeyCombination(KeyEvent.VK_SHIFT, KeyEvent.VK_HOME);
                pressKeys.pressKey("delete");
            }

            pressKeys.pressKey(task);
        }

        if(!enableWriting)
        {
            if("enable writing".equalsIgnoreCase(task))
            {
                enableWriting = true;
            }

            if ("hello".equalsIgnoreCase(task))
            {
                stringToSpeech.speak("Hello sir, how are you", false, true);
            }

            if ("what is your name".equalsIgnoreCase(task))
            {
                stringToSpeech.speak("OMEGA sir", false, true);
            }

            if ("who is your daddy".equalsIgnoreCase(task))
            {
                stringToSpeech.speak("Damian is my daddy", false, true);
            }

            if ("omega exit".equalsIgnoreCase(task))
            {
                stringToSpeech.speak("Goodbye sir", false, true);
                System.exit(0);
            }

            if ("switch to voice one".equalsIgnoreCase(task))
            {
                stringToSpeech.setVoice("cmu-slt-hsmm");
                stringToSpeech.speak("Yes sir", false, true);
            }

            if ("switch to voice two".equalsIgnoreCase(task))
            {
                stringToSpeech.setVoice("dfki-poppy-hsmm");
                stringToSpeech.speak("Yes sir", false, true);
            }

            if ("switch to voice three".equalsIgnoreCase(task))
            {
                stringToSpeech.setVoice("cmu-rms-hsmm");
                stringToSpeech.speak("Yes sir", false, true);
            }

            if ("what is the weather today".equalsIgnoreCase(task))
            {
                stringToSpeech.speak(dayInfo.weather(), false, true);
            }

            if ("what is the day today".equalsIgnoreCase(task))
            {
                stringToSpeech.speak(dayInfo.dayToday(), false, true);
            }

            if ("tell the current date".equalsIgnoreCase(task))
            {
                stringToSpeech.speak(dayInfo.currentDate(), false, true);
            }

            if("show me the most beautiful girl".equalsIgnoreCase(task))
            {
                opener.openBrowser("https://www.instagram.com/olciamat/");
                stringToSpeech.speak("She is the most beautiful girl on the world is she", false, true);
            }

            if("yes she is".equalsIgnoreCase(task))
            {
                stringToSpeech.speak("I know that", false, true);
            }

            if ("open".equalsIgnoreCase(array[0]))
            {
                if(array.length > 1)
                {
                    if ("file".equalsIgnoreCase(array[1]))
                    {
                        stringToSpeech.speak(opener.openFileExplorer(), false, true);
                    }
                    else if ("tab".equalsIgnoreCase(array[1]))
                    {
                        pressKeys.pressKeyCombination(KeyEvent.VK_CONTROL, KeyEvent.VK_T);
                    }
                    else
                    {
                        stringToSpeech.speak(opener.openBrowser(array[1]), false, true);
                    }
                }
            }

            if ("close".equalsIgnoreCase(array[0]))
            {
                if ("application".equalsIgnoreCase(array[1]))
                {
                    pressKeys.pressKeyCombination(KeyEvent.VK_ALT, KeyEvent.VK_F4);
                }
                else if ("tab".equalsIgnoreCase(array[1]))
                {
                    pressKeys.pressKeyCombination(KeyEvent.VK_CONTROL, KeyEvent.VK_W);
                }
            }

            if ("take screenshot".equalsIgnoreCase(task))
            {
                pressKeys.pressKey("screen");
            }

            if ("switch window".equalsIgnoreCase(task))
            {
                pressKeys.pressKeyCombination(KeyEvent.VK_ALT, KeyEvent.VK_TAB);
            }

            if ("left tab".equalsIgnoreCase(task))
            {
                pressKeys.pressKeyCombination(KeyEvent.VK_ALT, KeyEvent.VK_PAGE_DOWN);
            }

            if ("right tab".equalsIgnoreCase(task))
            {
                pressKeys.pressKeyCombination(KeyEvent.VK_ALT, KeyEvent.VK_PAGE_UP);
            }

            if ("previous tab".equalsIgnoreCase(task))
            {
                pressKeys.pressKeyCombination(KeyEvent.VK_ALT, KeyEvent.VK_LEFT);
            }

            if ("next tab".equalsIgnoreCase(task))
            {
                pressKeys.pressKeyCombination(KeyEvent.VK_ALT, KeyEvent.VK_RIGHT);
            }

            if ("tell me a joke".equalsIgnoreCase(task))
            {
                stringToSpeech.speak(jokes.tellRandomJoke(), false, false);
            }

            if ("scroll".equalsIgnoreCase(array[0]))
            {
                pressKeys.pressKey(array[1]);
            }

            if ("very funny".equalsIgnoreCase(task))
            {
                stringToSpeech.speak("Thank you", false, true);
            }

            if("set volume".equalsIgnoreCase(task))
            {
                if(array.length > 2)
                {
                    pcAttributes.setVolume(stringToNumberEng.convert(array[2]));
                }
            }

            if("show switch panel".equalsIgnoreCase(task))
            {
                switchPanel.showSwitchPanel();
            }

            if("show command list".equalsIgnoreCase(task))
            {
                init.setVisible(true);
            }

            if("hide command list".equalsIgnoreCase(task))
            {
                init.setVisible(false);
            }

            if("crack password".equalsIgnoreCase(task))
            {
                PasswordCracker passwordCracker = new PasswordCracker();
                passwordCracker.startCracking(switchPanel);
            }

            if("save command track".equalsIgnoreCase(task))
            {
                switchPanel.saveCommandTrack();
            }

            if ("calculate".equalsIgnoreCase(array[0]))
            {
                stringToSpeech.speak(calculator.calculate(task), false, true);
            }
        }
    }

    public static void main(String[] args)
    {
        JFrame frame = new JFrame("OMEGA ACCESS");
        frame.getContentPane().setBackground(Color.BLACK);
        frame.setSize(300, 150);
        frame.setIconImage(new ImageIcon("/resources/Icon1.png").getImage());
        System.out.println(new ImageIcon("/resources/Icon1.png").getImage().getSource());
        System.out.println(new ImageIcon("/resources/Icon1.png"));

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

        frame.add(passwordField);
        frame.add(passLabel);
        frame.add(badCredentials);
        frame.setLayout(null);
        frame.setVisible(true);

//        DELETE BEFORE JAR
        new Main();
        new Init().init();
        new SwitchPanel();

        passwordField.addActionListener(event ->
        {
            if(getAccess(passwordField.getPassword()))
            {
                frame.setVisible(false);
                frame.dispose();
                new Main();
                new Init().init();
                new SwitchPanel();
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












