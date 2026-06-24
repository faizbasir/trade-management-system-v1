package src.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtil {

    public static String setTradeDate(){
        //format for date time
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        // Obtain current date and time
        LocalDateTime now = LocalDateTime.now();

        return now.format(formatter);
    }
}
