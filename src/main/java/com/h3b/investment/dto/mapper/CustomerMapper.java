package com.h3b.investment.dto.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.h3b.investment.dto.CustomerDTO;
import com.h3b.investment.model.Customer;

@Component
public class CustomerMapper {

	@Autowired
	private ModelMapper modelMapper;
	
	
	public CustomerDTO convertToDTO(Customer customer) {
		
		if(customer == null)
			return null;
		
		return modelMapper.map(customer, CustomerDTO.class);
	}
	
	public Customer convertToEntity(CustomerDTO customerDTO) {
		
		
		if(customerDTO == null)
			return null;
		
		return modelMapper.map(customerDTO, Customer.class);
	}
	
}
