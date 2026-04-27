package org.samtar.cms.modules.shared.service;

import org.samtar.cms.modules.shared.entity.GenderEntity;
import org.samtar.cms.modules.shared.repository.GenderRepository;
import org.springframework.stereotype.Service;

@Service
public class GenderService {
    GenderRepository genderRepository;

    public GenderService(GenderRepository genderRepository) {
        this.genderRepository = genderRepository;
    }

    public GenderEntity getGender(String gender) throws Exception{
        return genderRepository.findByGender(gender).orElseThrow(()-> new NullPointerException("Invalid Gender"));
    }

    public GenderEntity getGender(Long gender) throws Exception{
        return genderRepository.findById(gender).orElseThrow(()-> new NullPointerException("Invalid Gender"));
    }

}
