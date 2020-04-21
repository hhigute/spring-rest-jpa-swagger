package com.h3b.investment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.h3b.investment.model.ProductBank;

public interface ProductBankRepository extends JpaRepository<ProductBank, Integer>{
	
	@Query(value = "select * from ProductBank where CodeBank = ?1", nativeQuery = true)
	List<ProductBank> findProductBankByBankCode(String codeBank);

}
