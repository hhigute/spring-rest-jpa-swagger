package com.h3b.investment.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.h3b.investment.exception.ResourceNotFoundException;
import com.h3b.investment.model.RiskLevel;
import com.h3b.investment.service.RiskLevelService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/api/v1", produces = {"application/json"})
public class RiskLevelController {

	@Autowired
	RiskLevelService riskLevelService;
	
	@GetMapping("/riskLevel")
	@ApiOperation(value="List all Risk Levels")
	public ResponseEntity<List<RiskLevel>> listRiskLevels(	@RequestParam(name = "pageNo", defaultValue= "0") int pageNo,
															@RequestParam(name = "pageSize", defaultValue = "10")int pageSize,
															@RequestParam(name = "sortBy", defaultValue = "description") String sortBy){
		
		
		List<RiskLevel> listRiskLevel = riskLevelService.listRiskLevels(pageNo, pageSize, sortBy);
		return ResponseEntity.ok().body(listRiskLevel);
	}
	
	@GetMapping("/riskLevel/{description}")
	@ApiOperation(value="Get a specific Risk by description")
	public ResponseEntity<RiskLevel> findRiskLevelByDescription(@Valid @PathVariable("description") String description)
															throws ResourceNotFoundException{
		RiskLevel riskLevel = riskLevelService.findRiskLevelByDescription(description);
		return ResponseEntity.ok().body(riskLevel);
	}
	
}
