package org.samtar.cms.modules.accesscontrols.user.service;

import jakarta.transaction.Transactional;
import org.samtar.cms.modules.accesscontrols.branch.entity.BranchEntity;
import org.samtar.cms.modules.accesscontrols.branch.service.BranchService;
import org.samtar.cms.modules.accesscontrols.department.entity.DepartmentEntity;
import org.samtar.cms.modules.accesscontrols.department.service.DepartmentService;
import org.samtar.cms.modules.accesscontrols.designation.entity.DesignationEntity;
import org.samtar.cms.modules.accesscontrols.designation.service.DesignationService;
import org.samtar.cms.modules.accesscontrols.user.dto.UserProfileDto;
import org.samtar.cms.modules.accesscontrols.user.entity.UserEntity;
import org.samtar.cms.modules.accesscontrols.user.entity.UserProfileEntity;
import org.samtar.cms.modules.accesscontrols.user.mapper.UserProfileMapper;
import org.samtar.cms.modules.accesscontrols.user.repository.UserProfileRepository;
import org.samtar.cms.modules.shared.entity.GenderEntity;
import org.samtar.cms.modules.shared.service.GenderService;
import org.springframework.stereotype.Service;

@Service
public class UserProfileService {
    DesignationService designationService;
    DepartmentService departmentService;
    UserProfileRepository userProfileRepository;
     UserProfileMapper mapper;
     BranchService branchService;
     GenderService genderService;
    public UserProfileService(DepartmentService departmentService,
                              DesignationService designationService,
                              UserProfileRepository userProfileRepository,
                              BranchService branchService,
                              GenderService genderService,
                              UserProfileMapper mapper) {
        this.departmentService = departmentService;
        this.designationService = designationService;
        this.userProfileRepository = userProfileRepository;
        this.branchService = branchService;
        this.mapper  = mapper;
        this.genderService = genderService;
    }

    public UserProfileEntity createUserProfile(UserProfileDto req) throws Exception{
        UserProfileEntity newProfile = mapper.toEntity(req);
        DepartmentEntity department = departmentService.getDepartment(req.departmentID());
        DesignationEntity designation = designationService.getDesignation(req.designationID());
        BranchEntity branch = branchService.getBranch(req.branchID());
        GenderEntity gender = genderService.getGender(req.gender());
        newProfile.setDepartmentID(department);
        newProfile.setBranchID(branch);
        newProfile.setDesignationID(designation);
        newProfile.setGenderID(gender);
        return userProfileRepository.save(newProfile);
    }

}
