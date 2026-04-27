package org.samtar.cms.modules.accesscontrols.designation.service;

import org.samtar.cms.modules.accesscontrols.department.entity.DepartmentEntity;
import org.samtar.cms.modules.accesscontrols.department.repository.DepartmentRepository;
import org.samtar.cms.modules.accesscontrols.designation.entity.DesignationEntity;
import org.samtar.cms.modules.accesscontrols.designation.repository.DesignationRepository;
import org.springframework.stereotype.Service;

@Service
public class DesignationService {
    DesignationRepository designationRepository;

    public DesignationService(DesignationRepository designationRepository) {
        this.designationRepository = designationRepository;
    }

    public DesignationEntity getDesignation(String designation) throws NullPointerException{
        return designationRepository.findByDesignation(designation).orElseThrow(()-> new NullPointerException("Department not found"));
    }
    public DesignationEntity getDesignation(Long id ) throws Exception{
        return designationRepository.findById(id).orElseThrow(()-> new NullPointerException("Department not found"));
    }
}
