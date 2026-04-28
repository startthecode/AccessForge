package org.samtar.cms.modules.accesscontrols.user.dto.request;

import jakarta.validation.constraints.NotBlank;

public record LoginRequestDto(
        @NotBlank
        String username,

        @NotBlank
        String password
) {
}
