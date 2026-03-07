package com.quant.trading.web.controller.market;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import com.quant.trading.common.annotation.Log;
import com.quant.trading.common.core.controller.BaseController;
import com.quant.trading.common.core.domain.AjaxResult;
import com.quant.trading.common.enums.BusinessType;
import com.quant.trading.system.domain.market.MarketStockInfo;
import com.quant.trading.system.service.market.IMarketStockInfoService;

/**
 * 全量股票信息Controller
 * 
 * @author narrow
 */
@RestController
@RequestMapping("/market/stock")
public class MarketStockInfoController extends BaseController
{
    @Autowired
    private IMarketStockInfoService marketStockInfoService;

    private final RestTemplate restTemplate = new RestTemplate();

    /**
     * 查询股票列表
     */
    @PreAuthorize("@ss.hasPermi('market:stock:list')")
    @GetMapping("/list")
    public AjaxResult list(MarketStockInfo marketStockInfo)
    {
        startPage();
        List<MarketStockInfo> list = marketStockInfoService.selectMarketStockInfoList(marketStockInfo);
        return getDataTable(list);
    }

    /**
     * 获取股票总数
     */
    @GetMapping("/count")
    public AjaxResult count()
    {
        return AjaxResult.success(marketStockInfoService.selectMarketStockInfoCount());
    }

    /**
     * 根据交易所获取股票列表
     */
    @GetMapping("/exchange/{exchange}")
    public AjaxResult getByExchange(@PathVariable String exchange)
    {
        return AjaxResult.success(marketStockInfoService.selectMarketStockInfoByExchange(exchange));
    }

    /**
     * 获取股票详细信息
     */
    @PreAuthorize("@ss.hasPermi('market:stock:query')")
    @GetMapping(value = "/{stockId}")
    public AjaxResult getInfo(@PathVariable("stockId") Long stockId)
    {
        return AjaxResult.success(marketStockInfoService.selectMarketStockInfoById(stockId));
    }

    /**
     * 根据代码获取股票信息
     */
    @GetMapping(value = "/symbol/{symbol}")
    public AjaxResult getBySymbol(@PathVariable("symbol") String symbol)
    {
        return AjaxResult.success(marketStockInfoService.selectMarketStockInfoBySymbol(symbol));
    }

