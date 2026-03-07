-- ----------------------------
-- 全量股票信息表
-- ----------------------------
DROP TABLE IF EXISTS market_stock_info;
CREATE TABLE market_stock_info (
  stock_id bigint(20) NOT NULL AUTO_INCREMENT COMMENT '股票ID',
  symbol varchar(20) NOT NULL COMMENT '股票代码',
  name varchar(100) DEFAULT NULL COMMENT '股票名称',
  exchange varchar(20) DEFAULT NULL COMMENT '交易所',
  stock_type varchar(20) DEFAULT 'STOCK' COMMENT '股票类型: STOCK=普通股,ETF=ETF,ADR=ADR',
  sector varchar(50) DEFAULT NULL COMMENT '所属板块',
  industry varchar(50) DEFAULT NULL COMMENT '所属行业',
  market_cap decimal(20,2) DEFAULT NULL COMMENT '市值',
  pe decimal(10,2) DEFAULT NULL COMMENT '市盈率',
  pb decimal(10,2) DEFAULT NULL COMMENT '市净率',
  dividend_yield decimal(10,4) DEFAULT NULL COMMENT '股息率',
  week52_high decimal(10,2) DEFAULT NULL COMMENT '52周最高',
  week52_low decimal(10,2) DEFAULT NULL COMMENT '52周最低',
  current_price decimal(10,4) DEFAULT NULL COMMENT '当前价格',
  change decimal(10,4) DEFAULT NULL COMMENT '价格变化',
  change_percent decimal(10,4) DEFAULT NULL COMMENT '涨跌幅',
  volume bigint(20) DEFAULT NULL COMMENT '成交量',
  avg_volume bigint(20) DEFAULT NULL COMMENT '平均成交量',
  beta decimal(10,4) DEFAULT NULL COMMENT 'Beta值',
  volatility decimal(10,4) DEFAULT NULL COMMENT '波动率',
  status varchar(20) DEFAULT 'ACTIVE' COMMENT '状态: ACTIVE=上市,DELISTED=退市,SUSPENDED=暂停',
  listing_date datetime DEFAULT NULL COMMENT '上市日期',
  last_update_time datetime DEFAULT NULL COMMENT '最后更新时间',
  create_time datetime DEFAULT NULL COMMENT '创建时间',
  update_time datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (stock_id),
  UNIQUE KEY uk_symbol (symbol),
  KEY idx_exchange (exchange),
  KEY idx_sector (sector),
  KEY idx_industry (industry),
  KEY idx_status (status)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='全量股票信息表';
