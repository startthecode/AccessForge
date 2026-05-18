package org.samtar.cms.modules.accesscontrols.users.dto.response;

import java.util.Map;

public record AuthTokenDto(
       Map<String,Object> accessToken,
       String  refreshToken
) {

}
