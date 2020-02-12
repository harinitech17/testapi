package com.reesby.service;

import java.util.List;
import com.reesby.model.Deal;

public interface DealService {
	
	void addDeal(Deal deal);
	Deal getDeal(String contactId,String tenantId);
	List<Deal> getAllDeals(String tenantId);
	void updateDeal(Deal deal); 
	void deleteDeal(String dealId);
}
