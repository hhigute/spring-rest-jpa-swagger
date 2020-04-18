package com.h3b.investment.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.h3b.investment.model.CustomerBank;
import com.h3b.investment.service.CustomerBankService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value="/api/v1" , produces = {"application/json"})
public class CustomerBankController {

	
	@Autowired
	CustomerBankService customerBankService; 
	
	
	@GetMapping("/customerBank")
	@ApiOperation(value="List all references between Customer and Bank")
	public List<CustomerBank> listCustomerBank( @RequestParam(name="pageNo", defaultValue = "0") int pageNo,
												@RequestParam(name="pageSize", defaultValue="10") int pageSize,
												@RequestParam(name="sortBy",defaultValue="customerDoc") String sortBy){
		return customerBankService.listCustomerBank(pageNo,pageSize,sortBy);
	}
	
	@PostMapping("/customerBank")
	@ApiOperation(value="Link Customer to Bank")
	public CustomerBank createCustomerBank(@Valid @RequestBody CustomerBank customerBank) throws Exception{
		return customerBankService.createCustomerBank(customerBank);
	}
	
}
