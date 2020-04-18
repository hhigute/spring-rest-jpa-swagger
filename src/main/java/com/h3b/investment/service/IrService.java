package com.h3b.investment.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.h3b.investment.exception.ResourceNotFoundException;
import com.h3b.investment.model.Ir;
import com.h3b.investment.repository.IrRepository;

@Service
public class IrService {
	@Autowired
	IrRepository irRepository;
	
	
	public List<Ir> listIrs(int pageNo, int pageSize, String sortBy){
		
		Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		
		Page<Ir> pageReturn = irRepository.findAll(pageable); 
		
		if(pageReturn.hasContent()) {
			return pageReturn.getContent();
		}else {
			return new ArrayList<Ir>();
		}
		
		
	}
	
	
	public Ir findByRangeDay(int day) throws ResourceNotFoundException {
		
		Ir ir = irRepository.findByRangeDay(day);
		
		if(ir == null)
			throw new ResourceNotFoundException("IR not found for this day: "+day);
		
		return ir;
		
	}
}
