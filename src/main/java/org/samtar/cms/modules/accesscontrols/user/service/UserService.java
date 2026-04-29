package org.samtar.cms.modules.accesscontrols.user.service;

import jakarta.transaction.Transactional;
import org.samtar.cms.common.exception.AuthException;
import org.samtar.cms.common.exception.UserException;
import org.samtar.cms.common.util.JwtUtils;
import org.samtar.cms.modules.accesscontrols.user.dto.request.CreateUserDto;
import org.samtar.cms.modules.accesscontrols.user.dto.request.LoginRequestDto;
import org.samtar.cms.modules.accesscontrols.user.dto.response.AuthTokenDto;
import org.samtar.cms.modules.accesscontrols.user.entity.UserEntity;
import org.samtar.cms.modules.accesscontrols.user.entity.UserProfileEntity;
import org.samtar.cms.modules.accesscontrols.user.mapper.UserMapper;
import org.samtar.cms.modules.accesscontrols.user.repository.UserRepository;
import org.samtar.cms.modules.accesscontrols.user.service.imps.UserPrincipleImps;
import org.samtar.cms.modules.shared.enums.Status;
import org.samtar.cms.modules.shared.enums.TokenTypes;
import org.samtar.cms.modules.shared.service.StatusService;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
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

    public UserService(UserRepository userRepository, UserMapper mapper, StatusService statusService, UserProfileService userProfileService, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtUtils jwtUtils) {
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
        accessToken.put("token", jwtUtils.generateToken(Map.of("username", reqBody.username()), TokenTypes.Access));
        String refreshToken = jwtUtils.generateToken(Map.of("username", reqBody.username()), TokenTypes.Refresh);
        userRepository.save(newUser);
        return new AuthTokenDto(accessToken, refreshToken);
    }


    public AuthTokenDto signin(LoginRequestDto reqBody) throws Exception {
        Authentication auth;
        try {
            auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(reqBody.username(), reqBody.password()));

        } catch (BadCredentialsException e) {
            throw AuthException.InvalidCredentials("Invalid username or password", null);

        } catch (LockedException e) {
            throw AuthException.InvalidCredentials("Your account is locked", null);

        } catch (DisabledException e) {
            throw AuthException.InvalidCredentials("Your account is disabled", null);

        }

        UserPrincipleImps principle = (UserPrincipleImps) auth.getPrincipal();
        assert principle != null;
        UserEntity user = principle.getUser();

        Map<String, Object> tokenData = new HashMap<>();
        tokenData.put("username", user.getUsername());

        String accessToken = jwtUtils.generateToken(tokenData, TokenTypes.Access);

        String refreshToken = jwtUtils.generateToken(tokenData, TokenTypes.Refresh);

        return new AuthTokenDto(Map.of("token", accessToken), refreshToken);
    }


    // Helpers
    public UserEntity getUserOrThrow(String username, String error) throws Exception {
        return userRepository.findByUsername(username).orElseThrow(UserException::userNotFound);
    }

    public UserEntity getUserOrThrow(Long id, String error) throws Exception {
        return userRepository.findById(id).orElseThrow(UserException::userNotFound);
    }

    public boolean isUserExists(String username) {
        return userRepository.existsByUsername(username);
    }

    public boolean isEmailExists(String email) {
        return userRepository.existsByEmail(email);
    }

}
