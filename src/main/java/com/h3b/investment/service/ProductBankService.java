package com.h3b.investment.service;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.h3b.investment.exception.ResourceNotFoundException;
import com.h3b.investment.model.ProductBank;
import com.h3b.investment.repository.ProductBankRepository;

@Service
public class ProductBankService {
	
	@Autowired
	ProductBankRepository productBankRepository;
	
	
	public List<ProductBank> listProductBank(int pageNo, int pageSize, String sortBy){
		Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		Page<ProductBank> pageResult = productBankRepository.findAll(pageable); 
		
		if(pageResult.hasContent()) {
			return pageResult.getContent();
		}else {
			return new ArrayList<ProductBank>();	
		}
		
	}
	
	public ProductBank getProductBankById( int id) throws ResourceNotFoundException{
		ProductBank productBank = productBankRepository.findById(id)
											.orElseThrow(()-> new ResourceNotFoundException("Product Bank not found for id: "+id));
		return productBank;
	}
	
	
	public List<ProductBank> getProductBankByBankCode(String codeBank) throws ResourceNotFoundException{
		List<ProductBank> listProductBank = productBankRepository.findProductBankByBankCode(codeBank);
		
		if(listProductBank == null)
			throw new ResourceNotFoundException("Product Bank not found for bank: "+codeBank);
		
		return listProductBank;
	}
	
	public ProductBank createProductBank(@Valid @RequestBody ProductBank productBank) {
		return productBankRepository.save(productBank);
	}
	
	public ProductBank updateProductBank(int id, ProductBank productBankRequest) 
			throws ResourceNotFoundException {
		
		ProductBank productBank = productBankRepository.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("Product Bank not found for id: "+id));
		
		productBank.setId(productBankRequest.getId());
		productBank.setCodeBank(productBankRequest.getCodeBank());
		productBank.setDescription(productBankRequest.getDescription());
		productBank.setAdministrationFee(productBankRequest.getAdministrationFee());
		productBank.setDueDate(productBankRequest.getDueDate());
		productBank.setLiquidityDay(productBankRequest.getLiquidityDay());
		productBank.setIdRiskLevel(productBankRequest.getIdRiskLevel());
		productBank.setEnabled(productBankRequest.getEnabled());
		
		return productBankRepository.save(productBank);
		
	}
	
	public void deleteProductBank(int id) throws ResourceNotFoundException{
		ProductBank productBank = productBankRepository.findById(id)
												.orElseThrow(()->new ResourceNotFoundException("Product Bank not found for id: "+id));
		
		productBankRepository.delete(productBank);
		
	}
}
