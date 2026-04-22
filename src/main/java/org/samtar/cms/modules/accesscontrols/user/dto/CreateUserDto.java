package org.samtar.cms.modules.accesscontrols.user.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateUserDto(
        @NotBlank
        String username,
        @NotBlank
        String password,
        @NotBlank
        String email,
        @Valid
        UserProfileDto profileDto
) {
}
