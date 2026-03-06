package com.quant.trading.web.controller.system;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.quant.trading.common.annotation.Log;
import com.quant.trading.common.core.controller.BaseController;
import com.quant.trading.common.core.domain.AjaxResult;
import com.quant.trading.common.enums.BusinessType;
import com.quant.trading.system.domain.trading.TradingBroker;
import com.quant.trading.system.service.trading.ITradingBrokerService;

/**
 * 券商配置Controller
 * 
 * @author narrow
 */
@RestController
@RequestMapping("/system/trading/broker")
public class TradingBrokerController extends BaseController
{
    @Autowired
    private ITradingBrokerService tradingBrokerService;

    /**
     * 查询券商列表
     */
    @PreAuthorize("@ss.hasPermi('system:broker:list')")
    @GetMapping("/list")
    public AjaxResult list(TradingBroker tradingBroker)
    {
        startPage();
        List<TradingBroker> list = tradingBrokerService.selectTradingBrokerList(tradingBroker);
        return getDataTable(list);
    }

    /**
     * 导出券商列表
     */
    @PreAuthorize("@ss.hasPermi('system:broker:export')")
    @Log(title = "券商配置", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(TradingBroker tradingBroker)
    {
        List<TradingBroker> list = tradingBrokerService.selectTradingBrokerList(tradingBroker);
        return exportExcel(list, "券商配置数据");
    }

    /**
     * 获取券商详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:broker:query')")
    @GetMapping(value = "/{brokerId}")
    public AjaxResult getInfo(@PathVariable("brokerId") Long brokerId)
    {
        return AjaxResult.success(tradingBrokerService.selectTradingBrokerById(brokerId));
    }

    /**
     * 根据代码获取券商信息
     */
    @GetMapping(value = "/code/{brokerCode}")
    public AjaxResult getByCode(@PathVariable("brokerCode") String brokerCode)
    {
        return AjaxResult.success(tradingBrokerService.selectTradingBrokerByCode(brokerCode));
    }

    /**
     * 新增券商
     */
    @PreAuthorize("@ss.hasPermi('system:broker:add')")
    @Log(title = "券商配置", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TradingBroker tradingBroker)
    {
        if (!tradingBrokerService.checkBrokerCodeUnique(tradingBroker))
        {
            return AjaxResult.error("新增券商'" + tradingBroker.getBrokerName() + "'失败，券商代码已存在");
        }
        return toAjax(tradingBrokerService.insertTradingBroker(tradingBroker));
    }

    /**
     * 修改券商
     */
    @PreAuthorize("@ss.hasPermi('system:broker:edit')")
    @Log(title = "券商配置", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TradingBroker tradingBroker)
    {
        if (!tradingBrokerService.checkBrokerCodeUnique(tradingBroker))
        {
            return AjaxResult.error("修改券商'" + tradingBroker.getBrokerName() + "'失败，券商代码已存在");
        }
        return toAjax(tradingBrokerService.updateTradingBroker(tradingBroker));
    }

    /**
     * 删除券商
     */
    @PreAuthorize("@ss.hasPermi('system:broker:remove')")
    @Log(title = "券商配置", businessType = BusinessType.DELETE)
    @DeleteMapping("/{brokerIds}")
    public AjaxResult remove(@PathVariable Long[] brokerIds)
    {
        return toAjax(tradingBrokerService.deleteTradingBrokerByIds(brokerIds));
    }
}
