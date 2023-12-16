package org.example.service;

import org.example.dto.Profile;
import org.example.dto.Respons;
import org.example.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfileService {
    @Autowired
    private ProfileRepository profileRepository;

    public void create(Profile dto) {
        for (Profile profile : printProfileList()) {
            if (profile.getPhone().equals(dto.getPhone())) {
                System.out.println("Such a profile is available in the base!!!");
                return ;
            }
        }
        Respons respons = profileRepository.create(dto);

        if (respons.success()) {
            System.out.println(respons.massage());
        }else {
            System.out.println(respons.massage());
        }
    }

    public boolean update(Integer id,String updateName, String updateSurname) {
        Respons update = profileRepository.update(id,updateName, updateSurname);
        return update.success();
    }

    public List<Profile> getById(Integer id) {
        List<Profile> byId = profileRepository.getById(id);
        return byId;
    }

    public List<Profile> printProfileList() {
        List<Profile> profiles = profileRepository.profileList();
        return profiles;
    }


    public Profile search(String searchingWord) {
        for (Profile profile : profileRepository.profileList()) {
            if (profile.getName().equals(searchingWord)||profile.getSurname().equals(searchingWord)||profile.getPhone().equals(searchingWord)){
               return profile;
            }
        }
        return null;
    }


}
