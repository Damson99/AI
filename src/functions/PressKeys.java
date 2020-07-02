package functions;


import layout.SwitchPanel;

import java.awt.*;
import java.awt.event.KeyEvent;

public class PressKeys
{
    private Robot robot;


    public PressKeys()
    {
        try
        {
            robot = new Robot();
//            robot.waitForIdle();
        }
        catch (AWTException ex)
        {
            SwitchPanel.getInstance().addCommand("ERROR : " + ex.toString() + "\n");
            ex.printStackTrace();

        }
    }

    public void pressKey(String key)
    {
        int value = getValue(key);
        robot.keyPress(value);
        robot.keyRelease(value);
    }

    public void pressKeyCombination(int toHold, int toPress)
    {
        robot.keyPress(toHold);
        robot.keyPress(toPress);
        robot.keyRelease(toPress);
        robot.keyRelease(toHold);
    }

    private int getValue(String key)
    {
        int value = KeyEvent.VK_SPACE;

        if(key.equalsIgnoreCase("delete"))
        {
            value = KeyEvent.VK_BACK_SPACE;
        }

        if(key.equalsIgnoreCase("delete word"))
        {
            value = KeyEvent.VK_BACK_SPACE;
        }

        if(key.equalsIgnoreCase("delete sentence"))
        {
            value = KeyEvent.VK_BACK_SPACE;
        }

        if(key.equalsIgnoreCase("enter"))
        {
            value = KeyEvent.VK_ENTER;
        }

        if(key.equalsIgnoreCase("space"))
        {
            value = KeyEvent.VK_SPACE;
        }

        if(key.equalsIgnoreCase("caps lock"))
        {
            value = KeyEvent.VK_CAPS_LOCK;
        }

        if (key.equalsIgnoreCase("period"))
        {
            value = KeyEvent.VK_PERIOD;
        }

        if(key.equalsIgnoreCase("comma"))
        {
            value = KeyEvent.VK_COMMA;
        }

        if (key.equalsIgnoreCase("delete"))
        {
            value = KeyEvent.VK_BACK_SPACE;
        }

        if(key.equalsIgnoreCase("delete word"))
        {
            value = KeyEvent.VK_BACK_SPACE;
        }

        if(key.equalsIgnoreCase("delete sentence"))
        {
            value = KeyEvent.VK_BACK_SPACE;
        }

        if(key.equalsIgnoreCase("enter"))
        {
            value = KeyEvent.VK_ENTER;
        }

        if(key.equalsIgnoreCase("space"))
        {
            value = KeyEvent.VK_SPACE;
        }

        if(key.equalsIgnoreCase("caps lock"))
        {
            value = KeyEvent.VK_CAPS_LOCK;
        }

        if (key.equalsIgnoreCase("period"))
        {
            value = KeyEvent.VK_PERIOD;
        }

        if(key.equalsIgnoreCase("comma"))
        {
            value = KeyEvent.VK_COMMA;
        }

        if(key.equalsIgnoreCase("a"))
        {
            value = KeyEvent.VK_A;
        }

        if(key.equalsIgnoreCase("b"))
        {
            value = KeyEvent.VK_B;
        }

        if(key.equalsIgnoreCase("c"))
        {
            value = KeyEvent.VK_C;
        }

        if(key.equalsIgnoreCase("d"))
        {
            value = KeyEvent.VK_D;
        }

        if(key.equalsIgnoreCase("e"))
        {
            value = KeyEvent.VK_E;
        }

        if(key.equalsIgnoreCase("f"))
        {
            value = KeyEvent.VK_F;
        }

        if(key.equalsIgnoreCase("g"))
        {
            value = KeyEvent.VK_G;
        }

        if(key.equalsIgnoreCase("h"))
        {
            value = KeyEvent.VK_H;
        }

        if(key.equalsIgnoreCase("i"))
        {
            value = KeyEvent.VK_I;
        }

        if(key.equalsIgnoreCase("j"))
        {
            value = KeyEvent.VK_J;
        }

        if(key.equalsIgnoreCase("k"))
        {
            value = KeyEvent.VK_K;
        }

        if(key.equalsIgnoreCase("q"))
        {
            value = KeyEvent.VK_Q;
        }

        if(key.equalsIgnoreCase("l"))
        {
            value = KeyEvent.VK_L;
        }

        if(key.equalsIgnoreCase("m"))
        {
            value = KeyEvent.VK_M;
        }

        if(key.equalsIgnoreCase("n"))
        {
            value = KeyEvent.VK_N;
        }

        if(key.equalsIgnoreCase("o"))
        {
            value = KeyEvent.VK_O;
        }

        if(key.equalsIgnoreCase("p"))
        {
            value = KeyEvent.VK_P;
        }

        if(key.equalsIgnoreCase("r"))
        {
            value = KeyEvent.VK_R;
        }

        if(key.equalsIgnoreCase("s"))
        {
            value = KeyEvent.VK_S;
        }

        if(key.equalsIgnoreCase("t"))
        {
            value = KeyEvent.VK_T;
        }

        if(key.equalsIgnoreCase("u"))
        {
            value = KeyEvent.VK_U;
        }

        if(key.equalsIgnoreCase("w"))
        {
            value = KeyEvent.VK_W;
        }

        if(key.equalsIgnoreCase("v"))
        {
            value = KeyEvent.VK_V;
        }

        if(key.equalsIgnoreCase("x"))
        {
            value = KeyEvent.VK_X;
        }

        if(key.equalsIgnoreCase("y"))
        {
            value = KeyEvent.VK_Y;
        }

        if(key.equalsIgnoreCase("z"))
        {
            value = KeyEvent.VK_Z;
        }

        return value;
    }
}
