package com.h3b.investment.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.h3b.investment.service.IrService;

@ExtendWith(SpringExtension.class)
@WebMvcTest
public class IrControllerUnitTest {
	
	@Autowired
	MockMvc mockMvc;
	
	
	@MockBean
	IrService irService;
	
	@Test
	void listIrs() {
		
	}
	
}
