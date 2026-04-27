package org.samtar.cms.modules.accesscontrols.user.controller;


import org.samtar.cms.modules.accesscontrols.user.dto.CreateUserDto;
import org.samtar.cms.modules.accesscontrols.user.dto.CreateUserResponse;
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

    @PostMapping("/create")
    public ResponseEntity<CreateUserResponse> createUser(@RequestBody CreateUserDto reqBody){
        try {
            return  ResponseEntity.ok(userService.createUser(reqBody));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
