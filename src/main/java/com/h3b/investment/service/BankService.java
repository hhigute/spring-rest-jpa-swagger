package com.h3b.investment.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.h3b.investment.exception.ResourceNotFoundException;
import com.h3b.investment.model.Bank;
import com.h3b.investment.repository.BankRepository;

@Service
public class BankService {

	@Autowired
	BankRepository bankRepository;
	
	public List<Bank> listBanks(Integer pageNo, Integer pageSize, String sortBy){
		
		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		Page<Bank> pageResult = bankRepository.findAll(paging);
		
		if(pageResult.hasContent()) {
			return pageResult.getContent();
		}else {
			return new ArrayList<Bank>();
		}
			
	}
	

	public Bank getBankByCode(String code) throws ResourceNotFoundException{
		Bank bankResponse = bankRepository.findById(code)
											.orElseThrow(
												()-> new ResourceNotFoundException("Bank not found for code: "+code));
		return bankResponse;
	}
	
	
	public Bank createBank(Bank bank){
		return bankRepository.save(bank);
	}

}
