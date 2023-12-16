package org.example.repository;

import org.example.dto.Respons;
import org.example.dto.ServiceType;
import org.example.enums.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class ServiceTypeRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    public Respons create(String name, double price, double personTage, LocalDate now, String named) {
        int res;
        String sql = "insert into serviceType(name, price, person_tage,created_data,status) values (?,?,?,?,?) ";
        res = jdbcTemplate.update(sql,name,price,personTage,now,named);
        if (res != 0) {
            return new Respons("Car added 👌👌👌", true);
        }
        return new Respons("Error added ❌!!!", false);

    }

    public ServiceType getById(Integer id) {
        return null;
    }



    public List<ServiceType> all() {
        List<ServiceType> cardList;
        String sql = "select * from serviceType";
        cardList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(ServiceType.class));
        return cardList;
    }

    public List<ServiceType> getAllActive() {
        return null;
    }

    public Respons changeStatus(Integer id, String status) {
        int res;
        String sql = "update serviceType set status=? where id=?";
        res = jdbcTemplate.update(sql,status,id);
        if (res != 0) {
            return new Respons("Service status updated 👌👌👌", true);
        }
        return new Respons("Error added ❌!!!", false);
    }


    public boolean checkServiceTypeId(Integer serviceTypeId) {
        for (ServiceType serviceType : all()) {
            if (serviceType.getId().equals(serviceTypeId)) {
                return true;
            }
        }
        return false;
    }
}
