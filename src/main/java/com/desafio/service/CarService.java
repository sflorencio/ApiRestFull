package com.desafio.service;

import java.util.List;
import com.desafio.model.Car;

public interface CarService {

	Car createCar(Car car);

	Car updateCar(Car car);

	List<Car> getAllCar();

	Car getCarById(long carId);

	void deleteCar(long carId);
}