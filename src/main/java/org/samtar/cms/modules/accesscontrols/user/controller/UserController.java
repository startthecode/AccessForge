package org.samtar.cms.modules.accesscontrols.user.controller;


import org.samtar.cms.modules.accesscontrols.user.dto.request.CreateUserDto;
import org.samtar.cms.modules.accesscontrols.user.dto.request.LoginRequestDto;
import org.samtar.cms.modules.accesscontrols.user.dto.response.AuthTokenDto;
import org.samtar.cms.modules.accesscontrols.user.dto.response.CreateUserResponse;
import org.samtar.cms.modules.accesscontrols.user.service.UserService;
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
    public ResponseEntity<AuthTokenDto> signUp(@RequestBody CreateUserDto reqBody) {
        try {
            return ResponseEntity.ok(userService.signup(reqBody));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/signin")
    public ResponseEntity<Object> signIn(@RequestBody LoginRequestDto reqBody) {
        try {
            return ResponseEntity.ok(userService.signin(reqBody));
        } catch (Exception e) {
            return ResponseEntity.ok(e);
        }
    }

}
