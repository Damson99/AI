package utils;

import java.text.DecimalFormat;

public class NumberToStringEng
{
    private static final String[] tensNames = { "", " ten", " twenty", " thirty", " forty", " fifty", " sixty",
            " seventy", " eighty", " ninety" };

    private static final String[] numNames = { "", " one", " two", " three", " four", " five", " six", " seven",
            " eight", " nine", " ten", " eleven", " twelve", " thirteen", " fourteen", " fifteen", " sixteen",
            " seventeen", " eighteen", " nineteen" };


    public String convert(long number)
    {
        if(number == 0)
        {
            return "zero";
        }

        String mask = "000000000000";
        DecimalFormat decimalFormat = new DecimalFormat(mask);
        String stringNumber = decimalFormat.format(number);

        int billions = Integer.parseInt(stringNumber.substring(0, 3));
        int millions = Integer.parseInt(stringNumber.substring(3, 6));
        int hundredThousands = Integer.parseInt(stringNumber.substring(6, 9));
        int thousands = Integer.parseInt(stringNumber.substring(9, 12));

        String tradBillions;
        String tradMillions;
        String tradHundredThousands;
        String result;

        if(billions == 0)
        {
            tradBillions = "";
        }
        else
        {
            tradBillions = convertLessThanOneThousand(billions) + " billions ";
        }
        result = tradBillions;

        if(millions == 0)
        {
            tradMillions = "";
        }
        else
        {
            tradMillions = convertLessThanOneThousand(millions) + " millions ";
        }
        result = result + tradMillions;

        switch (hundredThousands)
        {
            case 0:
                tradHundredThousands = "";
                break;
            case 1:
                tradHundredThousands = "one thousand ";
                break;
            default:
                tradHundredThousands = convertLessThanOneThousand(hundredThousands) + " thousand ";
                break;
        }
        result = result + tradHundredThousands;

        String tradThousand = convertLessThanOneThousand(thousands);
        result = result + tradThousand;

        return result.replaceAll("^\\s+", "").replaceAll("\\b\\s{2,}\\b", " ");
    }

    private String convertLessThanOneThousand(int number)
    {
        String far;

        if(number % 100 < 20)
        {
            far = numNames[number % 100];
            number /= 100;
        }
        else
        {
            far = numNames[number % 10];
            number /= 10;

            far = tensNames[number % 10] + far;
            number /= 10;
        }

        if(number == 0)
            return far;

        return numNames[number] + " hundred" + far;
    }
}
