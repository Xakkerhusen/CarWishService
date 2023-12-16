package org.example.service;

import org.example.dto.Respons;
import org.example.dto.ServiceType;
import org.example.enums.Status;
import org.example.repository.ServiceTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

@Service
public class ServiceTypeService {
    @Autowired
    private ServiceTypeRepository serviceTypeRepository;


    public void create(String name, double price, double personTage, LocalDate now, String named) {
        for (ServiceType serviceType : serviceTypeRepository.all()) {
            if (serviceType.getName().equals(name)) {
                System.out.println("Such a service type is available in the base!!!");
                return;
            }
        }
        Respons respons = serviceTypeRepository.create(name, price, personTage, now, named);
        if (respons.success()) {
            System.out.println(respons.massage());
        } else {
            System.out.println(respons.massage());
        }

    }

    public ServiceType getById(Integer id) {
        for (ServiceType serviceType : serviceTypeRepository.all()) {
            if (serviceType.getId().equals(id)) {
                return serviceType;
            }
        }
        return null;
    }

    public List<ServiceType> all() {
        return serviceTypeRepository.all();
    }


    public List<ServiceType> getAllActive() {
        List<ServiceType> serviceTypes =new LinkedList<>();
        for (ServiceType serviceType : serviceTypeRepository.all()) {
            if (serviceType.getStatus().equals(Status.ACTIVE)) {
                serviceTypes.add(serviceType);
            }
        }
        return serviceTypes;
    }

    public void changeStatus(Integer id, String status) {
        Respons respons;
        if (status.equals("ACTIVE") || status.equals("NOT_ACTIVE")) {
            respons = serviceTypeRepository.changeStatus(id, status);
            if (respons == null) {
                System.out.println("Wrong service id!!!");
            } else if (respons.success()) {
                System.out.println(respons.massage());
            } else {
                System.out.println(respons.massage());
            }
        } else {
            System.out.println("False status must be selected(ACTIVE OR  NOT_ACTIVE)");
        }
    }


}
