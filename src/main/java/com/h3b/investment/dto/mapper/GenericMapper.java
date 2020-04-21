package com.h3b.investment.dto.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.h3b.investment.dto.DTOEntity;

public abstract class GenericMapper  {
	@Autowired
	private ModelMapper modelMapper;

	public DTOEntity convertToDto(Object entity, DTOEntity dto) {
		return modelMapper.map(entity, dto.getClass());
	}

	public Object convertToEntity(Object entity, DTOEntity dto) {
		return modelMapper.map(dto, entity.getClass());
	}

}
