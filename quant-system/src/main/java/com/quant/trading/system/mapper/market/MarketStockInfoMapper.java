package com.quant.trading.system.mapper.market;

import java.util.List;
import com.quant.trading.system.domain.market.MarketStockInfo;
import org.apache.ibatis.annotations.Param;

/**
 * 全量股票信息Mapper接口
 * 
 * @author narrow
 */
public interface MarketStockInfoMapper
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
    public int batchInsertMarketStockInfo(@Param("list") List<MarketStockInfo> list);

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
    public int checkSymbolExists(String symbol);

    /**
     * 获取股票总数
     */
    public int selectMarketStockInfoCount();

    /**
     * 标记退市
     */
    public int updateStockStatusDelisted(String symbol);
}
