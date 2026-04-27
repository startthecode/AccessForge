package org.samtar.cms.modules.accesscontrols.department.service;

import org.samtar.cms.modules.accesscontrols.department.entity.DepartmentEntity;
import org.samtar.cms.modules.accesscontrols.department.repository.DepartmentRepository;
import org.springframework.stereotype.Service;

@Service
public class DepartmentService {
    DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public DepartmentEntity getDepartment(String department) throws NullPointerException{
        return departmentRepository.findByDepartment(department).orElseThrow(()-> new NullPointerException("Department not found"));
    }
    public DepartmentEntity getDepartment(Long id ) throws Exception{
        return departmentRepository.findById(id).orElseThrow(()-> new NullPointerException("Department not found"));
    }
}
