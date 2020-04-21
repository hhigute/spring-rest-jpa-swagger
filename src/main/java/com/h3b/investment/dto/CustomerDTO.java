package com.h3b.investment.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class CustomerDTO {

	private String document;
	private String name;
	private String phone;
	
	@JsonIgnoreProperties("customers")
	private List<BankDTO> banks = new ArrayList<BankDTO>();
	
	
	public String getDocument() {
		return document;
	}
	public void setDocument(String document) {
		this.document = document;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public List<BankDTO> getBanks() {
		return banks;
	}
	public void setBanks(List<BankDTO> banks) {
		this.banks = banks;
	}
	
	

	
}
