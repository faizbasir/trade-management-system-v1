package src.service;

import src.enums.Direction;
import src.enums.TradeStatus;
import src.enums.TradeType;
import src.exception.InvalidTradeException;
import src.model.FXTrade;
import src.model.Trade;
import src.store.TradeStore;
import src.util.DateUtil;
import src.util.TradeUtil;

import java.util.List;

public class TradeService {
    private final TradeStore tradeStore;

    public TradeService(TradeStore tradeStore){
        this.tradeStore = tradeStore;
    }

    public void createTrade(Trade trade, TradeType tradeType){
        boolean validTrade = trade.validate();

        if (validTrade){
            String tradeId = TradeUtil.generateTradeID(getAllTrades(), tradeType);
            trade.setTradeDateTime(DateUtil.setTradeDate());
            trade.setTradeId(tradeId);
            tradeStore.addTrades(trade);
        } else {
            throw new InvalidTradeException("This is not a valid trade");
        }
    }

    public Trade getTradeById(String tradeId){
        return tradeStore.getTradeById(tradeId);
    }

    public void removeTrade(String tradeId){
        if (tradeStore.getTradeById(tradeId).getStatus() == TradeStatus.FILLED){
            throw new InvalidTradeException("Trade cannot be removed as it is filled");
        } else {
            tradeStore.removeTrade(tradeId);
        }
    }

    public List<Trade> getAllTrades(){
        return tradeStore.getAllTrades();
    }

    public void fillTrade(String tradeId){
        Trade foundTrade = tradeStore.getTradeById(tradeId);
        if (foundTrade.getStatus() == TradeStatus.FILLED){
            throw new InvalidTradeException("Trade already filled");
        } else {
            foundTrade.setStatus(TradeStatus.FILLED);
            tradeStore.updateTrade(foundTrade, tradeId);
        }
    }
}
