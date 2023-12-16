package org.example.repository;

import org.example.dto.Profile;
import org.example.dto.Respons;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProfileRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    public Respons create(Profile dto) {
        int res;
        String sql = "insert into profile2(name, surname, phone,created_data) values (?,?,?,?) ";
        res = jdbcTemplate.update(sql,dto.getName(),dto.getSurname(),dto.getPhone(),dto.getCreatedData());
        if (res != 0) {
            return new Respons("Profile added 👌👌👌", true);
        }
        return new Respons("Error added ❌!!!", false);
    }

    public Respons update(Integer id,String updateName, String updateSurname) {
        int res;
        String sql = "update profile2 set name=?, surname=? where id=? ";
        res = jdbcTemplate.update(sql, updateName, updateSurname,id);
        if (res > 0) {
            return new Respons("Profile updated 👌👌👌", true);
        }
        return new Respons("Error Fill!!!", false);
    }

    public List<Profile> getById(Integer id) {

        String sql = "select * from profile2 where id=? ";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Profile.class),id);

    }

    public List<Profile> profileList() {
            List<Profile> cardList;
            String sql = "select * from profile2";
            cardList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Profile.class));
            return cardList;
    }


    public boolean checkProfileId(Integer profileId) {
        for (Profile profile : profileList()) {
            if (profile.getId().equals(profileId)) {
                return true;
            }
        }
        return false;
    }
}
