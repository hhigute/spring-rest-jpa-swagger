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

import com.h3b.investment.dto.IrDTO;
import com.h3b.investment.dto.mapper.IrMapper;
import com.h3b.investment.exception.ResourceNotFoundException;
import com.h3b.investment.service.IrService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/api/v1", produces = {"application/json"})
public class IrController {
	
	@Autowired
	IrService irService;
	
	@Autowired
	IrMapper irMapper;
	
	@GetMapping("/ir")
	@ApiOperation(value="List all IR fees")
	public ResponseEntity<List<IrDTO>> listIrs(	@RequestParam(name="pageNo",defaultValue="0") int pageNo,
												@RequestParam(name="pageSize",defaultValue="10") int pageSize,
												@RequestParam(name="sortBy",defaultValue="startDay") String sortBy){
		
		List<IrDTO> listIrDTO = irService.listIrs(pageNo, pageSize, sortBy)
											.stream()
											.map(irMapper::convertToDTO)
											.collect(Collectors.toList());
		
		return ResponseEntity.ok().body(listIrDTO);
	}
	
	
	@GetMapping("/ir/{day}")
	@ApiOperation(value="Get IR fee by day")
	public ResponseEntity<IrDTO> findByRangeDay(@Valid @PathVariable(value="day") int day) throws ResourceNotFoundException {
		IrDTO irDTO = irMapper.convertToDTO(irService.findByRangeDay(day));
		return ResponseEntity.ok().body(irDTO);		
	}
	
	
}
