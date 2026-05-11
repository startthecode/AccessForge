package org.samtar.cms.modules.accesscontrols.permission.dto;

public record PermissionResDto(
        Long userid,
        String authority,
        String module
) {
}
