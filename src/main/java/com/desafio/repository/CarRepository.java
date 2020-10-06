package com.desafio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.desafio.model.Car;

public interface CarRepository extends JpaRepository<Car, Long> {

}
