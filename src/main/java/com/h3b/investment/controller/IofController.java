package com.h3b.investment.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.h3b.investment.dto.IofDTO;
import com.h3b.investment.dto.mapper.IofMapper;
import com.h3b.investment.exception.ResourceNotFoundException;
import com.h3b.investment.model.Iof;
import com.h3b.investment.service.IofService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/api/v1", produces = {"application/json"})
public class IofController{

	@Autowired
	private IofService iofService;
	
	@Autowired
    private IofMapper iofMapper;

	@GetMapping("/iof")
	@ApiOperation(value="List all IOF fees")
	public ResponseEntity<List<IofDTO>> listIofs( 	@RequestParam(defaultValue = "0") Integer pageNo, 
            									@RequestParam(defaultValue = "10") Integer pageSize,
            									@RequestParam(defaultValue = "nrDay") String sortBy){

		List<Iof> listIof = iofService.listIofs(pageNo, pageSize, sortBy);
		List<IofDTO> listIofDTO = listIof.stream()
											.map(iofMapper::convertToIofDTO)
											.collect(Collectors.toList());
		return ResponseEntity.ok().body(listIofDTO);
		
	}

	@GetMapping("/iof/{nrDay}")
	@ApiOperation(value="Get IOF fee by day")
	public ResponseEntity<IofDTO> getIofByNrDay(@PathVariable(value="nrDay") int nrDay) throws ResourceNotFoundException {
		IofDTO iofDTO =  iofMapper.convertToIofDTO(iofService.getIofByNrDay(nrDay));
		return ResponseEntity.ok().body(iofDTO);
	}
	
	
	
}