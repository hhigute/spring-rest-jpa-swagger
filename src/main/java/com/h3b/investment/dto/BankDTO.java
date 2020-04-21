package com.h3b.investment.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class BankDTO implements DTOEntity{


	private String code;
	private String name;
	private String contactName;
	private String contactPhone;
	
	@JsonIgnoreProperties("banks")
	private List<CustomerDTO> customers = new ArrayList<CustomerDTO>();
	
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContactName() {
		return contactName;
	}
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	public String getContactPhone() {
		return contactPhone;
	}
	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}
	public List<CustomerDTO> getCustomers() {
		return customers;
	}
	public void setCustomers(List<CustomerDTO> customers) {
		this.customers = customers;
	}
	
	
	
	
}
