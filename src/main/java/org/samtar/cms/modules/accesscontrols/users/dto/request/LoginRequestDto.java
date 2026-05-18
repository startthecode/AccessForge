package org.samtar.cms.modules.accesscontrols.users.dto.request;

import jakarta.validation.constraints.NotBlank;

public record LoginRequestDto(
        @NotBlank
        String username,

        @NotBlank
        String password
) {
}
