package src;

import src.enums.TradeStatus;
import src.model.Trade;
import src.util.DateUtil;

public class Main {
    public static void main(String[] args) {

        createTrade("FX-001", "", 100);
        // createTrade("", "", 100);
        // createTrade("FX-001", "", -100);

    }

    public static void createTrade(String tradeId, String traderId, double quantity){

        if (tradeId == null || tradeId.isEmpty()){
            throw new IllegalArgumentException("Trade ID cannot be empty");
        }

        if (quantity <= 0){
            throw new IllegalArgumentException("Quantity must be more than 0");
        }

        String tradeDateTime = DateUtil.setTradeDate();

        Trade trade1 = new Trade(tradeId, traderId, quantity, tradeDateTime, TradeStatus.NEW);
        System.out.println(trade1);
    }
}
