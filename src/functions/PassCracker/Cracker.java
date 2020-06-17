package functions.PassCracker;

import layout.SwitchPanel;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class Cracker implements Runnable
{
    private int start;

    private int end;

    private final MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");

    private static  boolean DONE = false;

    private String decrypted;

    private SwitchPanel switchPanel;


    public Cracker(int s, int e, SwitchPanel sp) throws NoSuchAlgorithmException
    {
        start = s;
        end = e;
        switchPanel = sp;
    }

    private void generate(StringBuilder stringBuilder, int length)
    {
        if(DONE) return;

        if(length == stringBuilder.length())
        {
            String candidate = stringBuilder.toString();
            byte[] bytes = messageDigest.digest(candidate.getBytes());

            if(Arrays.equals(bytes, PasswordCracker.ENCRYPTED_PASSWORD_BYTES))
            {
                decrypted = candidate;
                DONE = true;
            }

            return;
        }

        switchPanel.addLineToCracker("Password not cracked for subset [ " + start + ", " + end + " ]" + "\n");

        for(int i = 0; i < PasswordCracker.ALPHABET.length && !DONE; i++)
        {
            char letter = PasswordCracker.ALPHABET[i];
            stringBuilder.setCharAt(length, letter);
            generate(stringBuilder, length + 1);
        }
    }

    @Override
    public void run()
    {
        for(int length = start; length <= end && !DONE; length++)
        {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.setLength(length);
            generate(stringBuilder, 0);
        }

        if(DONE)
        {
            long duration = System.currentTimeMillis() - PasswordCracker.START_TIME;
            switchPanel.addLineToCracker("[+] Password Cracked in " + TimeUnit.MILLISECONDS.toSeconds(duration)
                    + "." + TimeUnit.MILLISECONDS.toMillis(duration) + " sec" + "\n");
            switchPanel.setTextField2(decrypted);
            System.out.println(decrypted);
        }
        else
        {
            switchPanel.addLineToCracker("[-] Password not cracked for subset [ " + start + ", " + end + " ]" + "\n");
            System.out.println("Password not cracked for subset [ " + start + ", " + end + " ]" + "\n");
        }
    }
}
