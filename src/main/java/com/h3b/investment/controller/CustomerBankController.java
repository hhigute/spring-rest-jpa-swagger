package com.h3b.investment.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.h3b.investment.dto.CustomerBankDTO;
import com.h3b.investment.dto.DTOEntity;
import com.h3b.investment.dto.mapper.CustomerBankMapper;
import com.h3b.investment.model.CustomerBank;
import com.h3b.investment.service.CustomerBankService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value="/api/v1" , produces = {"application/json"})
public class CustomerBankController {

	
	@Autowired
	CustomerBankService customerBankService; 
	
	@Autowired
	CustomerBankMapper customerBankMapper;
	
	
	@GetMapping("/customerBank")
	@ApiOperation(value="List all references between Customer and Bank")
	public ResponseEntity<List<DTOEntity>> listCustomerBank(  @RequestParam(name="pageNo", defaultValue = "0") int pageNo,
																	@RequestParam(name="pageSize", defaultValue="10") int pageSize,
																	@RequestParam(name="sortBy",defaultValue="customerDoc") String sortBy){
		List<DTOEntity> listCustomerBankDTO = customerBankService.listCustomerBank(pageNo,pageSize,sortBy)
																 	.stream()
																 	.map(node -> customerBankMapper.convertToDto(node, new CustomerBankDTO()))
																 	.collect(Collectors.toList());
		return ResponseEntity.ok().body(listCustomerBankDTO);
	}
	
	@PostMapping("/customerBank")
	@ApiOperation(value="Link Customer to Bank")
	public ResponseEntity<DTOEntity> createCustomerBank(@Valid @RequestBody CustomerBank customerBank) throws Exception{
		
		DTOEntity customerBankDTO = customerBankMapper.convertToDto(customerBankService.createCustomerBank(customerBank),new CustomerBankDTO() );
		
		return ResponseEntity.ok().body(customerBankDTO);
	}
	
}
