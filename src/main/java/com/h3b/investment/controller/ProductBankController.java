package com.h3b.investment.controller;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.h3b.investment.dto.DTOEntity;
import com.h3b.investment.dto.ProductBankDTO;
import com.h3b.investment.dto.mapper.ProductBankMapper;
import com.h3b.investment.exception.ResourceNotFoundException;
import com.h3b.investment.model.ProductBank;
import com.h3b.investment.service.ProductBankService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value =  "/api/v1" , produces = {"application/json"})
public class ProductBankController {


	@Autowired
	ProductBankService productBankSerice;
	
	@Autowired
	ProductBankMapper productBankMapper;
	
	
	@GetMapping("/productBank")
	@ApiOperation(value="List all Products")
	public ResponseEntity<List<DTOEntity>> listProductBank(	@RequestParam(name = "pageNo", defaultValue = "0") int pageNo,
																@RequestParam(name = "pageSize", defaultValue = "10") int pageSize,
																@RequestParam(name = "sortBy", defaultValue = "description") String sortBy){
		
		List<DTOEntity> listProductBankDTO = productBankSerice.listProductBank(pageNo, pageSize, sortBy)
																.stream()
																.map(node -> productBankMapper.convertToDto(node, new ProductBankDTO()))
																.collect(Collectors.toList());
		return ResponseEntity.ok().body(listProductBankDTO);
	}
	
	@GetMapping("/productBank/{id}")
	@ApiOperation(value="Get Product by ID")
	public ResponseEntity<DTOEntity> getProductBankById(@Valid @PathVariable("id") int id) throws ResourceNotFoundException{
		DTOEntity productBankDTO =  productBankMapper.convertToDto(productBankSerice.getProductBankById(id), new ProductBankDTO());
		return ResponseEntity.ok().body(productBankDTO);
	}
	
	
	@GetMapping("/productBank/codeBank/{codeBank}")
	@ApiOperation(value="List all Products from a specific Bank")
	public ResponseEntity<List<DTOEntity>> getProductBankByBankCode(@Valid @PathVariable("codeBank") String codeBank) throws ResourceNotFoundException{
		
		List<DTOEntity> listProductBankDTO = productBankSerice.getProductBankByBankCode(codeBank)
																.stream()
																.map(node -> productBankMapper.convertToDto(node,new ProductBankDTO()))
																.collect(Collectors.toList());
		
		return ResponseEntity.ok().body(listProductBankDTO);
	}
	
	@PostMapping("/productBank")
	@ApiOperation(value="Create Product from a specific Bank")
	public ResponseEntity<ProductBankDTO> createProductBank(@Valid @RequestBody ProductBankDTO productBankDTO) {
		ProductBank productBank = (ProductBank) productBankMapper.convertToEntity(new ProductBank() ,productBankDTO);
		ProductBank createdProductBank = productBankSerice.createProductBank(productBank);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
													.path("/{id}")
													.buildAndExpand(createdProductBank.getId())
													.toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping("/productBank/{id}")
	@ApiOperation(value="Update Product by ID")
	public ResponseEntity<DTOEntity> updateProductBank(@PathVariable("id") int id, @Valid @RequestBody ProductBankDTO productBankDTO) 
			throws ResourceNotFoundException {

		ProductBank productBank = (ProductBank) productBankMapper.convertToEntity(new ProductBank(),productBankDTO);
		ProductBank productBankUpdated = productBankSerice.updateProductBank(id, productBank);
		DTOEntity responseProductBankDTO = productBankMapper.convertToDto(productBankUpdated, new ProductBankDTO());
		return ResponseEntity.ok().body(responseProductBankDTO);
	}
	
	@DeleteMapping("/productBank/{id}")
	@ApiOperation(value="Delete Product by ID")
	public Map<String, Boolean> deleteProductBank(@PathVariable("id") int id) throws ResourceNotFoundException{
		
		productBankSerice.deleteProductBank(id);
		
		Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
		
	}
	
	
}
