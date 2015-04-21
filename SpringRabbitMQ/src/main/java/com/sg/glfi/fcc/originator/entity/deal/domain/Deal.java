package com.sg.glfi.fcc.originator.entity.deal.domain;

import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.sg.glfi.fcc.originator.entity.AbstractEntity;

@XmlRootElement(name = "deal")
public class Deal extends AbstractEntity {
	private static final long serialVersionUID = -938693949789642690L;

	private double dealAmount;

	private String dealName;

	private int opportunityId;

	private boolean isDealPriced;

	private Date dealExpectedClosure;

	@XmlElement
	public double getDealAmount() {
		return dealAmount;
	}

	public void setDealAmount(double dealAmount) {
		this.dealAmount = dealAmount;
	}
	
	@XmlElement
	public String getDealName() {
		return dealName;
	}

	public void setDealName(String dealName) {
		this.dealName = dealName;
	}

	@XmlElement
	public int getOpportunityId() {
		return opportunityId;
	}

	public void setOpportunityId(int opportunityId) {
		this.opportunityId = opportunityId;
	}

	@XmlElement
	public boolean isDealPriced() {
		return isDealPriced;
	}

	public void setDealPriced(boolean isDealPriced) {
		this.isDealPriced = isDealPriced;
	}

	@XmlElement
	public Date getDealExpectedClosure() {
		return dealExpectedClosure;
	}

	public void setDealExpectedClosure(Date dealExpectedClosure) {
		this.dealExpectedClosure = dealExpectedClosure;
	}
}