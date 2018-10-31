package com.apap.tutorial7.service;
import com.apap.tutorial7.model.CarModel;
import com.apap.tutorial7.repository.CarDb;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*
 * CarServiceImpl
 */
@Service
@Transactional
public class CarServiceImpl implements CarService{
	@Autowired
	private CarDb carDb;

	@Override
	public CarModel addCar(CarModel car) {
		return carDb.save(car);
		
	}

	@Override
	public List<CarModel> getAllCar() {
		return carDb.findAll();
	}

	@Override
	public List<CarModel> sortByPriceDesc(Long dealer_id) {
		return carDb.findByDealerIdOrderByPriceDesc(dealer_id);
	}

	@Override
	public void deleteCar(CarModel car) {
		carDb.delete(car);
		
	}

	@Override
	public CarModel getCar(long id) {
		return carDb.findById(id).get();
	}

	@Override
	public void updateCar(Long id, String brand, String type, Long price, Integer amount) {
		// TODO Auto-generated method stub
		CarModel updateCar = carDb.getOne(id);
		updateCar.setAmount(amount);
		updateCar.setBrand(brand);
		updateCar.setPrice(price);
		updateCar.setType(type);
		carDb.save(updateCar);
	}

}
