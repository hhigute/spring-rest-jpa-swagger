package com.h3b.investment.dto.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.h3b.investment.dto.IrDTO;
import com.h3b.investment.model.Ir;

@Component
public class IrMapper {

	@Autowired
	private ModelMapper modelMapper;
	
	
	public IrDTO convertToDTO(Ir ir) {
		
		if(ir == null)
			return null;
		
		return modelMapper.map(ir, IrDTO.class);
	}
	
	public Ir convertToEntity(IrDTO irDTO) {
		
		if(irDTO == null)
			return null;
		
		return modelMapper.map(irDTO, Ir.class);
	}
	
	
}
