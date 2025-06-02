package com.cathay.coindesk.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cathay.coindesk.dto.CodeDto;
import com.cathay.coindesk.dto.CurrencyDto;
import com.cathay.coindesk.service.CurrencyService;

/**
 * 處理幣別資料 (CURRENCY) 的 RESTful API 控制器。
 * 提供幣別的查詢、新增、修改、刪除功能。
 */
@RestController
@RequestMapping("/currency")
public class CurrencyController {
	
	@Autowired
	private CurrencyService currencyService;
	
	/**
	 * 獲取所有幣別資料。
	 * @return 包含所有幣別 DTO 的 ResponseEntity
	 */
	@PostMapping(value = "/getAllData", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<CurrencyDto>> getAllData() {
		List<CurrencyDto> CurrencyDtoList = currencyService.getAllData();
		return ResponseEntity.ok(CurrencyDtoList);
	}
	
	/**
	 * 根據幣別代碼獲取單一幣別資料。
	 * @param codeDto 幣別代碼
	 * @return 對應的幣別 DTO 的 ResponseEntity
	 */
	@PostMapping(value = "/getCurrencyByCode", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CurrencyDto> getCurrencyByCode(@RequestBody CodeDto codeDto) {
		CurrencyDto currencyDto = currencyService.getCurrencyByCode(codeDto.getCode());
		return ResponseEntity.ok(currencyDto);
	}
	
	/**
	 * 新增幣別資料。
	 * @param currencyDto 要新增的幣別 DTO
	 * @return 新增後的幣別 DTO 的 ResponseEntity
	 */
	@PostMapping(value = "/addCurrency", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CurrencyDto> addCurrency(@RequestBody CurrencyDto currencyDto) {
		CurrencyDto newCurrencyDto = currencyService.addCurrency(currencyDto);
		return ResponseEntity.ok(newCurrencyDto);
	}
	
	/**
	 * 更新幣別資料。
	 * @param currencyDto 包含更新資訊的幣別 DTO
	 * @return 更新後的幣別 DTO 的 ResponseEntity
	 */
	@PostMapping(value = "/updateCurrency", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CurrencyDto> updateCurrency(@RequestBody CurrencyDto currencyDto) {
		CurrencyDto updatedCurrencyDto = currencyService.updateCurrency(currencyDto);
		return ResponseEntity.ok(updatedCurrencyDto);
	}
	
	/**
	 * 刪除幣別資料。
	 * @param codeDto 幣別代碼
	 */
	@PostMapping(value = "/deleteByCode", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<String> deleteByCode(@RequestBody CodeDto codeDto) {
		currencyService.deleteByCode(codeDto.getCode());
		return ResponseEntity.ok("ok");
	}
	
	/**
	 * 刪除所有幣別資料。
	 */
	@PostMapping(value = "/deleteAll", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<String> deleteAll() {
		currencyService.deleteAll();
		return ResponseEntity.ok("ok");
	}
}
