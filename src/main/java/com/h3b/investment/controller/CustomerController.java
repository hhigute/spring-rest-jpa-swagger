package com.h3b.investment.controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.PersistenceException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.h3b.investment.dto.CustomerDTO;
import com.h3b.investment.dto.mapper.CustomerMapper;
import com.h3b.investment.exception.ResourceNotFoundException;
import com.h3b.investment.model.Customer;
import com.h3b.investment.service.CustomerService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/api/v1", produces = {"application/json"})
public class CustomerController {

	@Autowired
	CustomerService customerService;
	
	@Autowired
	CustomerMapper customerMapper;
	
	@GetMapping("/customer" )
	@ApiOperation(value="List all Customers")
	public ResponseEntity<List<CustomerDTO>> listCustomer( @RequestParam(name="pageNo", defaultValue = "0") int pageNo,
										@RequestParam(name="pageSize", defaultValue = "10") int pageSize,
										@RequestParam(name="sortBy", defaultValue = "name") String sortBy){
		
			List<CustomerDTO> listCustomerDTO = customerService.listCustomer(pageNo, pageSize, sortBy)
																.stream()
																.map(customerMapper::convertToDTO)
																.collect(Collectors.toList());
			return ResponseEntity.ok().body(listCustomerDTO);
	}
	
	
	@GetMapping("/customer/{doc}")
	@ApiOperation(value="Get Customer by document")
	public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable(name = "doc") String doc) throws ResourceNotFoundException{
		
		CustomerDTO customerDTOResponse = customerMapper.convertToDTO(customerService.getCustomerById(doc));
		return ResponseEntity.ok().body(customerDTOResponse);
		
	}
	
	@PostMapping("/customer")
	@ApiOperation(value="Create Customer")
	public ResponseEntity<CustomerDTO> createCustomer(@Valid @RequestBody CustomerDTO customerDTO) throws PersistenceException   {
		Customer customer = customerMapper.convertToEntity(customerDTO);
		Customer customerCreated = customerService.createCustomer(customer);
		

		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
													.path("/{document}")
													.buildAndExpand(customerCreated.getDocument())
													.toUri();
		return ResponseEntity.created(location).build();
	}
	
	
	@PutMapping("/customer")
	@ApiOperation(value="Update Customer by Document")
	public ResponseEntity<CustomerDTO> updateCustomer(	@Valid @RequestBody CustomerDTO customerDTORequest) 
															throws ResourceNotFoundException{
		Customer customerRequest = customerMapper.convertToEntity(customerDTORequest);
		CustomerDTO customerDTOUpdated = customerMapper.convertToDTO(customerService.updateCustomer(customerRequest));
		return ResponseEntity.ok().body(customerDTOUpdated);
	}
	
}
