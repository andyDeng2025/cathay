package com.cathay.coindesk.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.cathay.coindesk.dto.CurrencyDto;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Configuration
public class CoindeskServiceTest {
	
	private Log logger = LogFactory.getLog(this.getClass());
	
	@Autowired
	private CoindeskService coindeskService;
	
	/**
	 * 針對資料轉換相關邏輯作單元測試。
	 */
	//@Test
	public void testTransCoindesk() {
		List<CurrencyDto> currencyDtoList;
		try {
			currencyDtoList = coindeskService.transCoindesk();
			for (CurrencyDto currencyDto : currencyDtoList) {
				logger.info("currencyDto="+currencyDto);
				assertNotNull(currencyDto.getCode(), "幣別代碼不應為空");
				assertNotNull(currencyDto.getName(), "幣別中文名稱不應為空,請檢查CurrencyName對應是否有短少");
				assertNotNull(currencyDto.getRate(), "匯率不應為空");
				assertNotNull(currencyDto.getUpdateTime(), "更新時間不應為空");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("轉換數據 - 成功");
	}
	
	/**
	 * 測試 將 API 轉換數據 並 寫入 幣別 db
	 */
	@Test
	public void testTransCoindeskToDb() {
		List<CurrencyDto> currencyDtoList;
		try {
			currencyDtoList = coindeskService.transCoindeskToDb();
			for (CurrencyDto currencyDto : currencyDtoList) {
				logger.info("currencyDto="+currencyDto);
				assertNotNull(currencyDto.getCode(), "幣別代碼不應為空");
				assertNotNull(currencyDto.getName(), "幣別中文名稱不應為空,請檢查CurrencyName對應是否有短少");
				assertNotNull(currencyDto.getRate(), "匯率不應為空");
				assertNotNull(currencyDto.getUpdateTime(), "更新時間不應為空");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("將 API 轉換數據 並 寫入 幣別 db - 成功");
	}
}
