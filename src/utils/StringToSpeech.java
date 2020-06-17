package utils;

import layout.SwitchPanel;
import marytts.LocalMaryInterface;
import marytts.MaryInterface;
import marytts.exceptions.MaryConfigurationException;
import marytts.exceptions.SynthesisException;
import marytts.modules.synthesis.Voice;
import marytts.util.data.audio.AudioPlayer;

import javax.sound.sampled.AudioInputStream;
import java.io.IOException;
import java.util.Collection;

public class StringToSpeech
{
    private SwitchPanel switchPanel = new SwitchPanel();

    private AudioPlayer audioPlayer;

    private MaryInterface maryInterface;


    public StringToSpeech()
    {
        try
        {
            maryInterface = new LocalMaryInterface();
            setVoice("cmu-rms-hsmm");
        }
        catch (MaryConfigurationException ex)
        {
            switchPanel.addCommand("ERROR : " + ex.toString() + "\n");
            ex.printStackTrace();
        }
    }

    public Collection<Voice> getAvailableVoices()
    {
        return Voice.getAvailableVoices();
    }

    public void setVoice(String voice)
    {
        maryInterface.setVoice(voice);
    }

    public void speak(String text, boolean daemon, boolean join)
    {
        stopSpeaking();

        try(AudioInputStream audioInputStream = maryInterface.generateAudio(text))
        {
            audioPlayer = new AudioPlayer();
            audioPlayer.setAudio(audioInputStream);
            audioPlayer.setDaemon(daemon);
            audioPlayer.start();

            if(join)
                audioPlayer.join();
        }
        catch (SynthesisException | IOException | InterruptedException ex)
        {
            switchPanel.addCommand("ERROR : " + ex.toString() + "\n");
            ex.printStackTrace();
        }
    }

    private void stopSpeaking()
    {
        if (audioPlayer != null)
        {
            audioPlayer.cancel();
        }
    }
}
