package com.quant.trading.system.mapper.trading;

import java.util.List;
import com.quant.trading.system.domain.trading.TradingPosition;
import org.apache.ibatis.annotations.Param;

/**
 * 持仓记录Mapper接口
 * 
 * @author narrow
 */
public interface TradingPositionMapper
{
    /**
     * 查询持仓
     * 
     * @param positionId 持仓ID
     * @return 持仓
     */
    public TradingPosition selectTradingPositionById(Long positionId);

    /**
     * 查询持仓列表
     * 
     * @param tradingPosition 持仓
     * @return 持仓集合
     */
    public List<TradingPosition> selectTradingPositionList(TradingPosition tradingPosition);

    /**
     * 查询某股票的持仓
     * 
     * @param symbol 股票代码
     * @param brokerId 券商ID
     * @param positionType 持仓类型
     * @return 持仓
     */
    public TradingPosition selectTradingPositionBySymbol(@Param("symbol") String symbol, @Param("brokerId") Long brokerId, @Param("positionType") String positionType);

    /**
     * 查询所有持仓中记录
     * 
     * @return 持仓集合
     */
    public List<TradingPosition> selectOpenPositions();

    /**
     * 新增持仓
     * 
     * @param tradingPosition 持仓
     * @return 结果
     */
    public int insertTradingPosition(TradingPosition tradingPosition);

    /**
     * 修改持仓
     * 
     * @param tradingPosition 持仓
     * @return 结果
     */
    public int updateTradingPosition(TradingPosition tradingPosition);

    /**
     * 删除持仓
     * 
     * @param positionId 持仓ID
     * @return 结果
     */
    public int deleteTradingPositionById(Long positionId);

    /**
     * 批量删除持仓
     * 
     * @param positionIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteTradingPositionByIds(Long[] positionIds);
}
