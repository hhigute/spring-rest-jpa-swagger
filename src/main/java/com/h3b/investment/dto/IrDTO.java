package com.h3b.investment.dto;

public class IrDTO implements DTOEntity{

	private int startDay;
	private int finishDay;
	private double percentFee;
	
	
	public int getStartDay() {
		return startDay;
	}
	public void setStartDay(int startDay) {
		this.startDay = startDay;
	}
	public int getFinishDay() {
		return finishDay;
	}
	public void setFinishDay(int finishDay) {
		this.finishDay = finishDay;
	}
	public double getPercentFee() {
		return percentFee;
	}
	public void setPercentFee(double percentFee) {
		this.percentFee = percentFee;
	}

	
	
}
