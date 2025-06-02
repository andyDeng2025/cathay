package com.cathay.coindesk.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * CURRENCY 資料表的 JPA 實體類。
 */
@Entity
@Table(name = "CURRENCY")
public class Currency {
	
	@Id // 標示為主鍵
	@Column(name = "CODE", length = 10) // 映射到 CODE 欄位，長度為 10
	private String code; // 幣別代碼
	
	@Column(name = "NAME", length = 50) // 映射到 NAME 欄位，長度為 50
	private String name; // 幣別中文名稱
	
	@Column(name = "RATE_FLOAT", precision = 15, scale = 4) // 映射到 RATE_FLOAT 欄位，精度 15，小數點後 4 位
	private Double rateFloat; // 匯率
	
	@Column(name = "RATE", length = 50) // 映射到 RATE 欄位，長度為 50
	private String rate; // 匯率(字串)
	
	@Column(name = "UPDATE_TIME") // 映射到 UPDATE_TIME 欄位
	private Date updateTime; // 更新時間
	
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
	
	public Date getUpdateTime() {
		return updateTime;
	}
	
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	@Override
	public String toString() {
		return "Currency [code="+code+", name="+name+", rateFloat="+rateFloat+", rate="+rate+", updateTime="+updateTime+"]";
	}
	
}