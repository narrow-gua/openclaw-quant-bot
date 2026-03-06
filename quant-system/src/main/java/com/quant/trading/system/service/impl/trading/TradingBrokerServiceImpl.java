package com.quant.trading.system.service.trading.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.quant.trading.system.mapper.trading.TradingBrokerMapper;
import com.quant.trading.system.domain.trading.TradingBroker;
import com.quant.trading.system.service.trading.ITradingBrokerService;
import com.quant.trading.common.utils.DateUtils;
import com.quant.trading.common.utils.StringUtils;

/**
 * 券商配置Service业务层处理
 * 
 * @author narrow
 */
@Service
public class TradingBrokerServiceImpl implements ITradingBrokerService
{
    @Autowired
    private TradingBrokerMapper tradingBrokerMapper;

    /**
     * 查询券商
     * 
     * @param brokerId 券商ID
     * @return 券商
     */
    @Override
    public TradingBroker selectTradingBrokerById(Long brokerId)
    {
        return tradingBrokerMapper.selectTradingBrokerById(brokerId);
    }

    /**
     * 查询券商列表
     * 
     * @param tradingBroker 券商
     * @return 券商
     */
    @Override
    public List<TradingBroker> selectTradingBrokerList(TradingBroker tradingBroker)
    {
        return tradingBrokerMapper.selectTradingBrokerList(tradingBroker);
    }

    /**
     * 根据代码查询券商
     * 
     * @param brokerCode 券商代码
     * @return 券商
     */
    @Override
    public TradingBroker selectTradingBrokerByCode(String brokerCode)
    {
        return tradingBrokerMapper.selectTradingBrokerByCode(brokerCode);
    }

    /**
     * 新增券商
     * 
     * @param tradingBroker 券商
     * @return 结果
     */
    @Override
    public int insertTradingBroker(TradingBroker tradingBroker)
    {
        tradingBroker.setCreateTime(DateUtils.getNowDate());
        return tradingBrokerMapper.insertTradingBroker(tradingBroker);
    }

    /**
     * 修改券商
     * 
     * @param tradingBroker 券商
     * @return 结果
     */
    @Override
    public int updateTradingBroker(TradingBroker tradingBroker)
    {
        tradingBroker.setUpdateTime(DateUtils.getNowDate());
        return tradingBrokerMapper.updateTradingBroker(tradingBroker);
    }

    /**
     * 批量删除券商
     * 
     * @param brokerIds 需要删除的券商ID
     * @return 结果
     */
    @Override
    public int deleteTradingBrokerByIds(Long[] brokerIds)
    {
        return tradingBrokerMapper.deleteTradingBrokerByIds(brokerIds);
    }

    /**
     * 删除券商信息
     * 
     * @param brokerId 券商ID
     * @return 结果
     */
    @Override
    public int deleteTradingBrokerById(Long brokerId)
    {
        return tradingBrokerMapper.deleteTradingBrokerById(brokerId);
    }

    /**
     * 校验券商代码是否唯一
     * 
     * @param tradingBroker 券商
     * @return 结果
     */
    @Override
    public boolean checkBrokerCodeUnique(TradingBroker tradingBroker)
    {
        Long brokerId = StringUtils.isNull(tradingBroker.getBrokerId()) ? -1L : tradingBroker.getBrokerId();
        TradingBroker info = tradingBrokerMapper.selectTradingBrokerByCode(tradingBroker.getBrokerCode());
        if (StringUtils.isNotNull(info) && info.getBrokerId().longValue() != brokerId.longValue())
        {
            return false;
        }
        return true;
    }
}
