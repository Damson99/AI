package functions;

import java.util.concurrent.ThreadLocalRandom;

public class Jokes
{

    public String tellRandomJoke()
    {
        int min = 0;
        int max = 7;
        int randomNumber = ThreadLocalRandom.current().nextInt(min, max + 1);
        String string;

        switch (randomNumber)
        {
            case 0:
                string = "What do you call a guy who’s had too much to drink?  A cab.";
                break;
            case 1:
                string = "How did the two dead brothers do in school? They were dead even.";
                break;
            case 2:
                string = "I’m not interested in any diet plan unless it lets me use rollover calories";
                break;
            case 3:
                string = "How do you know when the moon is broke? When it’s down to its last quarter.";
                break;
            case 4:
                string = "Which is closer, Florida or the moon? The moon. You can’t see Florida from here.";
                break;
            case 5:
                string = "Did you hear about the monkeys who shared an Amazon account? They were Prime mates.";
                break;
            case 6:
                string = "What is the biggest lie in the entire universe? I have read and agree to the Terms & Conditions.";
                break;
            default:
                string = "Why did the diet coach send her clients to the paint store? She heard you could get thinner there.";
        }

        return string;
    }
}

