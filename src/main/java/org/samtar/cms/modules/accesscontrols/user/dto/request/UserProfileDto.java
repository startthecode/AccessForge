package org.samtar.cms.modules.accesscontrols.user.dto.request;

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
