package com.h3b.investment.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.h3b.investment.exception.ResourceNotFoundException;
import com.h3b.investment.model.Customer;
import com.h3b.investment.repository.CustomerRepository;
import com.microsoft.sqlserver.jdbc.SQLServerException;

@Service
@Transactional
public class CustomerService {
	
	@Autowired
	CustomerRepository customerRepository;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public List<Customer> listCustomer(int pageNo, int pageSize, String sortBy){
		Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		Page<Customer> pageReturn = customerRepository.findAll(pageable);
		
		if(pageReturn.hasContent()) {
			return pageReturn.getContent();
		}else {
			return new ArrayList<Customer>();	
		}
		
	}
	
	
	public Customer getCustomerById(@PathVariable(name = "doc") String doc) throws ResourceNotFoundException{
		
		Customer customerResponse = customerRepository.findById(doc)
														.orElseThrow(
																()-> new ResourceNotFoundException("Customer not found for document: "+doc)
															
														);
		
		return customerResponse;
		
	}
	
	
	public Customer createCustomer(Customer customer) throws PersistenceException {
		try {
			this.entityManager.persist(customer);
			this.entityManager.flush();
		}catch(Exception ex ) {
			for(Throwable t = ex.getCause(); t!= null; t=t.getCause()) {
				if(SQLServerException.class.equals(t.getClass())) {
					throw new PersistenceException(t.getMessage());
				}
			}
		}
		return customer; 
	}
	
	
	public Customer updateCustomer(Customer customerRequest) 
															throws ResourceNotFoundException{
		
		String document = customerRequest.getDocument();
		Customer customer = customerRepository.findById(document)
												.orElseThrow(
														()-> new ResourceNotFoundException("Customer not found for document: "+document));
		
		customer.setName(customerRequest.getName());
		customer.setPhone(customerRequest.getPhone());
		
		return customerRepository.save(customer);
		
	}
}
