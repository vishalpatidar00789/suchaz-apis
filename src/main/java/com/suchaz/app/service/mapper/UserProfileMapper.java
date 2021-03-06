package com.suchaz.app.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.suchaz.app.domain.UserProfile;
import com.suchaz.app.service.dto.UserProfileDTO;

/**
 * Mapper for the entity UserProfile and its DTO UserProfileDTO.
 */
@Mapper(componentModel = "spring", uses = {SuchAzUserMapper.class})
public interface UserProfileMapper extends EntityMapper<UserProfileDTO, UserProfile> {

    @Mapping(source = "suchAzUser.id", target = "suchAzUserId")
    @Mapping(source = "suchAzUser.email", target = "suchAzUserEmail")
    UserProfileDTO toDto(UserProfile userProfile);

    @Mapping(source = "suchAzUserId", target = "suchAzUser")
    UserProfile toEntity(UserProfileDTO userProfileDTO);

    default UserProfile fromId(Long id) {
        if (id == null) {
            return null;
        }
        UserProfile userProfile = new UserProfile();
        userProfile.setId(id);
        return userProfile;
    }
}
