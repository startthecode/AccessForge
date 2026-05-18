package org.samtar.cms.modules.accesscontrols.users.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record UserProfileDto(
                @NotBlank String name,
                @NotBlank String bio,
                @NotBlank String lastname,
                @NotNull
                @Positive
                Long genderID

) {

}
