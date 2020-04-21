package com.h3b.investment.dto.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.h3b.investment.dto.RiskLevelDTO;
import com.h3b.investment.model.RiskLevel;

@Component
public class RiskLevelMapper {

	@Autowired
	private ModelMapper modelMapper;
	
	public RiskLevelDTO convertToDTO(RiskLevel riskLevel) {
		
		if(riskLevel == null)
			return null;
		
		return modelMapper.map(riskLevel, RiskLevelDTO.class);
	}
	
	public RiskLevel convertToEntity(RiskLevelDTO riskLevelDTO) {
		
		if(riskLevelDTO == null)
			return null;
		
		return modelMapper.map(riskLevelDTO, RiskLevel.class);
	}
}
