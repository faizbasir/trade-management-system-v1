package src;

import src.enums.Direction;
import src.enums.TradeStatus;
import src.enums.TradeType;
import src.model.EquityTrade;
import src.model.FXTrade;
import src.model.Trade;
import src.util.CcyUtil;
import src.util.DateUtil;
import src.util.TradeUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static List<Trade> tradeRepo = new ArrayList<>();

    public static void main(String[] args) {

        // Creating dummy trade repository

        // Create base trade
        createTrade("T001", 10000);

        // Create FX Trade
        BigDecimal rate = new BigDecimal("1.70");
        FXTrade FXTrade1 = createFXTrade("", 100, "EUR/USD", rate, Direction.SELL);
        storeTrade(FXTrade1);

        FXTrade FXTrade2 = createFXTrade("", 100, "EUR/USD", rate, Direction.SELL);
        storeTrade(FXTrade2);

        //Create Equity Trade
        BigDecimal price = new BigDecimal("100");
        EquityTrade equityTrade1 = createEquityTrade("", 1000, "AAPL", price, "NASDAQ");
        storeTrade(equityTrade1);

        //Print out the trade repo
//        for (Trade trade : tradeRepo){
//            System.out.println(trade.toString());
//        }

        // Fill FX trade
        TradeUtil.fillTrade(tradeRepo, "FX-001");
        System.out.println(tradeRepo);

        // Get trade by id
        Trade foundTrade = TradeUtil.getTradeById(tradeRepo, "FX-001");
        System.out.println("Trade found: " + foundTrade);

        // Trade Removal
        TradeUtil.removeTrade(tradeRepo, "FX-002");
        System.out.println("After removal: ");
        System.out.println(tradeRepo);

        // Remove Filled trade
        TradeUtil.removeTrade(tradeRepo, "FX-001");
        System.out.println("After removal: ");
        System.out.println(tradeRepo);
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

    public static FXTrade createFXTrade(String traderId, double quantity, String ccyPair, BigDecimal rate, Direction direction){
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
            String tradeId = TradeUtil.generateTradeID(tradeRepo, TradeType.FX);
            String tradeDateTime = DateUtil.setTradeDate();
            FXTrade fxTrade = new FXTrade(tradeId, traderId, quantity, tradeDateTime, TradeStatus.NEW, ccyPair, rate, direction);
//            System.out.println(fxTrade);
//            System.out.println(fxTrade.calculateNotional());
            return fxTrade;
        } else {
            throw new IllegalArgumentException("Please input valid currency pair");
        }
    }

    public static EquityTrade createEquityTrade(String traderId, double quantity, String ticker, BigDecimal price, String exchange){
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
        String tradeId = TradeUtil.generateTradeID(tradeRepo, TradeType.EQD);
        String tradeDateTime = DateUtil.setTradeDate();
        EquityTrade newTrade = new EquityTrade(tradeId, traderId, quantity, tradeDateTime, TradeStatus.NEW, ticker, price, exchange);
//        System.out.println(newTrade);
//        System.out.println(newTrade.calculateNotional());
        return newTrade;
    }

    public static void storeTrade(Trade trade){
        int counter = 0;
        for (Trade tradeObj : tradeRepo){
            if (trade.getTradeId().equals(tradeObj.getTradeId())){
                counter++;
            }
        }
        if (counter == 0){
            tradeRepo.add(trade);
        } else {
            throw new IllegalArgumentException("Duplicate trade id found");
        }
    }
}