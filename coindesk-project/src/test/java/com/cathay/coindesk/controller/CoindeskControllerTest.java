package com.cathay.coindesk.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.cathay.coindesk.repository.CurrencyRepository;

@WebMvcTest(CoindeskController.class)
public class CoindeskControllerTest {
	
	private Log logger = LogFactory.getLog(this.getClass());
	
	@Autowired
	private MockMvc mockMvc; // 用於發送 HTTP 請求
	
	@MockBean
	private CurrencyRepository currencyRepository;
	
	@Test
	void getCoindeskApiDataTest() throws Exception {
		String url = "/coindesk/getCoindeskApiData";
		MvcResult result = mockMvc.perform(post(url)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andReturn();
		
		// 取得 Response Body 字串
		String responseBody = result.getResponse().getContentAsString();
		
		logger.info("Response Body: "+responseBody);
		
		logger.info(String.format("測試接口 %s - 成功", url));
	}
	
	@Test
	void transCoindeskTest() throws Exception {
		String url = "/coindesk/transCoindesk";
		MvcResult result = mockMvc.perform(post(url)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andReturn();
		
		// 取得 Response Body 字串
		String responseBody = result.getResponse().getContentAsString();
		
		logger.info("Response Body: "+responseBody);
		
		logger.info(String.format("測試接口 %s - 成功", url));
	}
	
}