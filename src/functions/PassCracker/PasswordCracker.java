package functions.PassCracker;

import layout.SwitchPanel;

import java.security.NoSuchAlgorithmException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PasswordCracker
{
    public static char[] ALPHABET = "abcdefghijklmnopqrstuvwxyz0123456789@#&!()^=+/:.;,".toCharArray();

    public static byte[] ENCRYPTED_PASSWORD_BYTES;

    private static final int PASSWORD_LENGTH = 30;

    public static long START_TIME;


    private static byte[] encryptedPassToByteArray(String encryptedPass)
    {
        int length = encryptedPass.length();
        byte[] data = new byte[length / 2];

        for(int i = 0; i < length; i += 2)
        {
            data[i / 2] = (byte) ((Character.digit(encryptedPass.charAt(i), 16) << 4)
                    + Character.digit(encryptedPass.charAt(i + 1), 16));
        }

        return data;
    }

    public void startCracking(SwitchPanel switchPanel)
    {
        if(switchPanel.getTextField1().length() != 64)
        {
            switchPanel.addLineToCracker("INVALID ENCRYPTED PASSWORD... \n");
            return;
        }
        else
        {
            ENCRYPTED_PASSWORD_BYTES = encryptedPassToByteArray(switchPanel.getTextField1());
            switchPanel.addLineToCracker("CRACKING STARTED... \n");
        }

        int cores = Runtime.getRuntime().availableProcessors();
        int lengthSet = PASSWORD_LENGTH / cores;
        int end = 0;

        START_TIME = System.currentTimeMillis();

        ExecutorService executorService = Executors.newFixedThreadPool(cores);

        while (end < PASSWORD_LENGTH)
        {
            int start = end + 1;
            end = start + lengthSet;

            if (end > PASSWORD_LENGTH)
            {
                end = PASSWORD_LENGTH;
            }

            try
            {
                executorService.submit(new Cracker(start, end, switchPanel));
            }
            catch (NoSuchAlgorithmException ex)
            {
                switchPanel.addLineToCracker("ERROR :" + ex.toString() + "\n");
                ex.printStackTrace();
            }
        }

        executorService.shutdown();
    }
}

