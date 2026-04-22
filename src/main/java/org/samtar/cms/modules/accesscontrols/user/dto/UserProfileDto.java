package org.samtar.cms.modules.accesscontrols.user.dto;

import jakarta.validation.constraints.NotBlank;

public record UserProfileDto(
        @NotBlank
        String name,
        @NotBlank

        String bio,
        String lastname,
        Long branchID,
        Long departmentID
) {

}
