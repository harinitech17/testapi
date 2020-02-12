package com.reesby.repository;

import java.util.List;

import com.reesby.model.Deal;

public interface DealRepo  {
	
	void addDeal(Deal deal);
	Deal getDeal(String dealId,String tenantId);
	List<Deal> getAllDeals(String dealId);
	void updateDeal(Deal deal);
	void deleteDeal(String dealId);

}
