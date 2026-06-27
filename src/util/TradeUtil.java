package src.util;

import src.enums.Direction;
import src.enums.TradeStatus;
import src.enums.TradeType;
import src.model.Trade;

import java.math.BigDecimal;
import java.util.List;

public class TradeUtil {
    public static String generateTradeID(List<Trade> tradeRepo, TradeType type){
        int counter = 0;
        for (Trade trade : tradeRepo){
            String[] idArray = trade.getTradeId().split("-");
            if (idArray[0].equals(type.name()) && Integer.parseInt(idArray[1]) > counter){
                counter = Integer.parseInt(idArray[1]);
            }
        }

        return type.name() + "-" + String.format("%03d", counter == 0 ? 1 : counter + 1);
    }

    public static boolean validateQuantity(double quantity){return quantity > 0;}

    public static boolean validatePrice(BigDecimal price){return price.compareTo(BigDecimal.ZERO) > 0;}

    public static boolean validateDirection(Direction direction){return direction == Direction.BUY || direction == Direction.SELL;}

    public static boolean validateTicker(String ticker){return !ticker.isEmpty();}
}
