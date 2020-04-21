package com.h3b.investment.controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.h3b.investment.dto.BankDTO;
import com.h3b.investment.dto.mapper.BankMapper;
import com.h3b.investment.exception.ResourceNotFoundException;
import com.h3b.investment.model.Bank;
import com.h3b.investment.service.BankService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/api/v1", produces = {"application/json"})
public class BankController {

	@Autowired
	BankService bankService;
	
	@Autowired
	BankMapper bankMapper;
	
	@GetMapping("/bank")
	@ApiOperation(value="List all Banks")
	@Validated
	public ResponseEntity<List<BankDTO>> listBanks(@Valid  @RequestParam(name = "pageNo", defaultValue = "0") int pageNo,
												@Valid @RequestParam(name = "pageSize", defaultValue = "10") int pageSize,
												@Valid @RequestParam(name = "sortBy",defaultValue = "name") String sortBy){
		
		List<BankDTO> listBankDTO = bankService.listBanks(pageNo, pageSize, sortBy)
												.stream()
												.map(bankMapper::convertToDTO)
												.collect(Collectors.toList());
												
		return ResponseEntity.ok().body(listBankDTO);
	}
	
	@GetMapping("/bank/{code}")
	@ApiOperation(value="Get Bank by code")
	public ResponseEntity<BankDTO> getBankByCode(@Valid @PathVariable("code") String code) throws ResourceNotFoundException{
		BankDTO bankDTO = bankMapper.convertToDTO(bankService.getBankByCode(code));
		return ResponseEntity.ok().body(bankDTO);
	}
	
	
	@PostMapping("/bank")
	@ApiOperation(value="Create Bank")
	public  ResponseEntity<BankDTO> createBank(@Valid @RequestBody BankDTO bankDTO){
		Bank bank = bankMapper.convertToEntity(bankDTO);
		Bank bankCreated =bankService.createBank(bank);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{code}")
				.buildAndExpand(bankCreated.getCode())
				.toUri();

		return ResponseEntity.created(location).build();
		

	}
	
}
