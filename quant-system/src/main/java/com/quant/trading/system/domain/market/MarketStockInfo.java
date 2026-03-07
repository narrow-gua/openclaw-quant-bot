package com.quant.trading.system.domain.market;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.quant.trading.common.annotation.Excel;
import com.quant.trading.common.core.domain.BaseEntity;

/**
 * 全量股票信息 market_stock_info
 * 
 * @author narrow
 */
public class MarketStockInfo extends BaseEntity
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

    /** 交易所 */
    @Excel(name = "交易所")
    private String exchange;

    /** 股票类型 */
    @Excel(name = "股票类型", readConverterExp = "STOCK=普通股,ETF=ETF,ADR=ADR")
    private String stockType;

    /** 所属板块 */
    private String sector;

    /** 所属行业 */
    private String industry;

    /** 市值 */
    private BigDecimal marketCap;

    /** 市盈率 */
    private BigDecimal pe;

    /** 市净率 */
    private BigDecimal pb;

    /** 股息率 */
    private BigDecimal dividendYield;

    /** 52周最高 */
    private BigDecimal week52High;

    /** 52周最低 */
    private BigDecimal week52Low;

    /** 当前价格 */
    private BigDecimal currentPrice;

    /** 价格变化 */
    private BigDecimal change;

    /** 涨跌幅 */
    private BigDecimal changePercent;

    /** 成交量 */
    private Long volume;

    /** 平均成交量 */
    private Long avgVolume;

    /** Beta值 */
    private BigDecimal beta;

    /** 波动率 */
    private BigDecimal volatility;

    /** 状态 (ACTIVE=上市, DELISTED=退市, SUSPENDED=暂停) */
    private String status;

    /** 上市日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date listingDate;

    /** 最后更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastUpdateTime;

    // Getters and Setters

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

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public String getStockType() {
        return stockType;
    }

    public void setStockType(String stockType) {
        this.stockType = stockType;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public BigDecimal getMarketCap() {
        return marketCap;
    }

    public void setMarketCap(BigDecimal marketCap) {
        this.marketCap = marketCap;
    }

    public BigDecimal getPe() {
        return pe;
    }

    public void setPe(BigDecimal pe) {
        this.pe = pe;
    }

    public BigDecimal getPb() {
        return pb;
    }

    public void setPb(BigDecimal pb) {
        this.pb = pb;
    }

    public BigDecimal getDividendYield() {
        return dividendYield;
    }

    public void setDividendYield(BigDecimal dividendYield) {
        this.dividendYield = dividendYield;
    }

    public BigDecimal getWeek52High() {
        return week52High;
    }

    public void setWeek52High(BigDecimal week52High) {
        this.week52High = week52High;
    }

    public BigDecimal getWeek52Low() {
        return week52Low;
    }

    public void setWeek52Low(BigDecimal week52Low) {
        this.week52Low = week52Low;
    }

    public BigDecimal getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(BigDecimal currentPrice) {
        this.currentPrice = currentPrice;
    }

    public BigDecimal getChange() {
        return change;
    }

    public void setChange(BigDecimal change) {
        this.change = change;
    }

    public BigDecimal getChangePercent() {
        return changePercent;
    }

    public void setChangePercent(BigDecimal changePercent) {
        this.changePercent = changePercent;
    }

    public Long getVolume() {
        return volume;
    }

    public void setVolume(Long volume) {
        this.volume = volume;
    }

    public Long getAvgVolume() {
        return avgVolume;
    }

    public void setAvgVolume(Long avgVolume) {
        this.avgVolume = avgVolume;
    }

    public BigDecimal getBeta() {
        return beta;
    }

    public void setBeta(BigDecimal beta) {
        this.beta = beta;
    }

    public BigDecimal getVolatility() {
        return volatility;
    }

    public void setVolatility(BigDecimal volatility) {
        this.volatility = volatility;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getListingDate() {
        return listingDate;
    }

    public void setListingDate(Date listingDate) {
        this.listingDate = listingDate;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }
}
