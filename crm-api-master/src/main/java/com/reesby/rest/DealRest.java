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
import com.reesby.model.Deal;
import com.reesby.service.DealService;

@RestController
@RequestMapping("/api/v1")
public class DealRest {

	@Autowired
	DealService dealService;

	@GetMapping("/tenant/{tenantId}/deal/{dealId}")
	public ResponseEntity<Deal> getDeal(@PathVariable String tenantId, @PathVariable String dealId)
			throws ResourceNotFoundException {
		Deal deal = dealService.getDeal(dealId, tenantId);
		if (deal == null) {
			throw new ResourceNotFoundException("Deal not found for this id :: " + dealId);
		}
		return new ResponseEntity<Deal>(deal, new HttpHeaders(), HttpStatus.OK);
	}

	@PostMapping("/adddeal")
	public ResponseEntity<Deal> addDeal(@RequestBody Deal deal) {
		dealService.addDeal(deal);
		return new ResponseEntity<Deal>(dealService.getDeal(deal.getDealId(), deal.getTenantId()), new HttpHeaders(),
				HttpStatus.OK);
	}
	
	@PutMapping("/updatedeal")
	public void updateDeal(@RequestBody Deal deal) throws ResourceNotFoundException, Exception {
		if ((deal.getDealId() == null || deal.getDealId().equals(""))
				|| (deal.getTenantId() == null || deal.getTenantId().equals(""))
				|| (deal.getDealowner() == null || deal.getDealowner().equals(""))
				|| (deal.getDealName() == null || deal.getDealName().equals(""))
				|| (deal.getdBusinessType() == null || deal.getdBusinessType().equals(""))
				|| (deal.getdContactName() == null || deal.getdContactName().equals(""))
				|| (deal.getdAmt() == null || deal.getdAmt().equals(""))
				|| (deal.getdStatus() == null || deal.getdStatus().equals(""))) {
			throw new Exception("Required fields are missing");
		}

		if (dealService.getDeal(deal.getDealId(), deal.getTenantId()) == null) {
			throw new ResourceNotFoundException("Deal not found for this id - " + deal.getDealId());
		}
		dealService.updateDeal(deal);
	}

}
