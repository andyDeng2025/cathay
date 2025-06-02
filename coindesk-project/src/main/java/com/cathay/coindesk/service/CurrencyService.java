package com.cathay.coindesk.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.cathay.coindesk.dto.CurrencyDto;
import com.cathay.coindesk.entity.Currency;
import com.cathay.coindesk.enums.CurrencyName;
import com.cathay.coindesk.repository.CurrencyRepository;
import com.cathay.coindesk.util.DateUtil;

/**
 * 處理幣別資料 (CURRENCY) 的業務邏輯服務。
 * 提供幣別的查詢、新增、修改、刪除功能。
 */
@Service
public class CurrencyService {
	
	@Autowired
	private CurrencyRepository currencyRepository;
	
	/**
	 * 獲取所有幣別資料。
	 * @return 幣別 DTO 列表
	 */
	public List<CurrencyDto> getAllData() {
		return currencyRepository.findAll().stream()
				.map(this::convertToDto) // 將實體轉換為 DTO
				.collect(Collectors.toList());
	}
	
	/**
	 * 根據幣別代碼獲取單一幣別資料。
	 * @param code 幣別代碼
	 * @return 對應的幣別 DTO
	 */
	public CurrencyDto getCurrencyByCode(String code) {
		Optional<Currency> currencyOptional = currencyRepository.findById(code);
		if (!currencyOptional.isPresent()) {
			throw new IllegalArgumentException("Currency with code "+code+" not found.");
		}
		return convertToDto(currencyOptional.get());
	}
	
	/**
	 * 新增幣別資料。
	 * @param currencyDto 要新增的幣別 DTO
	 * @return 新增後的幣別 DTO
	 */
	public CurrencyDto addCurrency(CurrencyDto currencyDto) {
		// 檢查是否已存在該幣別
		if (currencyRepository.existsById(currencyDto.getCode())) {
			throw new IllegalArgumentException("Currency with code "+currencyDto.getCode()+" already exists.");
		}
		Currency currency = convertToEntity(currencyDto);
		Currency savedCurrency = currencyRepository.save(currency);
		
		return convertToDto(savedCurrency);
	}
	
	/**
	 * 更新幣別資料。
	 * @param code 幣別代碼
	 * @param currencyDto 包含更新資訊的幣別 DTO
	 * @return 更新後的幣別 DTO
	 */
	public CurrencyDto updateCurrency(CurrencyDto currencyDto) {
		Currency currency = convertToEntity(currencyDto);
		Currency updatedCurrency = currencyRepository.save(currency);
		return convertToDto(updatedCurrency);
	}
	
	/**
	 * 刪除幣別資料。
	 * @param code 幣別代碼
	 */
	public void deleteByCode(String code) {
		currencyRepository.deleteById(code);
	}
	
	/**
	 * 刪除所有幣別資料。
	 */
	public void deleteAll() {
		currencyRepository.deleteAll();
	}
	
	/**
	 * 將 Currency 實體轉換為 CurrencyDto。
	 * @param currency 要轉換的 Currency 實體
	 * @return 轉換後的 CurrencyDto
	 */
	private CurrencyDto convertToDto(Currency currency) {
		CurrencyDto dto = new CurrencyDto();
		dto.setCode(currency.getCode());
		dto.setName(currency.getName());
		dto.setRate(currency.getRate());
		dto.setRateFloat(currency.getRateFloat());
		dto.setUpdateTime(DateUtil.toString(currency.getUpdateTime()));
		return dto;
	}
	
	/**
	 * 將 CurrencyDto 轉換為 Currency 實體。
	 * @param dto 要轉換的 CurrencyDto
	 * @return 轉換後的 Currency 實體
	 */
	private Currency convertToEntity(CurrencyDto dto) {
		Currency entity = new Currency();
		entity.setCode(dto.getCode());
		entity.setRate(dto.getRate());
		entity.setRateFloat(dto.getRateFloat());
		
		String name = dto.getName();
		if (StringUtils.isEmpty(name)) {
			name = CurrencyName.findNameByCode(dto.getCode());
		}
		entity.setName(name);
		
		String updateTime = dto.getUpdateTime();
		if (StringUtils.isEmpty(updateTime)) {
			entity.setUpdateTime(new Date());
		} else {
			entity.setUpdateTime(DateUtil.toDate(updateTime));
		}
		
		return entity;
	}
}
