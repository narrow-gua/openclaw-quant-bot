package com.quant.trading.system.mapper.trading;

import java.util.List;
import com.quant.trading.system.domain.trading.TradingStock;
import org.apache.ibatis.annotations.Param;

/**
 * 股票关注Mapper接口
 * 
 * @author narrow
 */
public interface TradingStockMapper
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
     * 删除股票
     * 
     * @param stockId 股票ID
     * @return 结果
     */
    public int deleteTradingStockById(Long stockId);

    /**
     * 批量删除股票
     * 
     * @param stockIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteTradingStockByIds(Long[] stockIds);

    /**
     * 检查股票代码是否唯一
     * 
     * @param symbol 股票代码
     * @return 结果
     */
    public int checkSymbolUnique(@Param("symbol") String symbol);
}
