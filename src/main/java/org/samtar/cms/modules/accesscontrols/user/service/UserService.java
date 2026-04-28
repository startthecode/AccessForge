package org.samtar.cms.modules.accesscontrols.user.service;

import jakarta.transaction.Transactional;
import org.samtar.cms.config.security.JwtUtils;
import org.samtar.cms.modules.accesscontrols.user.dto.request.CreateUserDto;
import org.samtar.cms.modules.accesscontrols.user.dto.request.LoginRequestDto;
import org.samtar.cms.modules.accesscontrols.user.dto.response.AuthTokenDto;
import org.samtar.cms.modules.accesscontrols.user.dto.response.CreateUserResponse;
import org.samtar.cms.modules.accesscontrols.user.entity.UserEntity;
import org.samtar.cms.modules.accesscontrols.user.entity.UserProfileEntity;
import org.samtar.cms.modules.accesscontrols.user.mapper.UserMapper;
import org.samtar.cms.modules.accesscontrols.user.repository.UserRepository;
import org.samtar.cms.modules.shared.enums.Status;
import org.samtar.cms.modules.shared.enums.TokenTypes;
import org.samtar.cms.modules.shared.service.StatusService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {
    StatusService statusService;
    UserRepository userRepository;
    UserMapper mapper;
    PasswordEncoder passwordEncoder;
    UserProfileService userProfileService;
    JwtUtils jwtUtils;
    AuthenticationManager authenticationManager;

    public UserService(UserRepository userRepository,
                       UserMapper mapper,
                       StatusService statusService,
                       UserProfileService userProfileService,
                       PasswordEncoder passwordEncoder,
                       AuthenticationManager authenticationManager,
                       JwtUtils jwtUtils) {
        this.userRepository = userRepository;
        this.statusService = statusService;
        this.mapper = mapper;
        this.passwordEncoder = passwordEncoder;
        this.userProfileService = userProfileService;
        this.jwtUtils = jwtUtils;
        this.authenticationManager = authenticationManager;
    }

    @Transactional
    public AuthTokenDto signup(CreateUserDto reqBody) throws Exception {
        if (isUserExists(reqBody.username())) throw new Exception("Username already exists");
        if (isEmailExists(reqBody.email())) throw new Exception("Email already exists");
        UserEntity newUser = mapper.toEntity(reqBody);
        UserProfileEntity newUserProfile = userProfileService.createUserProfile(reqBody.profile());
        newUser.setUserProfile(newUserProfile);
        newUser.setPassword(passwordEncoder.encode(reqBody.password()));
        newUser.setStatusId(statusService.getStatusEntity(Status.ACTIVE));
        Map<String, Object> accessToken = new HashMap<>();
        accessToken.put("Token", jwtUtils.generateToken(Map.of("username", reqBody.username()), TokenTypes.Access));
        String refreshToken = jwtUtils.generateToken(Map.of("username", reqBody.username()), TokenTypes.Refresh);
        userRepository.save(newUser);
        return new AuthTokenDto(accessToken, refreshToken);
    }

    @Transactional
    public AuthTokenDto signin(LoginRequestDto reqBody) throws Exception {
        System.out.println(reqBody);
        UserEntity user = getUserOrThrow(reqBody.username(), "Username not found");
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(reqBody.username(), reqBody.password()));
        Map<String, Object> tokenData = new HashMap<>();
        tokenData.put("username", user.getUsername());
        Map<String, Object> accessToken = new HashMap<>();
        accessToken.put("Token", jwtUtils.generateToken(tokenData, TokenTypes.Access));
        String refreshToken = jwtUtils.generateToken(tokenData, TokenTypes.Refresh);
        return new AuthTokenDto(accessToken, refreshToken);
    }


    // Helpers

    public UserEntity getUserOrThrow(String username, String error) throws Exception {
        return userRepository.findByUsername(username).orElseThrow(() -> new Exception(error));
    }

    public UserEntity getUserOrThrow(Long id, String error) throws Exception {
        return userRepository.findById(id).orElseThrow(() -> new Exception(error));
    }

    public boolean isUserExists(String id) {
        return userRepository.findByUsername(id).isPresent();
    }

    public boolean isEmailExists(String email) {
        return userRepository.findByEmail(email).isPresent();
    }
}
