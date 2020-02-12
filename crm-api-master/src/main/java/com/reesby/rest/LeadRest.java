package com.reesby.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reesby.exception.ResourceNotFoundException;
import com.reesby.model.Lead;
import com.reesby.service.LeadService;

@RestController
@RequestMapping("/api/v1")
public class LeadRest {

	@Autowired
	LeadService leadService;

	@GetMapping("/tenant/{tenantId}/lead/{leadId}")
	public ResponseEntity<Lead> getUser(@PathVariable String tenantId, @PathVariable String leadId)
			throws ResourceNotFoundException {
		Lead lead = leadService.getLead(leadId, tenantId);

		if (lead == null) {
			throw new ResourceNotFoundException("Lead not found for this id :: " + leadId);
		}
		return new ResponseEntity<Lead>(lead, new HttpHeaders(), HttpStatus.OK);
	}

	@PostMapping("/addlead")
	public ResponseEntity<Lead> addLead(@RequestBody Lead lead) {
		leadService.addLead(lead);
		return new ResponseEntity<Lead>(leadService.getLead(lead.getLeadId(), lead.getTenantId()), new HttpHeaders(),
				HttpStatus.OK);
	}

	@PutMapping("/updatelead")
	public void updateLead(@RequestBody Lead lead) throws ResourceNotFoundException, Exception {
		if ((lead.getLeadowner() == null || lead.getLeadowner().equals(""))
				|| (lead.getTenantId() == null || lead.getTenantId().equals(""))
				|| (lead.getlCompany() == null || lead.getlCompany().equals(""))
				|| (lead.getlFname() == null || lead.getlFname().equals(""))
				|| (lead.getlEmail() == null || lead.getlEmail().equals(""))
				|| (lead.getlPersonalph() == null || lead.getlPersonalph().equals(""))
				|| (lead.getlStatus() == null || lead.getlStatus().equals(""))) {
			throw new Exception("Required fields are missing");
		}

		if (leadService.getLead(lead.getLeadId(), lead.getTenantId()) == null) {
			throw new ResourceNotFoundException("Lead not found for this id - " + lead.getLeadId());
		}
		leadService.updateLead(lead);
	}

}
