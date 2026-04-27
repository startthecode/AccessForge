package org.samtar.cms.modules.accesscontrols.user.service;

import jakarta.transaction.Transactional;
import org.samtar.cms.modules.accesscontrols.user.dto.CreateUserDto;
import org.samtar.cms.modules.accesscontrols.user.dto.CreateUserResponse;
import org.samtar.cms.modules.accesscontrols.user.entity.UserEntity;
import org.samtar.cms.modules.accesscontrols.user.entity.UserProfileEntity;
import org.samtar.cms.modules.accesscontrols.user.mapper.UserMapper;
import org.samtar.cms.modules.accesscontrols.user.repository.UserRepository;
import org.samtar.cms.modules.shared.enums.Status;
import org.samtar.cms.modules.shared.service.StatusService;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    StatusService statusService;
    UserRepository userRepository;
    UserMapper mapper;
    UserProfileService userProfileService;
    public UserService(UserRepository userRepository,
                       UserMapper mapper,
                       StatusService statusService,
                       UserProfileService userProfileService) {
        this.userRepository = userRepository;
        this.statusService = statusService;
        this.mapper = mapper;
        this.userProfileService= userProfileService;
    }

    @Transactional
    public CreateUserResponse createUser(CreateUserDto reqBody) throws Exception{
        UserEntity newUser = mapper.toEntity(reqBody);
        UserProfileEntity newUserProfile = userProfileService.createUserProfile(reqBody.profile());
        newUser.setUserProfile(newUserProfile);
        newUser.setStatusId(statusService.getStatusEntity(Status.ACTIVE));
        return mapper.tResponse(userRepository.save(newUser));
    }
}
