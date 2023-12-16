package org.example.service;

import org.example.dto.Car;
import org.example.dto.Respons;
import org.example.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {
    @Autowired
    private CarRepository carRepository;

    public void create(Car dto) {
        for (Car car : carRepository.all()) {
            if (car.getNumber().equals(dto.getNumber())){
                System.out.println("Such a car is available in the base!!!");
                return;
            }
        }

        Respons respons = carRepository.create(dto);
        if (respons.success()) {
            System.out.println(respons.massage());
        } else {
            System.out.println(respons.massage());
        }
    }

    public Car findByCarNumber(String carNumber) {
        List<Car> all = carRepository.all();
        for (Car car : all) {
            if (car.getNumber().equals(carNumber)) {
                return car;
            }
        }
        return null;
    }

    public Car findById(Integer id) {
        List<Car> all = carRepository.all();
        for (Car car : all) {
            if (car.getId().equals(id)) {
                return car;
            }
        }
        return null;
    }

    public List<Car> printAll() {
        List<Car> all = carRepository.all();
        return all;

    }

}
