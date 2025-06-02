-- 幣別資料表 CURRENCY 的建立語法
CREATE TABLE IF NOT EXISTS CURRENCY (
    CODE VARCHAR(10) PRIMARY KEY,           -- 幣別代碼
    NAME VARCHAR(50) ,                      -- 幣別中文名稱
    RATE_FLOAT DECIMAL(15, 4) ,             -- 匯率
    RATE VARCHAR(50) ,               		-- 匯率(字串)
    UPDATE_TIME TIMESTAMP DEFAULT CURRENT_TIMESTAMP -- 更新時間
);
