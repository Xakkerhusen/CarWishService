package org.example.repository;

import org.example.dto.ProvidedAffair;
import org.example.dto.Respons;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProvidedAffairRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Respons create(Integer profileId, Integer carId, Integer serviceTypeId) {
        int res;
        String sql = "insert into affair(profile_id, car_id, service_type_id) values (?,?,?) ";
        res = jdbcTemplate.update(sql,profileId,carId,serviceTypeId);
        if (res != 0) {
            return new Respons("Affair created 👌👌👌", true);
        }
        return new Respons("Error created ❌!!!", false);


    }

    /**
     * Method returns provided affair list by carId
     */
    public List<ProvidedAffair> listByCarId(Integer carId) {
        return null;
    }


    public List<ProvidedAffair> allAffairList() {

//        List<ProvidedAffair> providedAffairs;
//        String sql="select p.name,p.surname,p.phone,car1.number,model,serviceType.name,serviceType.person_tage,serviceType.created_data from affair " +
//                "inner join profile2 p on affair.profile_id = p.id " +
//                "inner join car1 on affair.car_id=car1.id " +
//                "inner join serviceType on affair.service_type_id=serviceType.id " +
//                "group by p.name,p.surname,p.phone,car1.number,model,serviceType.name,serviceType.person_tage,serviceType.created_data;";

        return jdbcTemplate.query("select * from affair",new BeanPropertyRowMapper<>(ProvidedAffair.class));

//      providedAffairs=jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(ProvidedAffair.class));
//    return providedAffairs;
    }
}
