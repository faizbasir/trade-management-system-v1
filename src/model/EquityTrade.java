package src.model;

import src.enums.TradeStatus;
import src.util.TradeUtil;

import java.math.BigDecimal;

public class EquityTrade extends Trade{
    private String ticker;
    private BigDecimal price;
    private String exchange;

    public EquityTrade(String tradeId, String traderId, double quantity, String tradeDateTime, TradeStatus status, String ticker, BigDecimal price, String exchange) {
        super(tradeId, traderId, quantity, tradeDateTime, status);
        this.ticker = ticker;
        this.price = price;
        this.exchange = exchange;
    }

    public String getTicker() {
        return ticker;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getExchange() {
        return exchange;
    }

    @Override
    public String toString() {
        return "EquityTrade{" +
                "ticker='" + ticker + '\'' +
                ", price=" + price +
                ", exchange='" + exchange + '\'' + ',' +
                super.toString() + "} ";
    }

    @Override
    public boolean validate() {
        return TradeUtil.validateQuantity(super.getQuantity()) && TradeUtil.validatePrice(this.price) && TradeUtil.validateTicker(this.ticker);
    }

    public BigDecimal calculateNotional(){
        return this.price.multiply(BigDecimal.valueOf(super.getQuantity()));
    }
}
