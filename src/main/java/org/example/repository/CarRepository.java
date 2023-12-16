package org.example.repository;

import org.example.dto.Car;
import org.example.dto.Profile;
import org.example.dto.Respons;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CarRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Respons create(Car dto) {
        int res;
        String sql = "insert into car1(number, model, color,created_data) values (?,?,?,?) ";
        res = jdbcTemplate.update(sql,dto.getNumber(),dto.getModel(),dto.getColor(),dto.getCreatedData());
        if (res != 0) {
            return new Respons("Car added 👌👌👌", true);
        }
        return new Respons("Error added ❌!!!", false);
    }

    public Car findByCarNumber(String carNumber) {
        return null;
    }

    public Car findById(String id) {
        return null;
    }

    public List<Car> all() {
        List<Car> cardList;
        String sql = "select * from car1";
        cardList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Car.class));
        return cardList;
    }

    public boolean checkCarId(Integer carId) {
        for (Car car : all()) {
            if (car.getId().equals(carId)) {
                return true;
            }
        }
        return false;
    }
}
