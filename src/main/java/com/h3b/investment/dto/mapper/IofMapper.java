package com.h3b.investment.dto.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.h3b.investment.dto.IofDTO;
import com.h3b.investment.model.Iof;

@Component
public class IofMapper {
	
	@Autowired
    private ModelMapper modelMapper;
	
	
	public IofDTO convertToDTO(Iof iof) {
		
		if(iof == null)
			return null;
		
	    IofDTO iofDTO = modelMapper.map(iof, IofDTO.class);
	    iofDTO.setPorcentagemTaxa(iof.getPercentFee());
	    return iofDTO;
	}
	
	public Iof convertToEntity(IofDTO iofDTO) {
		
		if(iofDTO == null)
			return null;
		
		Iof iof = modelMapper.map(iofDTO, Iof.class);
		iof.setPercentFee(iofDTO.getPorcentagemTaxa());
	    return iof;
	}

}
 