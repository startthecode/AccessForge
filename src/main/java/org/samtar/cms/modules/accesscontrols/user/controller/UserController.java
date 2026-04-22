package org.samtar.cms.modules.accesscontrols.user.controller;


import org.samtar.cms.modules.accesscontrols.user.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController("/api/auth")
public class UserController {
    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


}
