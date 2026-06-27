package src.model;

import src.enums.Direction;
import src.enums.TradeStatus;

import java.math.BigDecimal;

public class FXTrade extends Trade{
    private String ccyPair;
    private BigDecimal rate;
    private Direction direction;

    public FXTrade(String tradeId, String traderId, double quantity, String tradeDateTime, TradeStatus status, String ccyPair, BigDecimal rate, Direction direction) {
        super(tradeId, traderId, quantity, tradeDateTime, status);
        this.ccyPair = ccyPair;
        this.rate = rate;
        this.direction = direction;
    }

    public String getCcyPair() {
        return ccyPair;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public Direction getDirection() {
        return direction;
    }

    public BigDecimal calculateNotional(){
        return this.rate.multiply(BigDecimal.valueOf(super.getQuantity()));
    }

    @Override
    public String toString() {
        return "FXTrade{" +
                "ccyPair='" + ccyPair + '\'' +
                ", rate=" + rate +
                ", direction=" + direction + ',' +
                super.toString() + "} ";
    }
}
