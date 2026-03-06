package com.quant.trading.system.service.trading;

import java.util.List;
import com.quant.trading.system.domain.trading.TradingBroker;

/**
 * 券商配置Service接口
 * 
 * @author narrow
 */
public interface ITradingBrokerService
{
    /**
     * 查询券商
     * 
     * @param brokerId 券商ID
     * @return 券商
     */
    public TradingBroker selectTradingBrokerById(Long brokerId);

    /**
     * 查询券商列表
     * 
     * @param tradingBroker 券商
     * @return 券商集合
     */
    public List<TradingBroker> selectTradingBrokerList(TradingBroker tradingBroker);

    /**
     * 根据代码查询券商
     * 
     * @param brokerCode 券商代码
     * @return 券商
     */
    public TradingBroker selectTradingBrokerByCode(String brokerCode);

    /**
     * 新增券商
     * 
     * @param tradingBroker 券商
     * @return 结果
     */
    public int insertTradingBroker(TradingBroker tradingBroker);

    /**
     * 修改券商
     * 
     * @param tradingBroker 券商
     * @return 结果
     */
    public int updateTradingBroker(TradingBroker tradingBroker);

    /**
     * 批量删除券商
     * 
     * @param brokerIds 需要删除的券商ID
     * @return 结果
     */
    public int deleteTradingBrokerByIds(Long[] brokerIds);

    /**
     * 删除券商信息
     * 
     * @param brokerId 券商ID
     * @return 结果
     */
    public int deleteTradingBrokerById(Long brokerId);

    /**
     * 校验券商代码是否唯一
     * 
     * @param tradingBroker 券商
     * @return 结果
     */
    public boolean checkBrokerCodeUnique(TradingBroker tradingBroker);
}
