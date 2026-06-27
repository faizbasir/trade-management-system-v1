package src.util;

import src.enums.TradeStatus;
import src.enums.TradeType;
import src.model.Trade;

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

    public static void fillTrade(List<Trade> tradeRepo, String tradeId){
        for (Trade trade : tradeRepo) {
            if (tradeId.equals(trade.getTradeId())){
                trade.setStatus(TradeStatus.FILLED);
            }
        }
    }

    public static void removeTrade(List<Trade> tradeRepo, String tradeId){
        for (Trade trade : tradeRepo){
            if (trade.getTradeId().equals(tradeId) && trade.getStatus() != TradeStatus.FILLED){
                tradeRepo.remove(trade);
            } else {
                throw new IllegalArgumentException("unable to remove trade");
            }
        }
    }

    public static Trade getTradeById(List<Trade> tradeRepo, String tradeId){
        for (Trade trade: tradeRepo){
            if (trade.getTradeId().equals(tradeId)){
                return trade;
            }
        }
        throw new IllegalArgumentException("Trade not found");
    }
}
