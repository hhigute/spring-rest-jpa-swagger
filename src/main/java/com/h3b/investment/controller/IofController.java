package com.h3b.investment.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.h3b.investment.exception.ResourceNotFoundException;
import com.h3b.investment.model.Iof;
import com.h3b.investment.repository.IofRepository;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/api/v1", produces = {"application/json"})
public class IofController{

	@Autowired
	private IofRepository iofRepository;
	
	@GetMapping("/iof")
	@ApiOperation(value="List all IOF fees")
	public List<Iof> listIofs(){
		return iofRepository.findAll();
	}
	
	@GetMapping("/iof/pageable")
	@ApiOperation(value="List all IOF fees")
	public List<Iof> listIofsPageable(
            @RequestParam(defaultValue = "0") Integer pageNo, 
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "nrDay") String sortBy){
		
		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		Page<Iof> pagedResult = iofRepository.findAll(paging);
		
		if(pagedResult.hasContent()) {
	        return pagedResult.getContent();
	    } else {
	    	return new ArrayList<Iof>();
	    }
	}

	@GetMapping("/iof/{nrDay}")
	@ApiOperation(value="Get IOF fee by day")
	public ResponseEntity<Iof> getIofByNrDay(@PathVariable(value="nrDay") int nrDay) throws ResourceNotFoundException {
		Iof iof = iofRepository.findById(nrDay)
				.orElseThrow(
						()-> new ResourceNotFoundException ("Iof not found for this day: "+nrDay)
				);
		return ResponseEntity.ok().body(iof);
	}
}