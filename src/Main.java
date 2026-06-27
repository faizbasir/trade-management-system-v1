package src;

import src.enums.Direction;
import src.enums.TradeStatus;
import src.model.FXTrade;
import src.model.Trade;
import src.util.CcyUtil;
import src.util.DateUtil;

import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {

        BigDecimal rate = new BigDecimal("1.70");
        createTrade("", 100, "EUR/USD", rate, Direction.SELL);
        // createTrade("", "", 100);
        // createTrade("FX-001", "", -100);

    }

    public static void createTrade(String traderId, double quantity, String ccyPair, BigDecimal rate, Direction direction){
        String tradeDateTime = DateUtil.setTradeDate();
        if (quantity <= 0){
            throw new IllegalArgumentException("Quantity must be more than 0");
        }

        if (!ccyPair.isEmpty()){
            if (CcyUtil.validateCcyPair(ccyPair)) {
                if (rate.compareTo(BigDecimal.ZERO) <= 0) {
                    throw new IllegalArgumentException("Rate has to be bigger than 0");
                }

                if (direction == Direction.NULL) {
                    throw new IllegalArgumentException("Direction can only be buy or sell");
                }

                // Refine tradeID generation when trade storage is developed
                String tradeId = "FX-001";
                FXTrade fxTrade1 = new FXTrade(tradeId, traderId, quantity, tradeDateTime, TradeStatus.NEW, ccyPair, rate, direction);
                System.out.println(fxTrade1);
                System.out.println(fxTrade1.calculateNotional());
            } else {
                throw new IllegalArgumentException("Not a valid ccy pair");
            }
        } else {
            String tradeId = "TR-001";
            Trade trade1 = new Trade(tradeId, traderId, quantity, tradeDateTime, TradeStatus.NEW);
            System.out.println(trade1);
        }
    }
}
