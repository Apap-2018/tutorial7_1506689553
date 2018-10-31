package com.apap.tutorial7.service;

import com.apap.tutorial7.model.DealerModel;
import com.apap.tutorial7.repository.DealerDb;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*
 * DealerServiceImpl
 */
@Service
@Transactional
public class DealerServiceImpl implements DealerService{
	@Autowired
	private DealerDb dealerDb;

	@Override
	public Optional<DealerModel> getDealerDetailById(Long id) {
		return dealerDb.findById(id.longValue());
	}

	@Override
	public DealerModel addDealer(DealerModel dealer) {
		return dealerDb.save(dealer);
		
	}

	@Override
	public void deleteDealer(DealerModel dealer) {
		dealerDb.delete(dealer);
	}


	@Override
	public void updateDealer(Long id, DealerModel dealer) {
		DealerModel updateDealer = dealerDb.getOne(id);
		dealerDb.save(updateDealer);
	}

	@Override
	public List<DealerModel> viewAllDealer() {
		return dealerDb.findAll();
	}
	
	

}
