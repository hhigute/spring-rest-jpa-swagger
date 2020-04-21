package com.h3b.investment.dto.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.h3b.investment.dto.CustomerBankDTO;
import com.h3b.investment.model.CustomerBank;

@Component
public class CustomerBankMapper {

	
	@Autowired
	private ModelMapper modelMapper;
	
	public CustomerBankDTO convertToDTO(CustomerBank customerBank) {
		
		if(customerBank == null)
			return null;
		
		return modelMapper.map(customerBank, CustomerBankDTO.class);
	}
	
	
	public CustomerBank convertToEntity(CustomerBankDTO customerBankDTO) {
		
		if(customerBankDTO == null)
			return null;
		
		return modelMapper.map(customerBankDTO, CustomerBank.class);
	}
	
}
