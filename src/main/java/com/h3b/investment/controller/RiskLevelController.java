package com.h3b.investment.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.h3b.investment.exception.ResourceNotFoundException;
import com.h3b.investment.model.RiskLevel;
import com.h3b.investment.repository.RiskLevelRepository;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/api/v1", produces = {"application/json"})
public class RiskLevelController {

	@Autowired
	RiskLevelRepository riskLevelRepository;
	
	@GetMapping("/riskLevel")
	@ApiOperation(value="List all Risk Levels")
	public List<RiskLevel> listRiskLevels(){
		return riskLevelRepository.findAll();
	}
	
	@GetMapping("/riskLevel/{description}")
	@ApiOperation(value="Get a specific Risk by description")
	public ResponseEntity<RiskLevel> findRiskLevelByDescription(@Valid @PathVariable("description") String description)
															throws ResourceNotFoundException{
		RiskLevel riskLevel = riskLevelRepository.findRiskLevelByDescription(description);
		
		if(riskLevel == null)
			throw new ResourceNotFoundException("RiskLevel not found for description: "+description);	
		
		return ResponseEntity.ok().body(riskLevel);
	}
	
}
