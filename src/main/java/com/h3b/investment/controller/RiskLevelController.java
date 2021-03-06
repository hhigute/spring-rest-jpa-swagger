package com.h3b.investment.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.h3b.investment.dto.DTOEntity;
import com.h3b.investment.dto.RiskLevelDTO;
import com.h3b.investment.dto.mapper.RiskLevelMapper;
import com.h3b.investment.exception.ResourceNotFoundException;
import com.h3b.investment.service.RiskLevelService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/api/v1", produces = {"application/json"})
public class RiskLevelController {

	@Autowired
	RiskLevelService riskLevelService;
	
	@Autowired
	RiskLevelMapper riskLevelMapper;
	
	@GetMapping("/riskLevel")
	@ApiOperation(value="List all Risk Levels")
	public ResponseEntity<List<DTOEntity>> listRiskLevels(	@RequestParam(name = "pageNo", defaultValue= "0") int pageNo,
															@RequestParam(name = "pageSize", defaultValue = "10")int pageSize,
															@RequestParam(name = "sortBy", defaultValue = "description") String sortBy){
		
		
		List<DTOEntity> listRiskLevelDTO = riskLevelService.listRiskLevels(pageNo, pageSize, sortBy)
																.stream()
																.map(node -> riskLevelMapper.convertToDto(node, new RiskLevelDTO()))
																.collect(Collectors.toList());
		return ResponseEntity.ok().body(listRiskLevelDTO);
	}
	
	@GetMapping("/riskLevel/{description}")
	@ApiOperation(value="Get a specific Risk by description")
	public ResponseEntity<DTOEntity> findRiskLevelByDescription(@Valid @PathVariable("description") String description)
															throws ResourceNotFoundException{
		
		DTOEntity riskLevelDTO = riskLevelMapper.convertToDto(riskLevelService.findRiskLevelByDescription(description), new RiskLevelDTO());
		return ResponseEntity.ok().body(riskLevelDTO);
	}
	
}
