package com.cathay.coindesk.enums;

public enum CurrencyName {
	USD("USD", "美金"),
	GBP("GBP", "英鎊"),
	EUR("EUR", "歐元"),
	JPY("JPY", "日圓"),
	UNKNOWN("UNKNOWN", "UNKNOWN");
	
	private final String code; // 幣別代碼
	private final String name; // 幣別中文名稱
	
	CurrencyName(String code, String name) {
		this.code = code;
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public String getCode() {
		return code;
	}
	
	public static String findNameByCode(String code) {
		for (CurrencyName value : CurrencyName.values()) {
			if (value.getCode().equalsIgnoreCase(code)) {
				return value.getName();
			}
		}
		return UNKNOWN.getName(); // 如果找不到對應的中文名稱
	}
}