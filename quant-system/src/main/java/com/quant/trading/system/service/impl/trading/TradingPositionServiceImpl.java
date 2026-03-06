package com.quant.trading.system.service.trading.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.quant.trading.system.mapper.trading.TradingPositionMapper;
import com.quant.trading.system.domain.trading.TradingPosition;
import com.quant.trading.system.service.trading.ITradingPositionService;
import com.quant.trading.common.utils.DateUtils;
import com.quant.trading.common.utils.StringUtils;

/**
 * 持仓记录Service业务层处理
 * 
 * @author narrow
 */
@Service
public class TradingPositionServiceImpl implements ITradingPositionService
{
    @Autowired
    private TradingPositionMapper tradingPositionMapper;

    /**
     * 查询持仓
     * 
     * @param positionId 持仓ID
     * @return 持仓
     */
    @Override
    public TradingPosition selectTradingPositionById(Long positionId)
    {
        return tradingPositionMapper.selectTradingPositionById(positionId);
    }

    /**
     * 查询持仓列表
     * 
     * @param tradingPosition 持仓
     * @return 持仓
     */
    @Override
    public List<TradingPosition> selectTradingPositionList(TradingPosition tradingPosition)
    {
        return tradingPositionMapper.selectTradingPositionList(tradingPosition);
    }

    /**
     * 查询某股票的持仓
     * 
     * @param symbol 股票代码
     * @param brokerId 券商ID
     * @param positionType 持仓类型
     * @return 持仓
     */
    @Override
    public TradingPosition selectTradingPositionBySymbol(String symbol, Long brokerId, String positionType)
    {
        return tradingPositionMapper.selectTradingPositionBySymbol(symbol, brokerId, positionType);
    }

    /**
     * 查询所有持仓中记录
     * 
     * @return 持仓集合
     */
    @Override
    public List<TradingPosition> selectOpenPositions()
    {
        return tradingPositionMapper.selectOpenPositions();
    }

    /**
     * 新增持仓
     * 
     * @param tradingPosition 持仓
     * @return 结果
     */
    @Override
    public int insertTradingPosition(TradingPosition tradingPosition)
    {
        // 计算总成本
        if (tradingPosition.getQuantity() != null && tradingPosition.getAvgPrice() != null)
        {
            tradingPosition.setTotalCost(tradingPosition.getAvgPrice().multiply(new BigDecimal(tradingPosition.getQuantity())));
            tradingPosition.setMarketValue(tradingPosition.getTotalCost());
            tradingPosition.setProfitLoss(BigDecimal.ZERO);
            tradingPosition.setProfitLossRate(BigDecimal.ZERO);
        }
        tradingPosition.setCreateTime(DateUtils.getNowDate());
        return tradingPositionMapper.insertTradingPosition(tradingPosition);
    }

    /**
     * 修改持仓
     * 
     * @param tradingPosition 持仓
     * @return 结果
     */
    @Override
    public int updateTradingPosition(TradingPosition tradingPosition)
    {
        // 重新计算盈亏
        if (tradingPosition.getCurrentPrice() != null)
        {
            calculateProfitLoss(tradingPosition, tradingPosition.getCurrentPrice());
        }
        tradingPosition.setUpdateTime(DateUtils.getNowDate());
        return tradingPositionMapper.updateTradingPosition(tradingPosition);
    }

    /**
     * 批量删除持仓
     * 
     * @param positionIds 需要删除的持仓ID
     * @return 结果
     */
    @Override
    public int deleteTradingPositionByIds(Long[] positionIds)
    {
        return tradingPositionMapper.deleteTradingPositionByIds(positionIds);
    }

    /**
     * 删除持仓信息
     * 
     * @param positionId 持仓ID
     * @return 结果
     */
    @Override
    public int deleteTradingPositionById(Long positionId)
    {
        return tradingPositionMapper.deleteTradingPositionById(positionId);
    }

    /**
     * 计算并更新盈亏
     * 
     * @param position 持仓
     * @param currentPrice 当前价格
     */
    @Override
    public void calculateProfitLoss(TradingPosition position, java.math.BigDecimal currentPrice)
    {
        if (position.getQuantity() == null || position.getAvgPrice() == null || currentPrice == null)
        {
            return;
        }

        // 当前市值 = 当前价格 * 数量
        BigDecimal marketValue = currentPrice.multiply(new BigDecimal(position.getQuantity()));
        position.setMarketValue(marketValue);

        // 浮动盈亏 = 当前市值 - 总成本
        BigDecimal totalCost = position.getAvgPrice().multiply(new BigDecimal(position.getQuantity()));
        BigDecimal profitLoss = marketValue.subtract(totalCost);
        position.setProfitLoss(profitLoss);

        // 盈亏比例 = 浮动盈亏 / 总成本 * 100
        if (totalCost.compareTo(BigDecimal.ZERO) > 0)
        {
            BigDecimal profitLossRate = profitLoss.divide(totalCost, 4, RoundingMode.HALF_UP).multiply(new BigDecimal("100"));
            position.setProfitLossRate(profitLossRate);
        }

        position.setCurrentPrice(currentPrice);
    }
}
