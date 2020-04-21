package com.h3b.investment.dto;

import java.util.Date;

public class ProductBankDTO {
	
	private int id;
	private String codeBank;
	private String description;
	private double administrationFee;
	private Date dueDate;
	private int liquidityDay;
	private boolean enabled;
	private int idRiskLevel;
	private RiskLevelDTO riskLevel;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCodeBank() {
		return codeBank;
	}
	public void setCodeBank(String codeBank) {
		this.codeBank = codeBank;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getAdministrationFee() {
		return administrationFee;
	}
	public void setAdministrationFee(double administrationFee) {
		this.administrationFee = administrationFee;
	}
	public Date getDueDate() {
		return dueDate;
	}
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	public int getLiquidityDay() {
		return liquidityDay;
	}
	public void setLiquidityDay(int liquidityDay) {
		this.liquidityDay = liquidityDay;
	}
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	public int getIdRiskLevel() {
		return idRiskLevel;
	}
	public void setIdRiskLevel(int idRiskLevel) {
		this.idRiskLevel = idRiskLevel;
	}
	public RiskLevelDTO getRiskLevel() {
		return riskLevel;
	}
	public void setRiskLevel(RiskLevelDTO riskLevel) {
		this.riskLevel = riskLevel;
	}
	
	

}
