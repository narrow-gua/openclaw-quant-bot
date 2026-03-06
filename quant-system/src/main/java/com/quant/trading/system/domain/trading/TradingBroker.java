package com.quant.trading.system.domain.trading;

import com.quant.trading.common.annotation.Excel;
import com.quant.trading.common.core.domain.BaseEntity;

/**
 * 券商配置 trading_broker
 * 
 * @author narrow
 */
public class TradingBroker extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 券商ID */
    private Long brokerId;

    /** 券商名称 */
    @Excel(name = "券商名称")
    private String brokerName;

    /** 券商代码 */
    @Excel(name = "券商代码")
    private String brokerCode;

    /** API Key */
    private String apiKey;

    /** Secret Key */
    private String secretKey;

    /** 券商类型 */
    @Excel(name = "券商类型", readConverterExp = "ALPACA=Alpaca,IB=盈透,TIGER=老虎,MOOMOO=富途,OTHER=其他")
    private String brokerType;

    /** 备注 */
    private String remark;

    /** 状态 */
    @Excel(name = "状态", readConverterExp = "0=正常,1=停用")
    private String status;

    public Long getBrokerId() {
        return brokerId;
    }

    public void setBrokerId(Long brokerId) {
        this.brokerId = brokerId;
    }

    public String getBrokerName() {
        return brokerName;
    }

    public void setBrokerName(String brokerName) {
        this.brokerName = brokerName;
    }

    public String getBrokerCode() {
        return brokerCode;
    }

    public void setBrokerCode(String brokerCode) {
        this.brokerCode = brokerCode;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getBrokerType() {
        return brokerType;
    }

    public void setBrokerType(String brokerType) {
        this.brokerType = brokerType;
    }

    @Override
    public String getRemark() {
        return remark;
    }

    @Override
    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
