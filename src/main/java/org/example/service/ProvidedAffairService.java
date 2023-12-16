package org.example.service;

import org.example.dto.ProvidedAffair;
import org.example.dto.Respons;
import org.example.repository.CarRepository;
import org.example.repository.ProfileRepository;
import org.example.repository.ProvidedAffairRepository;
import org.example.repository.ServiceTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class ProvidedAffairService {
    @Autowired
    private ProvidedAffairRepository affairRepository;
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private CarRepository carRepository;
    @Autowired
    private ServiceTypeRepository serviceTypeRepository;

    public void create(Integer profileId, Integer carId, Integer serviceTypeId) {
        for (ProvidedAffair providedAffair : affairRepository.allAffairList()) {
            if (providedAffair.getProfileId().equals(profileId)
              ||providedAffair.getCarId().equals(carId)
              ||providedAffair.getServiceTypeId().equals(serviceTypeId)){
                System.out.println("Such a affair is available in the base!!!");
                return;
            }
        }
        boolean checkProfileId = profileRepository.checkProfileId(profileId);
        boolean checkCarId = carRepository.checkCarId(carId);
        boolean checkServiceTypeId = serviceTypeRepository.checkServiceTypeId(serviceTypeId);
        Respons respons;
        if (checkServiceTypeId&&checkCarId&&checkProfileId) {
         respons = affairRepository.create(profileId, carId, serviceTypeId);

         if (respons==null) {
            System.out.println("There is an error in the entered data!!!");
         } else if (respons.success()) {
             System.out.println(respons.massage());
         }else {
             System.out.println(respons.massage());
         }
        }else {
            System.out.println("Enter the information available in the database!!!");
        }
    }

    /**
     * Method prints provided affairList by carId.
     */
    public List<ProvidedAffair> listByCarId(Integer carId) {
        List<ProvidedAffair> cartList =new LinkedList<>();
        for (ProvidedAffair providedAffair : affairRepository.allAffairList()) {
            Integer carId1 = providedAffair.getCarId();
            if (carId1.equals(carId)) {
                cartList.add(providedAffair);
            }
        }
        return cartList;
    }
    public List<ProvidedAffair> all() {
        return affairRepository.allAffairList();

    }
}
