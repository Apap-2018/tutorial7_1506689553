package com.apap.tutorial7.controller;

import java.util.List;

import com.apap.tutorial7.model.CarModel;
import com.apap.tutorial7.model.DealerModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.apap.tutorial7.service.CarService;
import com.apap.tutorial7.service.DealerService;
import org.springframework.web.client.RestTemplate;

/*
 * CarController
 */
@RestController
@RequestMapping("/car")
public class CarController {
	@Autowired
	private CarService carService;
	
	@Autowired
	private DealerService dealerService;


	@PostMapping(value = "/add")
	private CarModel addCarSubmit(@RequestBody CarModel car){
		return carService.addCar(car);
	}

	//coba pake id dealer
	@PostMapping(value = "/{dealerId}")
	private CarModel addCarSubmit(
			@PathVariable (value = "dealeId") long dealerId,
			@RequestBody CarModel car) {
		DealerModel dealer = dealerService.getDealerDetailById(dealerId).get();
		car.setDealer(dealer);
		return carService.addCar(car);
	}

	@GetMapping()
	private List<CarModel> viewAllCar(Model model) {
		System.out.println("cari car ");
		return carService.getAllCar();
	}

	@GetMapping(value = "/view")
	private CarModel viewCar(@RequestParam(value = "carId") long carId, Model model) {
		System.out.println("cari car "+carId);
		return carService.getCar(carId);
	}

	@PutMapping(value = "/{id}")
	private String updateCarSubmit(
			@PathVariable (value = "id") long carId,
			@RequestParam(value = "brand",required = false) String brand,
			@RequestParam(value = "type",required = false) String type,
			@RequestParam(value = "price",required = false) Long price,
			@RequestParam(value = "amount",required = false) Integer amount,
			@RequestParam(value = "dealerId",required = false) long dealerId){
		CarModel car = carService.getCar(carId);
		if (car.equals(null)){
			return "Cannot find a car";
		}
		car.setBrand(brand);
		car.setType(type);
		car.setPrice(price);
		car.setAmount(amount);

		if (String.valueOf(dealerId ) != null ){
			car.setDealer(dealerService.getDealerDetailById(dealerId).get());
		}
		carService.addCar(car);
		return "Car has been updated";
	}

	@DeleteMapping(value = "/delete")
	private String deleteCar(@RequestParam(value = "carId") long carId, Model model){
		CarModel car = carService.getCar(carId);
		carService.deleteCar(car);
		return "Car has been deleted";
	}

}
