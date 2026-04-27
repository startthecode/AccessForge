package org.samtar.cms.modules.accesscontrols.user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserProfileDto(
        @NotBlank
        String name,
        @NotBlank
        String bio,
        @NotBlank
        String lastname,

        @NotBlank
        Long gender,
        @NotNull
        Long branchID,
        @NotNull
        Long departmentID,
        @NotNull
        Long designationID

) {

}
