package org.samtar.cms.modules.accesscontrols.user.dto;

public record CreateUserResponse(
        Long id,
        String username,
        String email,
        UserProfileDto profileDto
) {}
