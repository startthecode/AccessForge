package org.samtar.cms.modules.accesscontrols.users.dto.response;

public record CreateUserResponse(
        Long id,
        String username,
        String email,
        UserProfileResponseDto profile
) {}
