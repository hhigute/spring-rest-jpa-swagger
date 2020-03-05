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
import org.springframework.web.bind.annotation.RestController;

import com.h3b.investment.exception.ResourceNotFoundException;
import com.h3b.investment.model.Customer;
import com.h3b.investment.repository.CustomerRepository;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/api/v1", produces = {"application/json"})
public class CustomerController {

	@Autowired
	CustomerRepository customerRepository;
	
	@GetMapping("/customer" )
	@ApiOperation(value="List all Customers")
	public List<Customer> listCustomer(){
		return customerRepository.findAll();
	}
	
	
	@GetMapping("/customer/{doc}")
	@ApiOperation(value="Get Customer by document")
	public ResponseEntity<Customer> getCustomerById(@PathVariable(name = "doc") String doc) throws ResourceNotFoundException{
		
		Customer customerResponse = customerRepository.findById(doc)
														.orElseThrow(
																()-> new ResourceNotFoundException("Customer not found for document: "+doc)
															
														);
		
		return ResponseEntity.ok().body(customerResponse);
		
	}
	
	@PostMapping("/customer")
	@ApiOperation(value="Create Customer")
	public Customer createCustomer(@Valid @RequestBody Customer customer) {
		return customerRepository.save(customer);
	}
	
	
	@PutMapping("/customer/{doc}")
	@ApiOperation(value="Update Customer by Document")
	public ResponseEntity<Customer> updateCustomer(	@PathVariable(name="doc") String doc,
													@Valid @RequestBody Customer customerRequest) 
															throws ResourceNotFoundException{
		
		Customer customer = customerRepository.findById(doc)
												.orElseThrow(
														()-> new ResourceNotFoundException("Customer not found for document: "+doc));
		
		customer.setDocument(customerRequest.getDocument());
		customer.setName(customerRequest.getName());
		customer.setPhone(customerRequest.getPhone());
		
		final Customer customerUpdated = customerRepository.save(customer);
		
		return ResponseEntity.ok().body(customerUpdated);
	}
	
}
