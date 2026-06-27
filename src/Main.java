package src;

import src.enums.Direction;
import src.enums.TradeStatus;
import src.model.EquityTrade;
import src.model.FXTrade;
import src.model.Trade;
import src.util.CcyUtil;
import src.util.DateUtil;

import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {

        // Create base trade
        createTrade("T001", 10000);

        // Create FX Trade
        BigDecimal rate = new BigDecimal("1.70");
        createFXTrade("", 100, "EUR/USD", rate, Direction.SELL);

        //Create Equity Trade
        BigDecimal price = new BigDecimal("100");
        createEquityTrade("", 1000, "AAPL", price, "NASDAQ");
    }

    public static void createTrade(String traderId, double quantity){
        String tradeDateTime = DateUtil.setTradeDate();
        if (quantity <= 0){
            throw new IllegalArgumentException("Quantity must be more than 0");
        }
        String tradeId = "TR-001";
        Trade trade1 = new Trade(tradeId, traderId, quantity, tradeDateTime, TradeStatus.NEW);
        System.out.println(trade1);
    }

    public static void createFXTrade(String traderId, double quantity, String ccyPair, BigDecimal rate, Direction direction){
        if (!ccyPair.isEmpty() && CcyUtil.validateCcyPair(ccyPair)) {
            if (quantity <= 0){
                throw new IllegalArgumentException("Quantity must be more than 0");
            }

            if (rate.compareTo(BigDecimal.ZERO) <= 0) {
                throw new IllegalArgumentException("Rate has to be bigger than 0");
            }

            if (direction == Direction.NULL) {
                throw new IllegalArgumentException("Direction can only be buy or sell");
            }

            // Refine tradeID generation when trade storage is developed
            String tradeId = "FX-001";
            String tradeDateTime = DateUtil.setTradeDate();
            FXTrade fxTrade1 = new FXTrade(tradeId, traderId, quantity, tradeDateTime, TradeStatus.NEW, ccyPair, rate, direction);
            System.out.println(fxTrade1);
            System.out.println(fxTrade1.calculateNotional());
        } else {
            throw new IllegalArgumentException("Please input valid currency pair");
        }
    }

    public static void createEquityTrade(String traderId, double quantity, String ticker, BigDecimal price, String exchange){
        if (quantity <= 0){
            throw new IllegalArgumentException("Quantity must be more than 0");
        }
        if (price.compareTo(BigDecimal.ZERO) <= 0){
            throw new IllegalArgumentException("Price must be more than 0");
        }
        if (ticker.isEmpty()){
            throw new IllegalArgumentException("Ticker cannot be empty");
        }
        // Refine trade id generation when trade storage is implemented
        String tradeId = "EQD-001";
        String tradeDateTime = DateUtil.setTradeDate();
        EquityTrade newTrade = new EquityTrade(tradeId, traderId, quantity, tradeDateTime, TradeStatus.NEW, ticker, price, exchange);
        System.out.println(newTrade);
        System.out.println(newTrade.calculateNotional());
    }
}