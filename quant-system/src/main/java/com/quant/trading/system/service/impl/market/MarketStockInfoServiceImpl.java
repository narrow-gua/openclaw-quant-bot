package com.quant.trading.system.service.impl.market;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.quant.trading.system.domain.market.MarketStockInfo;
import com.quant.trading.system.mapper.market.MarketStockInfoMapper;
import com.quant.trading.system.service.market.IMarketStockInfoService;

/**
 * 全量股票信息Service业务层处理
 * 
 * @author narrow
 */
@Service
public class MarketStockInfoServiceImpl implements IMarketStockInfoService
{
    @Autowired
    private MarketStockInfoMapper marketStockInfoMapper;

    /**
     * 查询股票信息
     */
    @Override
    public MarketStockInfo selectMarketStockInfoById(Long stockId)
    {
        return marketStockInfoMapper.selectMarketStockInfoById(stockId);
    }

    /**
     * 根据代码查询股票
     */
    @Override
    public MarketStockInfo selectMarketStockInfoBySymbol(String symbol)
    {
        return marketStockInfoMapper.selectMarketStockInfoBySymbol(symbol);
    }

    /**
     * 查询股票列表
     */
    @Override
    public List<MarketStockInfo> selectMarketStockInfoList(MarketStockInfo marketStockInfo)
    {
        return marketStockInfoMapper.selectMarketStockInfoList(marketStockInfo);
    }

    /**
     * 根据交易所查询股票
     */
    @Override
    public List<MarketStockInfo> selectMarketStockInfoByExchange(String exchange)
    {
        return marketStockInfoMapper.selectMarketStockInfoByExchange(exchange);
    }

    /**
     * 新增股票信息
     */
    @Override
    public int insertMarketStockInfo(MarketStockInfo marketStockInfo)
    {
        return marketStockInfoMapper.insertMarketStockInfo(marketStockInfo);
    }

    /**
     * 批量新增股票信息
     */
    @Override
    public int batchInsertMarketStockInfo(List<MarketStockInfo> list)
    {
        if (list == null || list.isEmpty())
        {
            return 0;
        }
        return marketStockInfoMapper.batchInsertMarketStockInfo(list);
    }

    /**
     * 更新股票信息
     */
    @Override
    public int updateMarketStockInfo(MarketStockInfo marketStockInfo)
    {
        return marketStockInfoMapper.updateMarketStockInfo(marketStockInfo);
    }

    /**
     * 删除股票信息
     */
    @Override
    public int deleteMarketStockInfoById(Long stockId)
    {
        return marketStockInfoMapper.deleteMarketStockInfoById(stockId);
    }

    /**
     * 批量删除股票信息
     */
    @Override
    public int deleteMarketStockInfoByIds(Long[] stockIds)
    {
        return marketStockInfoMapper.deleteMarketStockInfoByIds(stockIds);
    }

    /**
     * 检查股票代码是否存在
     */
    @Override
    public boolean checkSymbolUnique(MarketStockInfo marketStockInfo)
    {
        Long stockId = marketStockInfo.getStockId() == null ? -1L : marketStockInfo.getStockId();
        MarketStockInfo info = marketStockInfoMapper.selectMarketStockInfoBySymbol(marketStockInfo.getSymbol());
        if (info == null || info.getStockId().equals(stockId))
        {
            return true;
        }
        return false;
    }

    /**
     * 获取股票总数
     */
    @Override
    public int selectMarketStockInfoCount()
    {
        return marketStockInfoMapper.selectMarketStockInfoCount();
    }

    /**
     * 同步股票数据 (增量更新)
     * 如果股票存在则更新，不存在则新增
     */
    @Override
    public int syncStockData(List<MarketStockInfo> list)
    {
        if (list == null || list.isEmpty())
        {
            return 0;
        }

        int count = 0;
        List<MarketStockInfo> insertList = new ArrayList<>();
        List<MarketStockInfo> updateList = new ArrayList<>();

        for (MarketStockInfo stock : list)
        {
            MarketStockInfo existing = marketStockInfoMapper.selectMarketStockInfoBySymbol(stock.getSymbol());
            if (existing == null)
            {
                // 新增
                insertList.add(stock);
            }
            else
            {
                // 更新，保留原ID
                stock.setStockId(existing.getStockId());
                updateList.add(stock);
            }
        }

        // 批量新增
        if (!insertList.isEmpty())
        {
            count += marketStockInfoMapper.batchInsertMarketStockInfo(insertList);
        }

        // 逐条更新 (MyBatis批量更新需要特殊处理)
        for (MarketStockInfo stock : updateList)
        {
            count += marketStockInfoMapper.updateMarketStockInfo(stock);
        }

        return count;
    }
}
