package org.samtar.cms.modules.accesscontrols.authority.service;

import org.samtar.cms.common.exception.AuthorityException;
import org.samtar.cms.modules.accesscontrols.authority.entity.AuthorityEntity;
import org.samtar.cms.modules.accesscontrols.authority.repository.AuthorityRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthorityServices {
    AuthorityRepository authorityRepository;

    public AuthorityServices(AuthorityRepository authorityRepository) {
        this.authorityRepository = authorityRepository;
    }

    public AuthorityEntity findAuthorityOrThrow(Long authorityID){
return authorityRepository.findById(authorityID).orElseThrow(AuthorityException::authorityNotFound);
    }
}
