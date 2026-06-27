package src.util;

public class CcyUtil {
    public static boolean validateCcyPair(String ccyPair){

        // Basic validation to check if ccy pair is valid format or not.
        // No check on whether actual ccy is a valid ccy or not
        String[] ccy = ccyPair.split("/");
        return ccy.length == 2 && ccy[0].length() == 3 && ccy[1].length() == 3;
    }
}
