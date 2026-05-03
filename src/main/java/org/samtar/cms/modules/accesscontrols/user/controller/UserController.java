package org.samtar.cms.modules.accesscontrols.user.controller;


import jakarta.validation.Valid;
import org.samtar.cms.common.util.AuthCookieUtils;
import org.samtar.cms.modules.accesscontrols.user.dto.request.CreateUserDto;
import org.samtar.cms.modules.accesscontrols.user.dto.request.LoginRequestDto;
import org.samtar.cms.modules.accesscontrols.user.dto.response.AuthTokenDto;
import org.samtar.cms.modules.accesscontrols.user.service.UserService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class UserController {
    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<AuthTokenDto> signUp(@Valid @RequestBody CreateUserDto reqBody) throws Exception {
             AuthTokenDto response = userService.signup(reqBody);
            return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, AuthCookieUtils.addRefreshToken(response.refreshToken()).toString()).body(response);
    }

    @PostMapping("/signin")
    public ResponseEntity<Object> signIn(@Valid @RequestBody LoginRequestDto reqBody) throws Exception {
        AuthTokenDto response = userService.signin(reqBody);
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, AuthCookieUtils.addRefreshToken(response.refreshToken()).toString()).body(response);
    }


}
