package org.samtar.cms.modules.shared.service;

import org.samtar.cms.modules.shared.entity.StatusEntity;
import org.samtar.cms.modules.shared.enums.Status;
import org.samtar.cms.modules.shared.repository.StatusRepository;
import org.springframework.stereotype.Service;

@Service
public class StatusService {
    StatusRepository statusRepository;

    public StatusService(StatusRepository statusRepository){
        this.statusRepository = statusRepository;
    }

    public StatusEntity getStatusEntity(Status status) throws NullPointerException{
        return statusRepository.findByStatus(status).orElseThrow(()-> new NullPointerException("No found"));
    }

}
