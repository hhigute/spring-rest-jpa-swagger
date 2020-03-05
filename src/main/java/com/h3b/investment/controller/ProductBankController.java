package com.h3b.investment.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.h3b.investment.exception.ResourceNotFoundException;
import com.h3b.investment.model.ProductBank;
import com.h3b.investment.repository.ProductBankRepository;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value =  "/api/v1" , produces = {"application/json"})
public class ProductBankController {


	@Autowired
	ProductBankRepository productBankRepository;
	
	
	@GetMapping("/productBank")
	@ApiOperation(value="List all Products")
	public List<ProductBank> listProductBank(){
		return productBankRepository.findAll();
	}
	
	@GetMapping("/productBank/{id}")
	@ApiOperation(value="Get Product by ID")
	public ResponseEntity<ProductBank> getProductBankById(@Valid @PathVariable("id") int id) throws ResourceNotFoundException{
		ProductBank productBank = productBankRepository.findById(id)
											.orElseThrow(()-> new ResourceNotFoundException("Product Bank not found for id: "+id));
		return ResponseEntity.ok().body(productBank);
	}
	
	
	@GetMapping("/productBank/codeBank/{codeBank}")
	@ApiOperation(value="List all Products from a specific Bank")
	public ResponseEntity<ProductBank> getProductBankByBankCode(@Valid @PathVariable("codeBank") String codeBank) throws ResourceNotFoundException{
		ProductBank productBank = productBankRepository.findProductBankByBankCode(codeBank);
		
		if(productBank == null)
			throw new ResourceNotFoundException("Product Bank not found for bank: "+codeBank);
		
		return ResponseEntity.ok().body(productBank);
	}
	
	@PostMapping("/productBank")
	@ApiOperation(value="Create Product from a specific Bank")
	public ProductBank createProductBank(@Valid @RequestBody ProductBank productBank) {
		return productBankRepository.save(productBank);
	}
	
	@PutMapping("/productBank/{id}")
	@ApiOperation(value="Update Product by ID")
	public ResponseEntity<ProductBank> updateProductBank(@PathVariable("id") int id, @Valid @RequestBody ProductBank productBankRequest) 
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
		
		final ProductBank productBankUpdated = productBankRepository.save(productBank);
		
		return ResponseEntity.ok().body(productBankUpdated);
	}
	
	@DeleteMapping("/productBank/{id}")
	@ApiOperation(value="Delete Product by ID")
	public Map<String, Boolean> deleteProductBank(@PathVariable("id") int id) throws ResourceNotFoundException{
		ProductBank productBank = productBankRepository.findById(id)
												.orElseThrow(()->new ResourceNotFoundException("Product Bank not found for id: "+id));
		
		productBankRepository.delete(productBank);
		
		Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
		
	}
	
	
}
