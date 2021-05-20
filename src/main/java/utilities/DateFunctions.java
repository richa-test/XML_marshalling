package utilities;
import org.apache.commons.lang3.StringUtils;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.*;

public class DateFunctions {
    /**for current date formatted date**/

    public static String getFormattedCurrentDate(String dateFormat){
        SimpleDateFormat formattedCurrentDate = new SimpleDateFormat(dateFormat);
        Date currentdate = new Date();
        return formattedCurrentDate.format(currentdate);
    }

    /**for future date formatted date**/

    public static String getFormattedFutureDate(String dateFormat,int days){
        SimpleDateFormat formattedFutureDate = new SimpleDateFormat(dateFormat);
        Date currentdate = new Date();
        Date futureDate = new Date(currentdate.getTime() + (1000 * 60 * 60 * (24 *  days)));

        return formattedFutureDate .format(futureDate);
    }

    /**for past date formatted date**/
    public static String getFormattedPastDate(String dateFormat,int days){
        SimpleDateFormat formattedPastDate = new SimpleDateFormat(dateFormat);
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE,-days);
        String pastDate =formattedPastDate.format(new Date(cal.getTimeInMillis()));
        return pastDate;
    }


}
