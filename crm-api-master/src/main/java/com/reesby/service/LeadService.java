package com.reesby.service;

import java.util.List;

import com.reesby.model.Lead;

public interface LeadService {
	
	void addLead(Lead lead);
	Lead getLead(String leadId,String tenantId);
	List<Lead> getAllLeads(String tenantId);
	void updateLead(Lead lead);
	void deleteLead(String leadId);
}
