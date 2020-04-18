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
import com.h3b.investment.model.Ir;
import com.h3b.investment.service.IrService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/api/v1", produces = {"application/json"})
public class IrController {
	
	@Autowired
	IrService irService;
	
	
	@GetMapping("/ir")
	@ApiOperation(value="List all IR fees")
	public ResponseEntity<List<Ir>> listIrs(@RequestParam(name="pageNo",defaultValue="0") int pageNo,
							@RequestParam(name="pageSize",defaultValue="0") int pageSize,
							@RequestParam(name="sortBy",defaultValue="0") String sortBy){
		
		List<Ir> listIr = irService.listIrs(pageNo, pageSize, sortBy);
		
		return ResponseEntity.ok().body(listIr);
	}
	
	
	@GetMapping("/ir/{day}")
	@ApiOperation(value="Get IR fee by day")
	public ResponseEntity<Ir> findByRangeDay(@Valid @PathVariable(value="day") int day) throws ResourceNotFoundException {
		Ir ir = irService.findByRangeDay(day);
		return ResponseEntity.ok().body(ir);		
	}
	
	
}
