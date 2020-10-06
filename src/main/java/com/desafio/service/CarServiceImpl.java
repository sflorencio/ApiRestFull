package com.desafio.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.desafio.exception.ResourceNotFoundException;

import com.desafio.model.Car;
import com.desafio.repository.CarRepository;

@Service
@Transactional
public class CarServiceImpl implements CarService {

	@Autowired
	private CarRepository carRepository;
	
	@Override
	public Car createCar(Car car) {
		return carRepository.save(car);
	}

	@Override
	public Car updateCar(Car car) {
		Optional<Car> carDb = this.carRepository.findById(car.getId());
			
		if(carDb.isPresent()) {
			Car carUpdate = carDb.get();
			carUpdate.setLicensePlate(car.getLicensePlate());
			carUpdate.setModel(car.getModel());
			carUpdate.setColor(car.getColor());
			carUpdate.setYear(car.getYear());
			carRepository.save(carUpdate);
			return carUpdate;
		}else {
			throw new ResourceNotFoundException("Record not found with id : " + car.getId());
		}
		
	}

	@Override
	public List<Car> getAllCar() {
		return this.carRepository.findAll();
	}

	@Override
	public Car getCarById(long carId) {

		Optional<Car> carDb = this.carRepository.findById(carId);
		
		if(carDb.isPresent()) {
			return carDb.get();
		}else {
			throw new ResourceNotFoundException("Record not found with id : " + carId);
		}
	}

	@Override
	public void deleteCar(long carId) {

		Optional<Car> carDb = this.carRepository.findById(carId);
		
		if(carDb.isPresent()) {
			this.carRepository.delete(carDb.get());
		}else {
			throw new ResourceNotFoundException("Record not found with id : " + carId);
		}
		
	}
	
}
