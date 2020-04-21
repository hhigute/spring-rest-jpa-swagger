package com.h3b.investment.dto;

import com.h3b.investment.model.Bank;
import com.h3b.investment.model.Customer;

public class CustomerBankDTO {
	
	private String customerDoc;
	private String codeBank;
	private String branchCode;
	private String accountNumber;
	private Customer customer;
	private Bank bank;
	
	public String getCustomerDoc() {
		return customerDoc;
	}
	public void setCustomerDoc(String customerDoc) {
		this.customerDoc = customerDoc;
	}
	public String getCodeBank() {
		return codeBank;
	}
	public void setCodeBank(String codeBank) {
		this.codeBank = codeBank;
	}
	public String getBranchCode() {
		return branchCode;
	}
	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public Bank getBank() {
		return bank;
	}
	public void setBank(Bank bank) {
		this.bank = bank;
	}
	
	

}
