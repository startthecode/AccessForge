package org.samtar.cms.modules.accesscontrols.customModules.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateCldModReq(
        @NotNull(message = "Id cam not be blank")
   Long parentid,

        @NotBlank(message = "Child name can not be blank")
        String childname
) {
}
