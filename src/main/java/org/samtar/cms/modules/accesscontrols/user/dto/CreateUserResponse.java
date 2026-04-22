package org.samtar.cms.modules.accesscontrols.user.dto;

public record CreateUserResponse(
        String username,
        String password,
        String email,
        UserProfileDto profileDto
) {}
