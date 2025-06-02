package com.cathay.coindesk.dto;

/**
 * 用於幣別資料 (CURRENCY) CRUD 操作的數據傳輸對象 (DTO)。
 * 用於接收前端請求和返回響應。
 */
public class CurrencyDto {
	private String code; // 幣別代碼
	private String name; // 幣別中文名稱
	private Double rateFloat; // 匯率
	private String rate; // 匯率(字串)
	private String updateTime; // 更新時間
	
	public CurrencyDto() {
		
	}
	
	public CurrencyDto(String code, String name, Double rateFloat, String updateTime) {
		this.code = code;
		this.name = name;
		this.rateFloat = rateFloat;
		this.updateTime = updateTime;
	}
	
	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Double getRateFloat() {
		return rateFloat;
	}
	
	public void setRateFloat(Double rateFloat) {
		this.rateFloat = rateFloat;
	}
	
	public String getRate() {
		return rate;
	}
	
	public void setRate(String rate) {
		this.rate = rate;
	}
	
	public String getUpdateTime() {
		return updateTime;
	}
	
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	
	@Override
	public String toString() {
		return "CurrencyDto [code="+code+", name="+name+", rateFloat="+rateFloat+", rate="+rate+", updateTime="+updateTime+"]";
	}
}
