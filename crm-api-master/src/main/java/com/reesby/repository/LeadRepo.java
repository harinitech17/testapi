package com.reesby.repository;

import java.util.List;

import com.reesby.model.Lead;

public interface LeadRepo  {
	
	void addLead(Lead leadId);
	Lead getLead(String leadId,String tenantId);
	List<Lead> getAllLeads(String tenantId);
	void updateLead(Lead lead);
	void deleteLead(String leadId);
}