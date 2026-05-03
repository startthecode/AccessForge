package org.samtar.cms.modules.accesscontrols.user.service;

import org.samtar.cms.modules.accesscontrols.user.dto.request.UserProfileDto;
import org.samtar.cms.modules.accesscontrols.user.entity.UserProfileEntity;
import org.samtar.cms.modules.accesscontrols.user.mapper.UserProfileMapper;
import org.samtar.cms.modules.accesscontrols.user.repository.UserProfileRepository;
import org.samtar.cms.modules.shared.entity.GenderEntity;
import org.samtar.cms.modules.shared.service.GenderService;
import org.springframework.stereotype.Service;

@Service
public class UserProfileService {
    UserProfileRepository userProfileRepository;
     UserProfileMapper mapper;
     GenderService genderService;
    public UserProfileService(
                              UserProfileRepository userProfileRepository,
                              GenderService genderService,
                              UserProfileMapper mapper) {
        this.userProfileRepository = userProfileRepository;
        this.mapper  = mapper;
        this.genderService = genderService;
    }

    public UserProfileEntity createUserProfile(UserProfileDto req) throws Exception{
        UserProfileEntity newProfile = mapper.toEntity(req);
        GenderEntity gender = genderService.getGender(req.genderID());
        newProfile.setGenderID(gender);
        return userProfileRepository.save(newProfile);
    }

}
