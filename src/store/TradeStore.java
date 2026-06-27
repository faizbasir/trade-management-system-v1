package src.store;

import src.enums.TradeStatus;
import src.model.Trade;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TradeStore {
    private final Map<String, Trade> trades;

    public TradeStore(){
        this.trades = new HashMap<>();
    }

    public void addTrades(Trade trade){
        if (trades.containsKey(trade.getTradeId())){
            throw new IllegalArgumentException("Duplicate trade id found");
        } else {
            trades.put(trade.getTradeId(), trade);
        }
    }

    public void removeTrade(String tradeId){
        if (!trades.containsKey(tradeId)){
            throw new IllegalArgumentException("Trade not found");
        }
        trades.remove(tradeId);
    }

    public List<Trade> getAllTrades(){
        return new ArrayList<Trade>(trades.values());
    }

    public Trade getTradeById(String tradeId){
        if (trades.containsKey(tradeId)){
            return trades.get(tradeId);
        } else {
            throw new IllegalArgumentException("Trade not found");
        }
    }

    public void updateTrade(Trade trade, String tradeId){
        if (trades.containsKey(tradeId)){
            trades.put(tradeId, trade);
        }
    }
}
