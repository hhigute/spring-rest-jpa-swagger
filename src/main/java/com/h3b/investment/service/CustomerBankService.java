package com.h3b.investment.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.h3b.investment.model.CustomerBank;
import com.h3b.investment.repository.CustomerBankRepository;

@Service
public class CustomerBankService {
	@Autowired
	CustomerBankRepository customerBankRepository; 
	
	
	public List<CustomerBank> listCustomerBank( int pageNo, int pageSize, String sortBy){
		Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		Page<CustomerBank> pageResult = customerBankRepository.findAll(pageable);
		if(pageResult.hasContent()) {
			return pageResult.getContent();
		}else {
			return new ArrayList<CustomerBank>();
		}
			
		
	}
	
	public CustomerBank createCustomerBank(CustomerBank customerBank) throws Exception{
	
		return customerBankRepository.save(customerBank);
		
	}

}
