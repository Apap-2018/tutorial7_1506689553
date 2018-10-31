package com.apap.tutorial7.service;

import java.util.List;

import com.apap.tutorial7.model.CarModel;

/*
 * CarService
 */
public interface CarService {
	CarModel addCar(CarModel car);
	List<CarModel> getAllCar();
	List<CarModel> sortByPriceDesc(Long dealer_id);
	void deleteCar(CarModel car);
	CarModel getCar(long id);
	void updateCar(Long id, String brand, String type, Long price, Integer amount);

}
