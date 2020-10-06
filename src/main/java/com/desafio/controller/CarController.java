package com.desafio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.desafio.model.Car;
import com.desafio.service.CarService;

@RestController
public class CarController {

	@Autowired
	private CarService carService;
	
	@GetMapping("api/cars") 
	public ResponseEntity<List<Car>> getAllCar(){
		return ResponseEntity.ok().body(carService.getAllCar());
	}

	@PostMapping("api/cars")
	public ResponseEntity<Car> createCar(@RequestBody Car car){
		return ResponseEntity.ok().body(this.carService.createCar(car));
	}
	
	@GetMapping("api/car/{id}")
	public ResponseEntity<Car> getUserById(@PathVariable long id){
		return ResponseEntity.ok().body(carService.getCarById(id));
	}

	@DeleteMapping("api/car/{id}")
	public HttpStatus deleteUser(@PathVariable long id){
		this.carService.deleteCar(id);
		return HttpStatus.OK;
	}	
	
	@PutMapping("api/car/{id}")
	public ResponseEntity<Car> updateCar(@PathVariable long id, @RequestBody Car car){
		car.setId(id);
		return ResponseEntity.ok().body(this.carService.updateCar(car));
	}

	
}
