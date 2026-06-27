package src;

import src.enums.Direction;
import src.enums.TradeStatus;
import src.enums.TradeType;
import src.model.EquityTrade;
import src.model.FXTrade;
import src.model.Trade;
import src.service.TradeService;
import src.store.TradeStore;
import src.util.CcyUtil;
import src.util.DateUtil;
import src.util.TradeUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        // Creating trade repository
        TradeStore tradeStore = new TradeStore();

        // Creating trade service
        TradeService tradeService = new TradeService(tradeStore);

        // Create FX Trade
        BigDecimal rate = new BigDecimal("1.70");
        FXTrade fxTrade1 = new FXTrade("", "T001", 100, "", TradeStatus.NEW, "EUR/USD", rate, Direction.BUY);
        tradeService.createTrade(fxTrade1, TradeType.FX);

        FXTrade fxTrade2 = new FXTrade("", "T001", 100, "", TradeStatus.NEW, "EUR/USD", rate, Direction.BUY);
        tradeService.createTrade(fxTrade2, TradeType.FX);

        //Create Equity Trade
        BigDecimal price = new BigDecimal("100");
        EquityTrade equityTrade1 = new EquityTrade("", "T001", 100, "", TradeStatus.NEW, "AAPL", price, "NASDAQ");
        tradeService.createTrade(equityTrade1, TradeType.EQD);

        // Fill FX trade
        tradeService.fillTrade("FX-001");
        System.out.println(tradeService.getAllTrades());

        // Get trade by id
        Trade foundTrade = tradeService.getTradeById("FX-001");
        System.out.println(foundTrade);

        // Trade Removal
        tradeService.removeTrade("FX-002");
        System.out.println("After removal: ");
        System.out.println(tradeService.getAllTrades());

        // Remove Filled trade
        tradeService.removeTrade("FX-001");
        System.out.println("After removal: ");
        System.out.println(tradeService.getAllTrades());
    }
}