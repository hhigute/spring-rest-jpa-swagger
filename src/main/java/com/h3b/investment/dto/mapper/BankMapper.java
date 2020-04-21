package com.h3b.investment.dto.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.h3b.investment.dto.BankDTO;
import com.h3b.investment.model.Bank;

@Component
public class BankMapper {

	@Autowired
	private ModelMapper modelMapper;
	
	public BankDTO convertToDTO(Bank bank) {
		
		if(bank == null)
			return null;
		
		return modelMapper.map(bank, BankDTO.class);
	}
	
	public Bank convertToEntity(BankDTO bankDTO) {
		
		if(bankDTO == null)
			return null;
		
		return modelMapper.map(bankDTO, Bank.class);
	}
	
	
}
