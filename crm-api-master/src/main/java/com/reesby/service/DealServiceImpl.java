package com.reesby.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reesby.model.Deal;
import com.reesby.repository.DealRepo;

@Service
public class DealServiceImpl implements DealService {
	
	@Autowired
	DealRepo dealRepo;
	
	@Override
	public void addDeal(Deal deal) {
		dealRepo.addDeal(deal);
		
	}

	@Override
	public Deal getDeal(String dealId,String tenantId) {
		
		return dealRepo.getDeal(dealId,tenantId);
	}

	@Override
	public List<Deal> getAllDeals(String tenantId) {
		
		return dealRepo.getAllDeals(tenantId);
	}
	@Override
	public void updateDeal(Deal deal) {
		
		dealRepo.updateDeal(deal);
		
	}
	@Override
	public void deleteDeal(String dealId) {
		// TODO Auto-generated method stub
		
	}

}
