package com.cathay.coindesk.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.cathay.coindesk.dto.CurrencyDto;
import com.cathay.coindesk.enums.CurrencyName;
import com.cathay.coindesk.util.DateUtil;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 處理 Coindesk API 相關的業務邏輯服務。
 * 負責呼叫 Coindesk API 並進行資料轉換。
 */
@Service
public class CoindeskService {
	
	private Log logger = LogFactory.getLog(this.getClass());
	
	@Value("${coindesk.api.url}")
	private String coindeskApiUrl;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private CurrencyService currencyService;
	
	/**
	 * 呼叫 Coindesk 外部 API 並獲取原始響應。
	 * @return Coindesk API 的原始響應數據傳輸對象
	 */
	public String getCoindeskApiData() {
		return restTemplate.getForObject(coindeskApiUrl, String.class);
	}
	
	/**
	 * 呼叫 Coindesk API，解析其內容並進行資料轉換，組成新的 API 響應。
	 * 新 API 包含更新時間（格式：1990/01/01 00:00:00）和幣別相關資訊。
	 * @return 轉換後的 Coindesk 響應數據傳輸對象
	 * @throws Exception 
	 */
	public List<CurrencyDto> transCoindesk() throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		List<CurrencyDto> CurrencyDtoList = new ArrayList<>();
		try {
			String json = getCoindeskApiData();
			
			JsonNode rootNode = objectMapper.readTree(json);
			String updatedISO = rootNode.path("time").path("updatedISO").asText();
			String updateTime = DateUtil.formatIsoTime(updatedISO);
			JsonNode bpiNode = rootNode.path("bpi");
			for (JsonNode currencyNode : bpiNode) {
				CurrencyDto currencyDto = new CurrencyDto();
				String code = currencyNode.path("code").asText();
				currencyDto.setCode(code);
				currencyDto.setName(CurrencyName.findNameByCode(code));
				currencyDto.setRateFloat(currencyNode.path("rate_float").asDouble());
				currencyDto.setRate(currencyNode.path("rate").asText());
				currencyDto.setUpdateTime(updateTime);
				CurrencyDtoList.add(currencyDto);
			}
		} catch (Exception e) {
			logger.error("呼叫 Coindesk API，解析其內容並進行資料轉換,發生錯誤,error msg="+e.toString());
			throw new Exception(e);
		}
		
		return CurrencyDtoList;
	}
	
	/**
	 * 呼叫 Coindesk API，解析其內容並進行資料轉換，組成新的 API 響應。寫入DB
	 * @return 轉換後的 Coindesk 響應數據傳輸對象
	 * @throws Exception 
	 */
	@Transactional 
	public List<CurrencyDto> transCoindeskToDb() throws Exception {
		List<CurrencyDto> currencyDtoList = transCoindesk();
		
		currencyService.deleteAll();
		for (CurrencyDto currencyDto : currencyDtoList) {
			currencyService.addCurrency(currencyDto);
		}
		return currencyDtoList;
	}
}
