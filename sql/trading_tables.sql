-- ----------------------------
-- 交易相关表结构
-- ----------------------------

-- 券商配置表
DROP TABLE IF EXISTS trading_broker;
CREATE TABLE trading_broker (
  broker_id bigint(20) NOT NULL AUTO_INCREMENT COMMENT '券商ID',
  broker_name varchar(100) NOT NULL COMMENT '券商名称',
  broker_code varchar(50) NOT NULL COMMENT '券商代码',
  api_key varchar(500) DEFAULT NULL COMMENT 'API Key',
  secret_key varchar(500) DEFAULT NULL COMMENT 'Secret Key',
  broker_type varchar(20) DEFAULT 'OTHER' COMMENT '券商类型: ALPACA=Alpaca, IB=盈透, TIGER=老虎, MOOMOO=富途, OTHER=其他',
  status char(1) DEFAULT '0' COMMENT '状态: 0=正常, 1=停用',
  remark varchar(500) DEFAULT NULL COMMENT '备注',
  create_time datetime DEFAULT NULL COMMENT '创建时间',
  update_time datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (broker_id),
  UNIQUE KEY uk_broker_code (broker_code)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='券商配置表';

-- 股票关注表
DROP TABLE IF EXISTS trading_stock;
CREATE TABLE trading_stock (
  stock_id bigint(20) NOT NULL AUTO_INCREMENT COMMENT '股票ID',
  symbol varchar(20) NOT NULL COMMENT '股票代码',
  name varchar(100) DEFAULT NULL COMMENT '股票名称',
  type varchar(20) DEFAULT 'GROWTH' COMMENT '股票类型: GROWTH=成长, VALUE=价值, INDEX=指数, ETF=ETF, OPTIONS=期权',
  current_price decimal(10,2) DEFAULT NULL COMMENT '当前价格',
  target_price decimal(10,2) DEFAULT NULL COMMENT '目标价格',
  stop_loss_price decimal(10,2) DEFAULT NULL COMMENT '止损价格',
  status varchar(20) DEFAULT 'WATCHING' COMMENT '状态: WATCHING=关注, BUY_SIGNAL=买入信号, HOLDING=持有, SELL_SIGNAL=卖出信号, SOLD=已卖出',
  remark varchar(500) DEFAULT NULL COMMENT '备注',
  create_time datetime DEFAULT NULL COMMENT '创建时间',
  update_time datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (stock_id),
  UNIQUE KEY uk_symbol (symbol)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='股票关注表';

-- 持仓记录表
DROP TABLE IF EXISTS trading_position;
CREATE TABLE trading_position (
  position_id bigint(20) NOT NULL AUTO_INCREMENT COMMENT '持仓ID',
  stock_id bigint(20) DEFAULT NULL COMMENT '股票ID',
  symbol varchar(20) NOT NULL COMMENT '股票代码',
  stock_name varchar(100) DEFAULT NULL COMMENT '股票名称',
  broker_id bigint(20) DEFAULT NULL COMMENT '券商ID',
  broker_name varchar(100) DEFAULT NULL COMMENT '券商名称',
  position_type varchar(20) NOT NULL DEFAULT 'REAL' COMMENT '持仓类型: SIMULATED=模拟盘, REAL=实盘',
  quantity int(11) DEFAULT NULL COMMENT '持股数量',
  avg_price decimal(10,4) DEFAULT NULL COMMENT '入手平均价格',
  current_price decimal(10,4) DEFAULT NULL COMMENT '当前价格',
  stop_loss_price decimal(10,4) DEFAULT NULL COMMENT '止损价格',
  take_profit_price decimal(10,4) DEFAULT NULL COMMENT '止盈价格',
  total_cost decimal(12,2) DEFAULT NULL COMMENT '总成本',
  market_value decimal(12,2) DEFAULT NULL COMMENT '当前市值',
  profit_loss decimal(12,2) DEFAULT NULL COMMENT '浮动盈亏',
  profit_loss_rate decimal(8,4) DEFAULT NULL COMMENT '盈亏比例(%)',
  status varchar(20) DEFAULT 'OPEN' COMMENT '状态: OPEN=持仓中, CLOSED=已平仓, STOPPED_OUT=止损出局',
  open_time datetime DEFAULT NULL COMMENT '建仓时间',
  close_time datetime DEFAULT NULL COMMENT '平仓时间',
  remark varchar(500) DEFAULT NULL COMMENT '备注',
  create_time datetime DEFAULT NULL COMMENT '创建时间',
  update_time datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (position_id),
  KEY idx_symbol (symbol),
  KEY idx_broker (broker_id),
  KEY idx_status (status)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='持仓记录表';

-- ----------------------------
-- 示例数据
-- ----------------------------

-- 插入默认券商
INSERT INTO trading_broker (broker_name, broker_code, broker_type, status, remark) VALUES
('模拟盘', 'SIMULATED', 'OTHER', '0', '模拟交易账户'),
('Alpaca Paper', 'ALPACA_PAPER', 'ALPACA', '0', 'Alpaca模拟交易');

-- 插入示例股票
INSERT INTO trading_stock (symbol, name, type, target_price, stop_loss_price, status, remark) VALUES
('NVDA', '英伟达', 'GROWTH', 200.00, 150.00, 'WATCHING', 'AI芯片龙头'),
('AMD', '超威半导体', 'GROWTH', 180.00, 120.00, 'WATCHING', 'AI芯片竞争对手'),
('MSFT', '微软', 'GROWTH', 450.00, 380.00, 'WATCHING', '云计算龙头'),
('GOOGL', '谷歌', 'VALUE', 180.00, 140.00, 'WATCHING', '搜索+AI'),
('BABA', '阿里巴巴', 'VALUE', 150.00, 100.00, 'WATCHING', '中概股'),
('VTI', '先锋全市场ETF', 'INDEX', NULL, NULL, 'WATCHING', '分散投资');
