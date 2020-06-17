package functions;

import utils.NumberToStringEng;
import utils.StringToNumberEng;

public class Calculator
{
    private StringToNumberEng stringToNumberEng = new StringToNumberEng();

    private NumberToStringEng numberToStringEng = new NumberToStringEng();



    public String calculate(String task)
    {
        String[] array = task.split("(plus|minus|multiply|division){1}");
        if(array.length > 2)
        {
            return "Bad information";
        }

        int num1 = stringToNumberEng.convert(array[0]);
        int num2 = stringToNumberEng.convert(array[1]);
        int calc;
        String symbol = "?";

        if(task.contains("plus"))
        {
            calc = num1 + num2;
            symbol = "+";
        }
        else if(task.contains("minus"))
        {
            calc = num1 - num2;
            symbol = "-";
        }
        else if(task.contains("multiply"))
        {
            calc = num1 * num2;
            symbol = "*";
        }
        else if(task.contains("division"))
        {
            calc = num1 / num2;
            symbol = "/";
        }
        else
        {
            calc = 0;
        }

        System.out.println("OMEGA: " + num1 + " " + symbol + " " + num2 + " = " + calc);
        return numberToStringEng.convert(Math.abs(calc));
    }
}