    /**
     * 新增股票
     */
    @PreAuthorize("@ss.hasPermi('market:stock:add')")
    @Log(title = "股票信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MarketStockInfo marketStockInfo)
    {
        if (!marketStockInfoService.checkSymbolUnique(marketStockInfo))
        {
            return AjaxResult.error("新增股票'" + marketStockInfo.getSymbol() + "'失败，股票代码已存在");
        }
        return toAjax(marketStockInfoService.insertMarketStockInfo(marketStockInfo));
    }

    /**
     * 修改股票
     */
    @PreAuthorize("@ss.hasPermi('market:stock:edit')")
    @Log(title = "股票信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MarketStockInfo marketStockInfo)
    {
        if (!marketStockInfoService.checkSymbolUnique(marketStockInfo))
        {
            return AjaxResult.error("修改股票'" + marketStockInfo.getSymbol() + "'失败，股票代码已存在");
        }
        return toAjax(marketStockInfoService.updateMarketStockInfo(marketStockInfo));
    }

    /**
     * 删除股票
     */
    @PreAuthorize("@ss.hasPermi('market:stock:remove')")
    @Log(title = "股票信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{stockIds}")
    public AjaxResult remove(@PathVariable Long[] stockIds)
    {
        return toAjax(marketStockInfoService.deleteMarketStockInfoByIds(stockIds));
    }

    /**
     * 从外部API同步全量股票数据
     * 支持多种数据源: NASDAQ, NYSE, AMEX, ALL
     */
    @PreAuthorize("@ss.hasPermi('market:stock:sync')")
    @Log(title = "股票数据同步", businessType = BusinessType.INSERT)
    @GetMapping("/sync")
    public AjaxResult syncFromExternal(@RequestParam(required = false, defaultValue = "ALL") String exchange)
    {
        try
        {
            List<MarketStockInfo> stockList = fetchStockListFromExternal(exchange);
            
            if (stockList == null || stockList.isEmpty())
            {
                return AjaxResult.error("获取股票数据失败，请检查网络或API配置");
            }

            int count = marketStockInfoService.syncStockData(stockList);
            return AjaxResult.success("同步成功，共同步 " + count + " 条数据 (来源: " + exchange + ")");
        }
        catch (Exception e)
        {
            return AjaxResult.error("同步失败: " + e.getMessage());
        }
    }

    /**
     * 从外部API获取股票列表
     * 使用 NASDAQ API (免费)
     */
    private List<MarketStockInfo> fetchStockListFromExternal(String exchange) throws Exception
    {
        List<MarketStockInfo> result = new ArrayList<>();
        
        String url = "https://api.nasdaq.com/api/screener/stocks?limit=10000";
        
        try
        {
            ResponseEntity<Map> response = restTemplate.getForEntity(url, Map.class);
            
            if (response.getBody() != null && response.getBody().containsKey("data"))
            {
                Map<String, Object> data = (Map<String, Object>) response.getBody().get("data");
                if (data != null && data.containsKey("rows"))
                {
                    List<Map<String, Object>> rows = (List<Map<String, Object>>) data.get("rows");
                    
                    for (Map<String, Object> row : rows)
                    {
                        try
                        {
                            MarketStockInfo stock = new MarketStockInfo();
                            
                            String symbol = getStringValue(row, "symbol");
                            if (symbol == null || symbol.isEmpty())
                                continue;
                            
                            stock.setSymbol(symbol);
                            stock.setName(getStringValue(row, "companyName"));
                            stock.setExchange(getStringValue(row, "exchange"));
                            stock.setStockType("STOCK");
                            
                            // Market cap
                            String marketCapStr = getStringValue(row, "marketCap");
                            if (marketCapStr != null && !marketCapStr.equals("-"))
                            {
                                stock.setMarketCap(parseNumber(marketCapStr));
                            }
                            
                            // PE
                            String peStr = getStringValue(row, "peRatio");
                            if (peStr != null && !peStr.equals("-"))
                            {
                                stock.setPe(parseDecimal(peStr));
                            }
                            
                            // Dividend
                            String divStr = getStringValue(row, "dividendYield");
                            if (divStr != null && !divStr.equals("-"))
                            {
                                stock.setDividendYield(parseDecimal(divStr));
                            }
                            
                            // Volume
                            String volumeStr = getStringValue(row, "volume");
                            if (volumeStr != null && !volumeStr.equals("-"))
                            {
                                stock.setVolume(parseLong(volumeStr));
                            }
                            
                            // Price
                            String priceStr = getStringValue(row, "lastSalePrice");
                            if (priceStr != null && !priceStr.equals("-"))
                            {
                                stock.setCurrentPrice(parseDecimal(priceStr));
                            }
                            
                            // Change
                            String changeStr = getStringValue(row, "netChange");
                            if (changeStr != null && !changeStr.equals("-"))
                            {
                                stock.setChange(parseDecimal(changeStr));
                            }
                            
                            String changePctStr = getStringValue(row, "percentageChange");
                            if (changePctStr != null && !changePctStr.equals("-"))
                            {
                                stock.setChangePercent(parseDecimal(changePctStr.replace("%", "")));
                            }
                            
                            stock.setStatus("ACTIVE");
                            stock.setLastUpdateTime(new java.util.Date());
                            
                            // Filter by exchange if specified
                            if (!"ALL".equalsIgnoreCase(exchange))
                            {
                                if (exchange.equalsIgnoreCase(stock.getExchange()))
                                {
                                    result.add(stock);
                                }
                            }
                            else
                            {
                                result.add(stock);
                            }
                        }
                        catch (Exception e)
                        {
                            // Skip malformed rows
                            continue;
                        }
                    }
                }
            }
        }
        catch (Exception e)
        {
            // If NASDAQ API fails, try alternative source or return error
            throw new Exception("NASDAQ API 调用失败: " + e.getMessage());
        }
        
        return result;
    }

    /**
     * 获取Map中的字符串值
     */
    private String getStringValue(Map<String, Object> map, String key)
    {
        Object value = map.get(key);
        return value != null ? value.toString() : null;
    }

    /**
     * 解析数字字符串
     */
    private java.math.BigDecimal parseNumber(String value)
    {
        if (value == null || value.equals("-") || value.isEmpty())
            return null;
        
        try
        {
            // Handle abbreviations: M (million), B (billion), T (trillion)
            value = value.toUpperCase().replace(",", "").replace("$", "");
            long multiplier = 1;
            
            if (value.endsWith("T"))
            {
                multiplier = 1_000_000_000_000L;
                value = value.substring(0, value.length() - 1);
            }
            else if (value.endsWith("B"))
            {
                multiplier = 1_000_000_000L;
                value = value.substring(0, value.length() - 1);
            }
            else if (value.endsWith("M"))
            {
                multiplier = 1_000_000L;
                value = value.substring(0, value.length() - 1);
            }
            else if (value.endsWith("K"))
            {
                multiplier = 1_000L;
                value = value.substring(0, value.length() - 1);
            }
            
            return new java.math.BigDecimal(value).multiply(new java.math.BigDecimal(multiplier));
        }
        catch (Exception e)
        {
            return null;
        }
    }

    /**
     * 解析Decimal
     */
    private java.math.BigDecimal parseDecimal(String value)
    {
        if (value == null || value.equals("-") || value.isEmpty())
            return null;
        
        try
        {
            value = value.replace("%", "").replace(",", "").replace("$", "");
            return new java.math.BigDecimal(value);
        }
        catch (Exception e)
        {
            return null;
        }
    }

    /**
     * 解析Long
     */
    private Long parseLong(String value)
    {
        if (value == null || value.equals("-") || value.isEmpty())
            return null;
        
        try
        {
            value = value.replace(",", "");
            return Long.parseLong(value);
        }
        catch (Exception e)
        {
            return null;
        }
    }
}
