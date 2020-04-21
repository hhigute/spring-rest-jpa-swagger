package com.h3b.investment.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity(name = "Bank")
public class Bank {

	@Id
	@Column(name = "Code", nullable = false)
	@Length(max = 3, message = "Code {javax.validation.constraints.Length.message}")
	@NotBlank(message = "Code {javax.validation.constraints.NotBlank.message}")
	private String code;

	@Column(name = "Name", precision = 50, nullable = false)
	@Length(max = 50, message = "Name {javax.validation.constraints.Length.message}")
	@NotBlank(message = "Name {javax.validation.constraints.NotBlank.message}")
	private String name;

	@Column(name = "ContactName", precision = 50, nullable = false)
	@Length(max = 50, message = "ContactName {javax.validation.constraints.Length.message}")
	@NotBlank(message = "ContactName {javax.validation.constraints.NotBlank.message}")
	private String contactName;

	@Column(name = "ContactPhone", precision = 50, nullable = false)
	@Length(max = 15, message = "ContactPhone {javax.validation.constraints.Length.message}")
	@NotBlank(message = "ContactPhone {javax.validation.constraints.NotBlank.message}")
	private String contactPhone;

	@JsonIgnoreProperties("banks")
	@ManyToMany(mappedBy="banks", fetch = FetchType.EAGER,cascade = {CascadeType.ALL})
	private List<Customer> customers = new ArrayList<Customer>();
	
	public Bank() {

	}

	public Bank(
			@Length(max = 3, message = "Code {javax.validation.constraints.Length.message}") @NotBlank(message = "Code {javax.validation.constraints.NotBlank.message}") String code,
			@Length(max = 50, message = "Name {javax.validation.constraints.Length.message}") @NotBlank(message = "Name {javax.validation.constraints.NotBlank.message}") String name,
			@Length(max = 50, message = "ContactName {javax.validation.constraints.Length.message}") @NotBlank(message = "ContactName {javax.validation.constraints.NotBlank.message}") String contactName,
			@Length(max = 15, message = "ContactPhone {javax.validation.constraints.Length.message}") @NotBlank(message = "ContactPhone {javax.validation.constraints.NotBlank.message}") String contactPhone) {
		this.code = code;
		this.name = name;
		this.contactName = contactName;
		this.contactPhone = contactPhone;
	}

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
	
	public List<Customer> getCustomers(){
		return customers;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + ((contactName == null) ? 0 : contactName.hashCode());
		result = prime * result + ((contactPhone == null) ? 0 : contactPhone.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Bank other = (Bank) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		if (contactName == null) {
			if (other.contactName != null)
				return false;
		} else if (!contactName.equals(other.contactName))
			return false;
		if (contactPhone == null) {
			if (other.contactPhone != null)
				return false;
		} else if (!contactPhone.equals(other.contactPhone))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Bank [code=" + code + ", name=" + name + ", contactName=" + contactName + ", contactPhone="
				+ contactPhone + "]";
	}

}
