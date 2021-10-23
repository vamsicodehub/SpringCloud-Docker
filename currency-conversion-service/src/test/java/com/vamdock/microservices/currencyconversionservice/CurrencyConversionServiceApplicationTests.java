package com.vamdock.microservices.currencyconversionservice;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest// This annotation only starts the web layer rather to the whole app like below annotation. Even we can limit 
//to partular controller by passing 
//@WebMvcTest(CurrencyConversionController.class) with these if any service calls exists have to mock them using @MockBean
@AutoConfigureMockMvc// this annotation will serve like the normal or real http request without starting the server
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class CurrencyConversionServiceApplicationTests {
	
	@Autowired
	CurrencyConversionController currencyConversionController;
	
	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;
	
	@Autowired
	private MockMvc mockMvc;
	
//	@MockBean	CurrencyConversionService currencyConversionService;


	@Test
	void contextLoads() {
		assertThat(currencyConversionController).isNotNull();
	}

	@Test
	public void testCurrencyConversionUrl() throws Exception {
		System.out.println("Port :: "+port);
		assertThat(this.restTemplate.getForObject("http://localhost:8080//currency-conversion/from/USD/to/INR/quantity/10",
				String.class)).isNotNull();
	}
	
	@Test
	public void testMockMvc() throws Exception {
		this.mockMvc.perform(get("/")).andDo(print()).andExpect(status().is4xxClientError())
				.andExpect(content().string(containsString("Hello, World")));
	}
	
	@Test
	public void testMockMvcForWeb() throws Exception {
//		when(service.greet()).thenReturn("Hello, Mock");
		this.mockMvc.perform(get("/")).andDo(print()).andExpect(status().is4xxClientError())
				.andExpect(content().string(containsString("Hello, World")));
	}
	
}
