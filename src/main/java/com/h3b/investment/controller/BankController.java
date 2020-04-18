package com.h3b.investment.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.h3b.investment.exception.ResourceNotFoundException;
import com.h3b.investment.model.Bank;
import com.h3b.investment.service.BankService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/api/v1", produces = {"application/json"})
public class BankController {

	@Autowired
	BankService bankService;
	
	@GetMapping("/bank")
	@ApiOperation(value="List all Banks")
	@Validated
	public ResponseEntity<List<Bank>> listBanks(@Valid  @RequestParam(name = "pageNo", defaultValue = "0") int pageNo,
												@Valid @RequestParam(name = "pageSize", defaultValue = "10") int pageSize,
												@Valid @RequestParam(name = "sortBy",defaultValue = "name") String sortBy){
		
		List<Bank> listBank = bankService.listBanks(pageNo, pageSize, sortBy);
		return ResponseEntity.ok().body(listBank);
	}
	
	@GetMapping("/bank/{code}")
	@ApiOperation(value="Get Bank by code")
	public ResponseEntity<Bank> getBankByCode(@Valid @PathVariable("code") String code) throws ResourceNotFoundException{
		Bank bank = bankService.getBankByCode(code);
		return ResponseEntity.ok().body(bank);
	}
	
	
	@PostMapping("/bank")
	@ApiOperation(value="Create Bank")
	public  ResponseEntity<Bank> createBank(@Valid @RequestBody Bank bank){
		Bank bankCreated = bankService.createBank(bank);
		return ResponseEntity.ok().body(bankCreated);
	}
	
}
