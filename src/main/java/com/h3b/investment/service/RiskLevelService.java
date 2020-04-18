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
import com.h3b.investment.model.RiskLevel;
import com.h3b.investment.repository.RiskLevelRepository;

@Service
public class RiskLevelService {
	@Autowired
	RiskLevelRepository riskLevelRepository;
	
	public List<RiskLevel> listRiskLevels(int pageNo, int pageSize, String sortBy){
		Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		Page<RiskLevel> pageResult = riskLevelRepository.findAll(pageable);
		
		if(pageResult.hasContent()) {
			return pageResult.getContent();
		}else {
			return new ArrayList<RiskLevel>();
		}
		
	}
	
	public RiskLevel findRiskLevelByDescription(String description)
															throws ResourceNotFoundException{
		RiskLevel riskLevel = riskLevelRepository.findRiskLevelByDescription(description);
		
		if(riskLevel == null)
			throw new ResourceNotFoundException("RiskLevel not found for description: "+description);	
		
		return riskLevel;
	}
}
