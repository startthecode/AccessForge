package org.samtar.cms.modules.accesscontrols.user.mapper;

import javax.annotation.processing.Generated;
import org.samtar.cms.modules.accesscontrols.user.dto.request.CreateUserDto;
import org.samtar.cms.modules.accesscontrols.user.dto.response.CreateUserResponse;
import org.samtar.cms.modules.accesscontrols.user.dto.response.UserProfileResponseDto;
import org.samtar.cms.modules.accesscontrols.user.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-04-30T22:26:03+0530",
    comments = "version: 1.6.3, compiler: javac, environment: Java 25.0.2 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Autowired
    private UserProfileMapper userProfileMapper;

    @Override
    public UserEntity toEntity(CreateUserDto res) {
        if ( res == null ) {
            return null;
        }

        UserEntity userEntity = new UserEntity();

        userEntity.setEmail( res.email() );
        userEntity.setUsername( res.username() );

        return userEntity;
    }

    @Override
    public CreateUserResponse tResponse(UserEntity entity) {
        if ( entity == null ) {
            return null;
        }

        UserProfileResponseDto profile = null;
        Long id = null;
        String username = null;
        String email = null;

        profile = userProfileMapper.toDto( entity.getUserProfile() );
        id = entity.getId();
        username = entity.getUsername();
        email = entity.getEmail();

        CreateUserResponse createUserResponse = new CreateUserResponse( id, username, email, profile );

        return createUserResponse;
    }
}
