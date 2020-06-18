package com.h3b.investment.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import com.google.common.collect.Lists;
import com.h3b.investment.exception.ResourceNotFoundException;
import com.h3b.investment.model.Customer;
import com.h3b.investment.service.CustomerService;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
@TestInstance(Lifecycle.PER_CLASS)
public class CustomerControllerUnitTest {

	@Autowired
	MockMvc mockMvc;

	@MockBean
	CustomerService customerService;
	
	@Test
    @DisplayName("GET /api/v1/customer - Success")
	void listCustomer() throws Exception {
		// given
		Customer c1 = new Customer("000.000.000-00", "Name 1", "11111111111");
		Customer c2 = new Customer("111.000.000-00", "Name 2", "22222222222");
		Customer c3 = new Customer("222.000.000-00", "Name 3", "33333333333");

		// when
		when(customerService.listCustomer(0, 10, "name")).thenReturn(Lists.newArrayList(c1, c2, c3));

		// Execute the GET request
		mockMvc.perform(get("/api/v1/customer")
									.param("pageNo", "0")
									.param("pageSize", "10")
									.param("sortBy","name"))
				.andDo(print())
				// 	Validate the response code and content type
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				 // Validate the returned fields
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].document", is("000.000.000-00")))
                .andExpect(jsonPath("$[0].name", is("Name 1")))
                .andExpect(jsonPath("$[0].phone", is("11111111111")))
		        .andExpect(jsonPath("$[1].document", is("111.000.000-00")))
		        .andExpect(jsonPath("$[1].name", is("Name 2")))
		        .andExpect(jsonPath("$[1].phone", is("22222222222")))
		        .andExpect(jsonPath("$[2].document", is("222.000.000-00")))
		        .andExpect(jsonPath("$[2].name", is("Name 3")))
		        .andExpect(jsonPath("$[2].phone", is("33333333333")));
				

	}
	
	
	@Test
    @DisplayName("GET /api/v1/customer/{doc} - Success")
	void getCustomerById() throws Exception {
		// given
		String mockDocument = "000.000.000-00";
		Customer c1 = new Customer(mockDocument, "Name 1", "11111111111");

		
		// when
		when(customerService.getCustomerById(mockDocument)).thenReturn(c1);

		// then
		
		String expectedJson = "{\"document\":\"000.000.000-00\",\"name\":\"Name 1\",\"phone\":\"11111111111\",\"banks\":[]}";
		
		// Execute the GET request
		mockMvc.perform(get("/api/v1/customer/{doc}",mockDocument))
				.andDo(print())
				// 	Validate the response code and content type
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				 // Validate the returned fields
                .andExpect(content().json(expectedJson));
	}
	
	@Test
    @DisplayName("POST /api/v1/customer - Success")
	void createCustomer() throws Exception {
		// given
		Customer c1 = new Customer("000.000.000-00", "Name 1", "11111111111");

		// when
		when(customerService.createCustomer(c1)).thenReturn(c1);

		// then
		
		
		
		// Execute the GET request
		String requestJson = "{\"document\":\"000.000.000-00\",\"name\":\"Name 1\",\"phone\":\"11111111111\"}";
		mockMvc.perform(post("/api/v1/customer")
							.contentType(MediaType.APPLICATION_JSON)
							.content(requestJson))
				.andDo(print())
				// 	Validate the response code and content type
				.andExpect(status().isCreated())
				//.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				 // Validate headers
                .andExpect(header().string(HttpHeaders.LOCATION, "http://localhost/api/v1/customer/000.000.000-00"));
				 // Validate the returned fields
                //.andExpect(jsonPath("$.document", is("000.000.000-00")));
	}
	
	@Test
	@DisplayName("PUT - /customer - Success")
	void updateCustomer() throws Exception {
		//give
		Customer customerMock = new Customer("111.111.111-11","Customer Update","112233445566");
		
		//when
		when(customerService.updateCustomer(customerMock)).thenReturn(customerMock);
		
		//then
		String requestJson = "{\"document\":\"111.111.111-11\",\"name\":\"Customer Update\",\"phone\":\"112233445566\"}";
		mockMvc.perform(put("/api/v1/customer")
						.contentType(MediaType.APPLICATION_JSON)
						.content(requestJson))
				.andDo(print())
				// Validate the response code and content type
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				// Validate content
				.andExpect(jsonPath("$.name", is("Customer Update")));
				
		
		
	}
	
	@Test
	@DisplayName("PUT - /customer - Failure")
	void updateCustomer_Failure() throws Exception {
		//give
		Customer customerMock = new Customer("111.111.111-11","Customer Update","112233445566");
		
		//when
		when(customerService.updateCustomer(customerMock)).thenThrow(new ResourceNotFoundException(("Customer not found for document 111.111.111-11")));
		
		//then
		String requestJson = "{\"document\":\"111.111.111-11\",\"name\":\"Customer Update\",\"phone\":\"112233445566\"}";
		mockMvc.perform(put("/api/v1/customer")
						.contentType(MediaType.APPLICATION_JSON)
						.content(requestJson))
				.andDo(print())
				// Validate the response code and content type
				.andExpect(status().isNotFound())
				.andExpect(jsonPath("$.message", is("Customer not found for document 111.111.111-11")));
				
		
		
	}
	

}
