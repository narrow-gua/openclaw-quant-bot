package com.quant.trading.system.domain.trading;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.quant.trading.common.annotation.Excel;
import com.quant.trading.common.core.domain.BaseEntity;

/**
 * 持仓记录 trading_position
 * 
 * @author narrow
 */
public class TradingPosition extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 持仓ID */
    private Long positionId;

    /** 股票ID */
    private Long stockId;

    /** 股票代码 */
    @Excel(name = "股票代码")
    private String symbol;

    /** 股票名称 */
    @Excel(name = "股票名称")
    private String stockName;

    /** 券商ID */
    @Excel(name = "券商")
    private Long brokerId;

    /** 券商名称 */
    @Excel(name = "券商名称")
    private String brokerName;

    /** 持仓类型 */
    @Excel(name = "持仓类型", readConverterExp = "SIMULATED=模拟盘,REAL=实盘")
    private String positionType;

    /** 持股数量 */
    @Excel(name = "持股数量")
    private Integer quantity;

    /** 入手平均价格 */
    @Excel(name = "入手平均价格")
    private BigDecimal avgPrice;

    /** 当前价格 */
    @Excel(name = "当前价格")
    private BigDecimal currentPrice;

    /** 止损价格 */
    @Excel(name = "止损价格")
    private BigDecimal stopLossPrice;

    /** 止盈价格 */
    @Excel(name = "止盈价格")
    private BigDecimal takeProfitPrice;

    /** 总成本 */
    @Excel(name = "总成本")
    private BigDecimal totalCost;

    /** 当前市值 */
    @Excel(name = "当前市值")
    private BigDecimal marketValue;

    /** 浮动盈亏 */
    @Excel(name = "浮动盈亏")
    private BigDecimal profitLoss;

    /** 盈亏比例(%) */
    @Excel(name = "盈亏比例(%)")
    private BigDecimal profitLossRate;

    /** 备注 */
    private String remark;

    /** 状态 */
    @Excel(name = "状态", readConverterExp = "OPEN=持仓中,CLOSED=已平仓,STOPPED_OUT=止损出局")
    private String status;

    /** 建仓时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "建仓时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date openTime;

    /** 平仓时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "平仓时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date closeTime;

    public Long getPositionId() {
        return positionId;
    }

    public void setPositionId(Long positionId) {
        this.positionId = positionId;
    }

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

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public Long getBrokerId() {
        return brokerId;
    }

    public void setBrokerId(Long brokerId) {
        this.brokerId = brokerId;
    }

    public String getBrokerName() {
        return brokerName;
    }

    public void setBrokerName(String brokerName) {
        this.brokerName = brokerName;
    }

    public String getPositionType() {
        return positionType;
    }

    public void setPositionType(String positionType) {
        this.positionType = positionType;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getAvgPrice() {
        return avgPrice;
    }

    public void setAvgPrice(BigDecimal avgPrice) {
        this.avgPrice = avgPrice;
    }

    public BigDecimal getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(BigDecimal currentPrice) {
        this.currentPrice = currentPrice;
    }

    public BigDecimal getStopLossPrice() {
        return stopLossPrice;
    }

    public void setStopLossPrice(BigDecimal stopLossPrice) {
        this.stopLossPrice = stopLossPrice;
    }

    public BigDecimal getTakeProfitPrice() {
        return takeProfitPrice;
    }

    public void setTakeProfitPrice(BigDecimal takeProfitPrice) {
        this.takeProfitPrice = takeProfitPrice;
    }

    public BigDecimal getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(BigDecimal totalCost) {
        this.totalCost = totalCost;
    }

    public BigDecimal getMarketValue() {
        return marketValue;
    }

    public void setMarketValue(BigDecimal marketValue) {
        this.marketValue = marketValue;
    }

    public BigDecimal getProfitLoss() {
        return profitLoss;
    }

    public void setProfitLoss(BigDecimal profitLoss) {
        this.profitLoss = profitLoss;
    }

    public BigDecimal getProfitLossRate() {
        return profitLossRate;
    }

    public void setProfitLossRate(BigDecimal profitLossRate) {
        this.profitLossRate = profitLossRate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getOpenTime() {
        return openTime;
    }

    public void setOpenTime(Date openTime) {
        this.openTime = openTime;
    }

    public Date getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(Date closeTime) {
        this.closeTime = closeTime;
    }

    @Override
    public String getRemark() {
        return remark;
    }

    @Override
    public void setRemark(String remark) {
        this.remark = remark;
    }
}
