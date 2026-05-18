package org.samtar.cms.modules.accesscontrols.users.dto.response;

public record UserProfileResponseDto(
        String name,
        String bio,
        String lastname,

        String genderID,

        String branchID,

        String departmentID,

        String designationID
) {
}
