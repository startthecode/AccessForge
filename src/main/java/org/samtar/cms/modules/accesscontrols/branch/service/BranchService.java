package org.samtar.cms.modules.accesscontrols.branch.service;

import org.samtar.cms.modules.accesscontrols.branch.entity.BranchEntity;
import org.samtar.cms.modules.accesscontrols.branch.repository.BranchRepository;
import org.springframework.stereotype.Service;

@Service
public class BranchService {
    BranchRepository branchRepository;

    public BranchService(BranchRepository branchRepository) {
        this.branchRepository = branchRepository;
    }

    public BranchEntity getBranch(String department) throws NullPointerException{
        return branchRepository.findByBranch(department).orElseThrow(()-> new NullPointerException("Branch not found"));
    }
    public BranchEntity getBranch(Long id ) throws Exception{
        return branchRepository.findById(id).orElseThrow(()-> new NullPointerException("Branch not found"));
    }
}
