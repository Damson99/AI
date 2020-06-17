package functions;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DayInfo
{
    public String weather()
    {
        return "weather";
    }

    public String dayToday()
    {
        Date date = new Date();
        return new SimpleDateFormat("EEEE", Locale.ENGLISH).format(date.getTime());
    }

    public String currentDate()
    {
        return new SimpleDateFormat("dd-MMM-yyyy").format(new Date());
    }
}
