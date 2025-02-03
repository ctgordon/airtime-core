package com.airtime.logbook_service.service.impl;

import com.airtime.logbook_service.persistence.dao.ProfileRepository;
import com.airtime.logbook_service.persistence.model.Profile;
import com.airtime.logbook_service.service.ProfileService;
import com.airtime.logbook_service.web.dto.ProfileDTO;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("profileService")
public class ProfileServiceImpl implements ProfileService {

    private final ProfileRepository profileRepository;

    public ProfileServiceImpl(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    @Override
    public List<Profile> findAll() {
        return this.profileRepository.findAll();
    }

    @Override
    public List<ProfileDTO> findAllPeople() {
        List<ProfileDTO> profileDTOList = new ArrayList<>();
        List<Profile> people = this.profileRepository.findAll();

        if (!people.isEmpty()) {
            people = people.stream().filter(Profile::isInUse).toList();
            people.forEach(person -> profileDTOList.add(person.dto()));
        }

        return profileDTOList;
    }

    @Override
    public boolean save(Profile profile) {
        boolean saved = false;
        try {
            this.profileRepository.save(profile);
            saved = true;
        } catch (Exception e) {
            System.out.println(e);
        }

        return saved;
    }

    @Override
    public Profile findPersonById(UUID id) {
        Profile profile = null;
        Optional<Profile> optional = profileRepository.findById(id);
        if (optional.isPresent()) {
            profile = optional.get();
        }
        return profile;
    }

    @Override
    public Profile findPersonByUserId(UUID userId) {
        Profile profile = null;
        Optional<Profile> optional = Optional.ofNullable(profileRepository.findByUserId(userId));
        if (optional.isPresent()) {
            profile = optional.get();
        }
        return profile;
    }

    @Override
    public Profile findPersonByName(String name) {
        Profile profile = null;
        /*Optional<Person> optional = personRepository.findPersonByNameIgnoreCase(name);
        if (optional.isPresent()) {
            person = optional.get();
        }*/
        return profile;
    }
}
