package com.h3b.investment.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import com.h3b.investment.exception.ResourceNotFoundException;
import com.h3b.investment.model.Bank;
import com.h3b.investment.service.BankService;

//@RunWith(SpringRunner.class) 
//@DataJpaTest
@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
public class BankRepositoryIntegrationTest {

	@Autowired
	private BankRepository bankRepository;

	@Autowired
	private BankService bankService;

	
	
	@AfterEach
	void tearDown() {
		bankRepository.deleteAll();
	}
	
	
	@Test
	void insertBank_Success() {
		//given
		Bank bank = new Bank("001", "Itau", "Contact Name", "11970293077");
		
		//when
		bankService.createBank(bank);
		Bank bankFound = bankRepository.findById("001").get();
		
		//then
		assertThat(bankFound.getName()).isEqualTo(bank.getName());
		
	}

	@Test
	void listBank_Success() {
		//given
		List<Bank> listBank = new ArrayList<Bank>();
		listBank.add(new Bank("001", "Itau", "Contact Name", "11970293077"));
		listBank.add(new Bank("002", "Bradesco", "New Name", "11123113131"));
		
		bankRepository.saveAll(listBank);
		
		//when
		List<Bank> banksFound = bankService.listBanks(0, 5, "Name");
		
		//then
		assertThat(banksFound).containsAll(listBank);
		
	}
	
	@Test
	void getBankByCode_Success() throws ResourceNotFoundException {
		//given
		Bank bank = new Bank("001", "Itau", "Contact Name", "11970293077");
		bankRepository.save(bank);
		
		//when
		Bank bankFound = bankService.getBankByCode("001");
		
		//then
		assertThat(bankFound).isEqualTo(bank);
		
	}
	
	@Test
	void getBankByCode_Failure() throws ResourceNotFoundException {
		//given
		Bank bank = new Bank("001", "Itau", "Contact Name", "11970293077");
		bankRepository.save(bank);
		
		//then
		assertThrows(ResourceNotFoundException.class, ()->bankService.getBankByCode("002"));
		
	}

	
	
	
}
