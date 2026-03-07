package com.quant.trading.system.service.market;

import java.util.List;
import com.quant.trading.system.domain.market.MarketStockInfo;

/**
 * 全量股票信息Service接口
 * 
 * @author narrow
 */
public interface IMarketStockInfoService
{
    /**
     * 查询股票信息
     */
    public MarketStockInfo selectMarketStockInfoById(Long stockId);

    /**
     * 根据代码查询股票
     */
    public MarketStockInfo selectMarketStockInfoBySymbol(String symbol);

    /**
     * 查询股票列表
     */
    public List<MarketStockInfo> selectMarketStockInfoList(MarketStockInfo marketStockInfo);

    /**
     * 根据交易所查询股票
     */
    public List<MarketStockInfo> selectMarketStockInfoByExchange(String exchange);

    /**
     * 新增股票信息
     */
    public int insertMarketStockInfo(MarketStockInfo marketStockInfo);

    /**
     * 批量新增股票信息
     */
    public int batchInsertMarketStockInfo(List<MarketStockInfo> list);

    /**
     * 更新股票信息
     */
    public int updateMarketStockInfo(MarketStockInfo marketStockInfo);

    /**
     * 删除股票信息
     */
    public int deleteMarketStockInfoById(Long stockId);

    /**
     * 批量删除股票信息
     */
    public int deleteMarketStockInfoByIds(Long[] stockIds);

    /**
     * 检查股票代码是否存在
     */
    public boolean checkSymbolUnique(MarketStockInfo marketStockInfo);

    /**
     * 获取股票总数
     */
    public int selectMarketStockInfoCount();

    /**
     * 同步股票数据
     */
    public int syncStockData(List<MarketStockInfo> list);
}
