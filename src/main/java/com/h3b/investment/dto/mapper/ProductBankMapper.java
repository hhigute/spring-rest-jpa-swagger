package com.h3b.investment.dto.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.h3b.investment.dto.ProductBankDTO;
import com.h3b.investment.model.ProductBank;

@Component
public class ProductBankMapper {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public ProductBankDTO convertToDTO(ProductBank productBank) {
		
		if(productBank == null)
			return null;
		
		return modelMapper.map(productBank, ProductBankDTO.class);
	}

	public ProductBank convertToEntity(ProductBankDTO productBankDTO) {
		
		if(productBankDTO == null)
			return null;
		
		return modelMapper.map(productBankDTO, ProductBank.class);
	}
}
