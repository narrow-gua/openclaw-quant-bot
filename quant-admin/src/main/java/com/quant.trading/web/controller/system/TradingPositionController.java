package com.quant.trading.web.controller.system;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.quant.trading.common.annotation.Log;
import com.quant.trading.common.core.controller.BaseController;
import com.quant.trading.common.core.domain.AjaxResult;
import com.quant.trading.common.enums.BusinessType;
import com.quant.trading.system.domain.trading.TradingPosition;
import com.quant.trading.system.service.trading.ITradingPositionService;

/**
 * 持仓记录Controller
 * 
 * @author narrow
 */
@RestController
@RequestMapping("/system/trading/position")
public class TradingPositionController extends BaseController
{
    @Autowired
    private ITradingPositionService tradingPositionService;

    /**
     * 查询持仓列表
     */
    @PreAuthorize("@ss.hasPermi('system:position:list')")
    @GetMapping("/list")
    public AjaxResult list(TradingPosition tradingPosition)
    {
        startPage();
        List<TradingPosition> list = tradingPositionService.selectTradingPositionList(tradingPosition);
        return getDataTable(list);
    }

    /**
     * 导出持仓列表
     */
    @PreAuthorize("@ss.hasPermi('system:position:export')")
    @Log(title = "持仓记录", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(TradingPosition tradingPosition)
    {
        List<TradingPosition> list = tradingPositionService.selectTradingPositionList(tradingPosition);
        return exportExcel(list, "持仓记录数据");
    }

    /**
     * 获取持仓详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:position:query')")
    @GetMapping(value = "/{positionId}")
    public AjaxResult getInfo(@PathVariable("positionId") Long positionId)
    {
        return AjaxResult.success(tradingPositionService.selectTradingPositionById(positionId));
    }

    /**
     * 查询某股票的持仓
     */
    @GetMapping(value = "/symbol/{symbol}")
    public AjaxResult getBySymbol(@PathVariable("symbol") String symbol, 
                                   @RequestParam(required = false) Long brokerId,
                                   @RequestParam(required = false, defaultValue = "REAL") String positionType)
    {
        return AjaxResult.success(tradingPositionService.selectTradingPositionBySymbol(symbol, brokerId, positionType));
    }

    /**
     * 查询所有持仓中记录
     */
    @GetMapping("/open")
    public AjaxResult getOpenPositions()
    {
        return AjaxResult.success(tradingPositionService.selectOpenPositions());
    }

    /**
     * 新增持仓
     */
    @PreAuthorize("@ss.hasPermi('system:position:add')")
    @Log(title = "持仓记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TradingPosition tradingPosition)
    {
        return toAjax(tradingPositionService.insertTradingPosition(tradingPosition));
    }

    /**
     * 修改持仓
     */
    @PreAuthorize("@ss.hasPermi('system:position:edit')")
    @Log(title = "持仓记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TradingPosition tradingPosition)
    {
        return toAjax(tradingPositionService.updateTradingPosition(tradingPosition));
    }

    /**
     * 删除持仓
     */
    @PreAuthorize("@ss.hasPermi('system:position:remove')")
    @Log(title = "持仓记录", businessType = BusinessType.DELETE)
    @DeleteMapping("/{positionIds}")
    public AjaxResult remove(@PathVariable Long[] positionIds)
    {
        return toAjax(tradingPositionService.deleteTradingPositionByIds(positionIds));
    }
}
