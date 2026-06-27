package src.model;

import src.enums.TradeStatus;

import java.math.BigDecimal;

public abstract class Trade {
    private String tradeId;
    private String traderId;
    private double quantity;
    private String tradeDateTime;
    private TradeStatus status;

    public Trade(String tradeId, String traderId, double quantity, String tradeDateTime, TradeStatus status) {
        this.tradeId = tradeId;
        this.traderId = traderId;
        this.quantity = quantity;
        this.tradeDateTime = tradeDateTime;
        this.status = status;
    }

    public String getTradeId() {
        return tradeId;
    }

    public String getTraderId() {
        return traderId;
    }

    public double getQuantity() {
        return quantity;
    }

    public String getTradeDateTime() {
        return tradeDateTime;
    }

    public TradeStatus getStatus() {
        return status;
    }

    public void setStatus(TradeStatus status) {
        this.status = status;
    }

    public void setTradeId(String tradeId) {
        this.tradeId = tradeId;
    }

    public void setTradeDateTime(String tradeDateTime){
        this.tradeDateTime = tradeDateTime;
    }

    @Override
    public String toString() {
        return " tradeId='" + tradeId + '\'' +
                ", traderId='" + traderId + '\'' +
                ", quantity=" + quantity +
                ", tradeDateTime='" + tradeDateTime + '\'' +
                ", status=" + status;
    }

    public abstract boolean validate();

    public abstract BigDecimal calculateNotional();
}
