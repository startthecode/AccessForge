package org.samtar.cms.modules.accesscontrols.customModules.dto;

import jakarta.validation.constraints.NotNull;

public record AddUserToChildReq(
        @NotNull
        Long userID,

        @NotNull
        Long childModuleID
) {
}
