package com.quant.trading.system.service.trading.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.quant.trading.system.mapper.trading.TradingStockMapper;
import com.quant.trading.system.domain.trading.TradingStock;
import com.quant.trading.system.service.trading.ITradingStockService;
import com.quant.trading.common.utils.DateUtils;
import com.quant.trading.common.utils.StringUtils;

/**
 * 股票关注Service业务层处理
 * 
 * @author narrow
 */
@Service
public class TradingStockServiceImpl implements ITradingStockService
{
    @Autowired
    private TradingStockMapper tradingStockMapper;

    /**
     * 查询股票
     * 
     * @param stockId 股票ID
     * @return 股票
     */
    @Override
    public TradingStock selectTradingStockById(Long stockId)
    {
        return tradingStockMapper.selectTradingStockById(stockId);
    }

    /**
     * 查询股票列表
     * 
     * @param tradingStock 股票
     * @return 股票
     */
    @Override
    public List<TradingStock> selectTradingStockList(TradingStock tradingStock)
    {
        return tradingStockMapper.selectTradingStockList(tradingStock);
    }

    /**
     * 根据代码查询股票
     * 
     * @param symbol 股票代码
     * @return 股票
     */
    @Override
    public TradingStock selectTradingStockBySymbol(String symbol)
    {
        return tradingStockMapper.selectTradingStockBySymbol(symbol);
    }

    /**
     * 新增股票
     * 
     * @param tradingStock 股票
     * @return 结果
     */
    @Override
    public int insertTradingStock(TradingStock tradingStock)
    {
        tradingStock.setCreateTime(DateUtils.getNowDate());
        return tradingStockMapper.insertTradingStock(tradingStock);
    }

    /**
     * 修改股票
     * 
     * @param tradingStock 股票
     * @return 结果
     */
    @Override
    public int updateTradingStock(TradingStock tradingStock)
    {
        tradingStock.setUpdateTime(DateUtils.getNowDate());
        return tradingStockMapper.updateTradingStock(tradingStock);
    }

    /**
     * 批量删除股票
     * 
     * @param stockIds 需要删除的股票ID
     * @return 结果
     */
    @Override
    public int deleteTradingStockByIds(Long[] stockIds)
    {
        return tradingStockMapper.deleteTradingStockByIds(stockIds);
    }

    /**
     * 删除股票信息
     * 
     * @param stockId 股票ID
     * @return 结果
     */
    @Override
    public int deleteTradingStockById(Long stockId)
    {
        return tradingStockMapper.deleteTradingStockById(stockId);
    }

    /**
     * 校验股票代码是否唯一
     * 
     * @param tradingStock 股票
     * @return 结果
     */
    @Override
    public boolean checkSymbolUnique(TradingStock tradingStock)
    {
        Long stockId = StringUtils.isNull(tradingStock.getStockId()) ? -1L : tradingStock.getStockId();
        TradingStock info = tradingStockMapper.selectTradingStockBySymbol(tradingStock.getSymbol());
        if (StringUtils.isNotNull(info) && info.getStockId().longValue() != stockId.longValue())
        {
            return false;
        }
        return true;
    }
}
