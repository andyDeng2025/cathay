package com.cathay.coindesk.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cathay.coindesk.dto.CurrencyDto;
import com.cathay.coindesk.service.CoindeskService;

/**
 * 處理 Coindesk API 相關的 RESTful API 控制器。
 * 提供獲取原始 Coindesk 資料和轉換後資料的接口。
 */
@RestController
@RequestMapping("/coindesk")
public class CoindeskController {
	
	@Autowired
	private CoindeskService coindeskService;
	
	/** 
	 * 呼叫 Coindesk 外部 API 並返回原始響應。
	 * @return Coindesk API 的原始響應數據傳輸對象的 ResponseEntity
	 */
	@PostMapping("/getCoindeskApiData")
	public ResponseEntity<String> getCoindeskApiData() {
		String res = coindeskService.getCoindeskApiData();
		return ResponseEntity.ok(res);
	}
	
	/**
	 * 呼叫 Coindesk API，解析其內容並進行資料轉換，組成新的 API 響應。
	 * @return 轉換後的 Coindesk 響應數據傳輸對象的 
	 * @throws Exception 
	 */
	@PostMapping("/transCoindesk")
	public ResponseEntity<List<CurrencyDto>> transCoindesk() throws Exception {
		List<CurrencyDto> currencyDtoList = coindeskService.transCoindesk();
		return ResponseEntity.ok(currencyDtoList);
	}
	
	/**
	 * 呼叫 Coindesk API，解析其內容並進行資料轉換，組成新的 API 響應。寫入DB
	 * @return 轉換後的 Coindesk 響應數據傳輸對象的 
	 * @throws Exception 
	 */
	@PostMapping("/transCoindeskToDb")
	public ResponseEntity<List<CurrencyDto>> transCoindeskToDb() throws Exception {
		List<CurrencyDto> currencyDtoList = coindeskService.transCoindeskToDb();
		return ResponseEntity.ok(currencyDtoList);
	}
	
}
