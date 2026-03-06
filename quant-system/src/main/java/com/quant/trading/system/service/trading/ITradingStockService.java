package com.quant.trading.system.service.trading;

import java.util.List;
import com.quant.trading.system.domain.trading.TradingStock;

/**
 * 股票关注Service接口
 * 
 * @author narrow
 */
public interface ITradingStockService
{
    /**
     * 查询股票
     * 
     * @param stockId 股票ID
     * @return 股票
     */
    public TradingStock selectTradingStockById(Long stockId);

    /**
     * 查询股票列表
     * 
     * @param tradingStock 股票
     * @return 股票集合
     */
    public List<TradingStock> selectTradingStockList(TradingStock tradingStock);

    /**
     * 根据代码查询股票
     * 
     * @param symbol 股票代码
     * @return 股票
     */
    public TradingStock selectTradingStockBySymbol(String symbol);

    /**
     * 新增股票
     * 
     * @param tradingStock 股票
     * @return 结果
     */
    public int insertTradingStock(TradingStock tradingStock);

    /**
     * 修改股票
     * 
     * @param tradingStock 股票
     * @return 结果
     */
    public int updateTradingStock(TradingStock tradingStock);

    /**
     * 批量删除股票
     * 
     * @param stockIds 需要删除的股票ID
     * @return 结果
     */
    public int deleteTradingStockByIds(Long[] stockIds);

    /**
     * 删除股票信息
     * 
     * @param stockId 股票ID
     * @return 结果
     */
    public int deleteTradingStockById(Long stockId);

    /**
     * 校验股票代码是否唯一
     * 
     * @param tradingStock 股票
     * @return 结果
     */
    public boolean checkSymbolUnique(TradingStock tradingStock);
}
