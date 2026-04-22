package org.samtar.cms.modules.accesscontrols.user.service;

import org.samtar.cms.modules.accesscontrols.user.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
