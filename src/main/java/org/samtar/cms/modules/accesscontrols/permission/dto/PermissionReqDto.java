package org.samtar.cms.modules.accesscontrols.permission.dto;

import jakarta.validation.constraints.NotNull;
import org.samtar.cms.common.exception.GenericException;

public record PermissionReqDto(
        @NotNull
        Long authorityid,
        Long userid,
        Long customModule

) {

public PermissionReqDto{
    boolean user = userid() != null;
    boolean hasCustomModule = customModule() != null;
    if (user == hasCustomModule) {
        throw GenericException.genException("Exactly one of userid or Custom Module must be provided");
    }
}
}
