package org.samtar.cms.modules.accesscontrols.user.mapper;

import javax.annotation.processing.Generated;
import org.samtar.cms.modules.accesscontrols.user.dto.CreateUserDto;
import org.samtar.cms.modules.accesscontrols.user.dto.CreateUserResponse;
import org.samtar.cms.modules.accesscontrols.user.dto.UserProfileDto;
import org.samtar.cms.modules.accesscontrols.user.entity.UserEntity;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-04-27T23:49:50+0530",
    comments = "version: 1.6.3, compiler: Eclipse JDT (IDE) 3.46.0.v20260407-0427, environment: Java 21.0.10 (Eclipse Adoptium)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserEntity toEntity(CreateUserDto res) {
        if ( res == null ) {
            return null;
        }

        UserEntity userEntity = new UserEntity();

        userEntity.setEmail( res.email() );
        userEntity.setPassword( res.password() );
        userEntity.setUsername( res.username() );

        return userEntity;
    }

    @Override
    public CreateUserResponse tResponse(UserEntity entity) {
        if ( entity == null ) {
            return null;
        }

        Long id = null;
        String username = null;
        String email = null;

        id = entity.getId();
        username = entity.getUsername();
        email = entity.getEmail();

        UserProfileDto profileDto = null;

        CreateUserResponse createUserResponse = new CreateUserResponse( id, username, email, profileDto );

        return createUserResponse;
    }
}
