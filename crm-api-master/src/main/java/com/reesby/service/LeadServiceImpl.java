package com.reesby.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reesby.model.Lead;
import com.reesby.repository.LeadRepo;

@Service
public class LeadServiceImpl implements LeadService {

	@Autowired
	LeadRepo leadRepo;

	@Override
	public void addLead(Lead lead) {
		leadRepo.addLead(lead);

	}

	@Override
	public Lead getLead(String leadId, String tenantId) {

		return leadRepo.getLead(leadId, tenantId);
	}

	@Override
	public List<Lead> getAllLeads(String tenantId) {

		return leadRepo.getAllLeads(tenantId);
	}

	@Override
	public void updateLead(Lead lead) {

		leadRepo.updateLead(lead);

	}

	@Override
	public void deleteLead(String leadId) {
		// TODO Auto-generated method stub

	}

}
