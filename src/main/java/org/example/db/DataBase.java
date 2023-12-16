package org.example.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class DataBase {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    public void creatTableProfile() {
        String sql ="create table if not exists profile2(" +
                "    id serial primary key ," +
                "    name varchar," +
                "    surname varchar," +
                "    phone varchar unique not null ," +
                "    created_data date," +
                "    visible boolean default true" +
                ");";
        jdbcTemplate.execute(sql);
    }
  public void creatTableCar() {
        String sql ="create table if not exists car1(" +
                "   id serial primary key ," +
                "   number varchar unique not null ," +
                "   model varchar," +
                "   color varchar ," +
                "   created_data date," +
                "visible boolean default true"+
                ");";
        jdbcTemplate.execute(sql);
    }

    public void creatTableServiceType() {
        String sql ="create table if not exists serviceType(" +
                "id serial primary key ," +
                "name varchar unique not null,"+
                "price double precision ," +
                "person_tage double precision," +
                "created_data date ," +
                "status varchar" +
                ");";
        jdbcTemplate.execute(sql);
    }

    public void creatTableAffair() {
        String sql ="create table if not exists affair (" +
                "    id serial primary key,"+
                "    profile_id integer references profile2(id)," +
                "    car_id integer references car1(id)," +
                "    service_type_id integer references serviceType(id)" +
                ");";
        jdbcTemplate.execute(sql);
    }



}
