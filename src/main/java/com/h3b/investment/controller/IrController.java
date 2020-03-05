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
import com.h3b.investment.model.Ir;
import com.h3b.investment.repository.IrRepository;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/api/v1", produces = {"application/json"})
public class IrController {
	
	@Autowired
	IrRepository irRepository;
	
	
	@GetMapping("/ir")
	@ApiOperation(value="List all IR fees")
	public List<Ir> listIrs(){
		return irRepository.findAll();
	}
	
	
	@GetMapping("/ir/{day}")
	@ApiOperation(value="Get IR fee by day")
	public ResponseEntity<Ir> findByRangeDay(@Valid @PathVariable(value="day") int day) throws ResourceNotFoundException {
		
		Ir ir = irRepository.findByRangeDay(day);
		
		if(ir == null)
			throw new ResourceNotFoundException("IR not found for this day: "+day);
		
		return ResponseEntity.ok().body(ir);
		
	}
	
	
}
