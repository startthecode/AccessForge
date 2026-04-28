package org.samtar.cms.modules.accesscontrols.user.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

public record CreateUserDto(
        @NotBlank
        String username,
        @NotBlank
        String password,
        @NotBlank
        String email,
        @Valid
        UserProfileDto profile
) {
}
