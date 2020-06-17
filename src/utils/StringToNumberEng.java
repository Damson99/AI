package utils;

public class StringToNumberEng
{
    public int convert(String word)
    {
        int wordNumber = 0;
        String[] words = word.split(" ");

        if ("zero".equalsIgnoreCase(word))
        {
            return 0;
        }

        if (word.contains("thousand"))
        {
            int indexOfThousand = word.indexOf("thousand");
            System.out.println(indexOfThousand);

            String beforeThousand = word.substring(0, indexOfThousand);
            System.out.println(beforeThousand);

            String[] arrayBeforeThousand = beforeThousand.split(" ");
            System.out.println(arrayBeforeThousand.length);

            if (arrayBeforeThousand.length == 2)
            {
                wordNumber = wordNumber + 1000 * (wordToNumber(arrayBeforeThousand[0]) + wordToNumber(arrayBeforeThousand[1]));
            }

            if (arrayBeforeThousand.length == 1)
            {
                wordNumber = wordNumber + 1000 * (wordToNumber(arrayBeforeThousand[0]));
            }
            System.out.println(wordNumber);
        }

        if (word.contains("hundred"))
        {
            int indexOfHundred = word.indexOf("hundred");
            System.out.println(indexOfHundred);

            String beforeHundred = word.substring(0, indexOfHundred);
            System.out.println(beforeHundred);

            String[] arrayBeforeHundred = beforeHundred.split(" ");
            wordNumber = wordNumber + 100 * (wordToNumber(arrayBeforeHundred[arrayBeforeHundred.length - 1]));
            String afterHundred = word.substring(indexOfHundred + 8);
            String[] arrayAfterHundred = afterHundred.split(" ");

            if (arrayAfterHundred.length == 1)
            {
                wordNumber = wordNumber + (wordToNumber(arrayAfterHundred[0]));
            }

            if (arrayAfterHundred.length == 2)
            {
                wordNumber = wordNumber + (wordToNumber(arrayAfterHundred[1] + wordToNumber(arrayAfterHundred[0])));
            }
            System.out.println(wordNumber);
        }

        if(!word.contains("thousand") && !word.contains("hundred"))
        {
            if(words.length == 1)
            {
                wordNumber = wordNumber + (wordToNumber((words[0])));
            }

            if(words.length == 2)
            {
                wordNumber = wordNumber + (wordToNumber((words[1])));
            }
        }

        return wordNumber;
    }

    private int wordToNumber(String word)
    {
        int num;

        switch (word)
        {
            case "one":
                num = 1;
                break;
            case "two":
                num = 2;
                break;
            case "three":
                num = 3;
                break;
            case "four":
                num = 4;
                break;
            case "five":
                num = 5;
                break;
            case "six":
                num = 6;
                break;
            case "seven":
                num = 7;
                break;
            case "eight":
                num = 8;
                break;
            case "nine":
                num = 9;
                break;
            case "ten":
                num = 10;
                break;
            case "eleven":
                num = 11;
                break;
            case "twelve":
                num = 12;
                break;
            case "thirteen":
                num = 13;
                break;
            case "fourteen":
                num = 14;
                break;
            case "fifteen":
                num = 15;
                break;
            case "sixteen":
                num = 16;
                break;
            case "seventeen":
                num = 17;
                break;
            case "eighteen":
                num = 18;
                break;
            case "nineteen":
                num = 19;
                break;
            case "twenty":
                num = 20;
                break;
            case "thirty":
                num = 30;
                break;
            case "forty":
                num = 40;
                break;
            case "fifty":
                num = 50;
                break;
            case "sixty":
                num = 60;
                break;
            case "seventy":
                num = 70;
                break;
            case "eighty":
                num = 80;
                break;
            case "ninety":
                num = 90;
                break;
            case "hundred":
                num = 100;
                break;
            case "thousand":
                num = 1000;
                break;
                default:
                    num = 0;
        }

        return num;
    }
}
