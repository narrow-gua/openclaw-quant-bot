package com.quant.trading.web.controller.system;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.quant.trading.common.annotation.Log;
import com.quant.trading.common.core.controller.BaseController;
import com.quant.trading.common.core.domain.AjaxResult;
import com.quant.trading.common.enums.BusinessType;
import com.quant.trading.system.domain.trading.TradingStock;
import com.quant.trading.system.service.trading.ITradingStockService;

/**
 * 股票关注Controller
 * 
 * @author narrow
 */
@RestController
@RequestMapping("/system/trading/stock")
public class TradingStockController extends BaseController
{
    @Autowired
    private ITradingStockService tradingStockService;

    /**
     * 查询股票列表
     */
    @PreAuthorize("@ss.hasPermi('system:stock:list')")
    @GetMapping("/list")
    public AjaxResult list(TradingStock tradingStock)
    {
        startPage();
        List<TradingStock> list = tradingStockService.selectTradingStockList(tradingStock);
        return getDataTable(list);
    }

    /**
     * 导出股票列表
     */
    @PreAuthorize("@ss.hasPermi('system:stock:export')")
    @Log(title = "股票关注", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(TradingStock tradingStock)
    {
        List<TradingStock> list = tradingStockService.selectTradingStockList(tradingStock);
        return exportExcel(list, "股票关注数据");
    }

    /**
     * 获取股票详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:stock:query')")
    @GetMapping(value = "/{stockId}")
    public AjaxResult getInfo(@PathVariable("stockId") Long stockId)
    {
        return AjaxResult.success(tradingStockService.selectTradingStockById(stockId));
    }

    /**
     * 根据代码获取股票信息
     */
    @GetMapping(value = "/symbol/{symbol}")
    public AjaxResult getBySymbol(@PathVariable("symbol") String symbol)
    {
        return AjaxResult.success(tradingStockService.selectTradingStockBySymbol(symbol));
    }

    /**
     * 新增股票
     */
    @PreAuthorize("@ss.hasPermi('system:stock:add')")
    @Log(title = "股票关注", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TradingStock tradingStock)
    {
        if (!tradingStockService.checkSymbolUnique(tradingStock))
        {
            return AjaxResult.error("新增股票'" + tradingStock.getSymbol() + "'失败，股票代码已存在");
        }
        return toAjax(tradingStockService.insertTradingStock(tradingStock));
    }

    /**
     * 修改股票
     */
    @PreAuthorize("@ss.hasPermi('system:stock:edit')")
    @Log(title = "股票关注", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TradingStock tradingStock)
    {
        if (!tradingStockService.checkSymbolUnique(tradingStock))
        {
            return AjaxResult.error("修改股票'" + tradingStock.getSymbol() + "'失败，股票代码已存在");
        }
        return toAjax(tradingStockService.updateTradingStock(tradingStock));
    }

    /**
     * 删除股票
     */
    @PreAuthorize("@ss.hasPermi('system:stock:remove')")
    @Log(title = "股票关注", businessType = BusinessType.DELETE)
    @DeleteMapping("/{stockIds}")
    public AjaxResult remove(@PathVariable Long[] stockIds)
    {
        return toAjax(tradingStockService.deleteTradingStockByIds(stockIds));
    }
}
