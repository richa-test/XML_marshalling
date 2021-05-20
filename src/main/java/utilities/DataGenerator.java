package utilities;
import org.apache.commons.lang3.StringUtils;
import utilities.DateFunctions;

public class DataGenerator {

    public static long generateRandomLong(int digits){
        double randomDouble =(Math.floor(Math.pow(10,digits -1.0) +Math.random()* (Math.pow(10,digits)-Math.pow(10,digits -1.0)-1)));
        long randomLong = (long)randomDouble;
        return randomLong;
    }

    public static String generateEmployeeId(int seq) {
        long rand3Digits = generateRandomLong(3);

        String systemDate = DateFunctions.getFormattedCurrentDate("yyyyMMddHHmmSSS");
         String id = "E"+ StringUtils.leftPad((systemDate + rand3Digits+ seq), 22, '0');
         return id;
    }


    public static String generateDepartmentId(int seq) {
        long rand3Digits = generateRandomLong(3);

        String systemDate = DateFunctions.getFormattedCurrentDate("yyyyMMddHHmmSSS");
        String id = "D"+ StringUtils.leftPad((systemDate + rand3Digits+ seq), 22, '0');
        return id;
    }
}
