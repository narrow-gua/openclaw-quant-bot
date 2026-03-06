package com.quant.trading.system.domain.trading;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.quant.trading.common.annotation.Excel;
import com.quant.trading.common.core.domain.BaseEntity;

/**
 * 股票关注列表 trading_stock
 * 
 * @author narrow
 */
public class TradingStock extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 股票ID */
    private Long stockId;

    /** 股票代码 */
    @Excel(name = "股票代码")
    private String symbol;

    /** 股票名称 */
    @Excel(name = "股票名称")
    private String name;

    /** 股票类型 */
    @Excel(name = "股票类型", readConverterExp = "GROWTH=成长,VALUE=价值,INDEX=指数,ETF=ETF,OPTIONS=期权")
    private String type;

    /** 当前价格 */
    @Excel(name = "当前价格")
    private BigDecimal currentPrice;

    /** 目标价格 */
    @Excel(name = "目标价格")
    private BigDecimal targetPrice;

    /** 止损价格 */
    @Excel(name = "止损价格")
    private BigDecimal stopLossPrice;

    /** 备注 */
    private String remark;

    /** 状态 */
    @Excel(name = "状态", readConverterExp = "WATCHING=关注,BUY_SIGNAL=买入信号,HOLDING=持有,SELL_SIGNAL=卖出信号,SOLD=已卖出")
    private String status;

    public Long getStockId() {
        return stockId;
    }

    public void setStockId(Long stockId) {
        this.stockId = stockId;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigDecimal getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(BigDecimal currentPrice) {
        this.currentPrice = currentPrice;
    }

    public BigDecimal getTargetPrice() {
        return targetPrice;
    }

    public void setTargetPrice(BigDecimal targetPrice) {
        this.targetPrice = targetPrice;
    }

    public BigDecimal getStopLossPrice() {
        return stopLossPrice;
    }

    public void setStopLossPrice(BigDecimal stopLossPrice) {
        this.stopLossPrice = stopLossPrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // Override remark to use different column name
    private String remark;

    @Override
    public String getRemark() {
        return remark;
    }

    @Override
    public void setRemark(String remark) {
        this.remark = remark;
    }
}
