package com.h3b.investment.controller;

import java.util.List;

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

import com.h3b.investment.exception.ResourceNotFoundException;
import com.h3b.investment.model.Customer;
import com.h3b.investment.service.CustomerService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/api/v1", produces = {"application/json"})
public class CustomerController {

	@Autowired
	CustomerService customerService;
	
	@GetMapping("/customer" )
	@ApiOperation(value="List all Customers")
	public ResponseEntity<List<Customer>> listCustomer( @RequestParam(name="pageNo", defaultValue = "0") int pageNo,
										@RequestParam(name="pageSize", defaultValue = "10") int pageSize,
										@RequestParam(name="sortBy", defaultValue = "name") String sortBy){
		
			List<Customer> listCustomer = customerService.listCustomer(pageNo, pageSize, sortBy);
			return ResponseEntity.ok().body(listCustomer);
	}
	
	
	@GetMapping("/customer/{doc}")
	@ApiOperation(value="Get Customer by document")
	public ResponseEntity<Customer> getCustomerById(@PathVariable(name = "doc") String doc) throws ResourceNotFoundException{
		
		Customer customerResponse = customerService.getCustomerById(doc);
		return ResponseEntity.ok().body(customerResponse);
		
	}
	
	@PostMapping("/customer")
	@ApiOperation(value="Create Customer")
	public ResponseEntity<Customer> createCustomer(@Valid @RequestBody Customer customer) {
		Customer customerCreated = customerService.createCustomer(customer);
		return ResponseEntity.ok().body(customerCreated);
	}
	
	
	@PutMapping("/customer/{doc}")
	@ApiOperation(value="Update Customer by Document")
	public ResponseEntity<Customer> updateCustomer(	@PathVariable(name="doc") String doc,
													@Valid @RequestBody Customer customerRequest) 
															throws ResourceNotFoundException{
		
		Customer customerUpdated = customerService.updateCustomer(doc, customerRequest);
		return ResponseEntity.ok().body(customerUpdated);
	}
	
}
