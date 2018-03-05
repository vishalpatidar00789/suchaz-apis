package com.suchaz.app.service;

import com.suchaz.app.service.dto.MetaDataOnLoadDTO;
import com.suchaz.app.service.dto.MyAccountDTO;
import com.suchaz.app.service.dto.UserProfileDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing UserProfile.
 */
public interface MetaDataOnLoadService {

    /**
     * Get the all MetaData.
     *
     * @return the entity
     */
    MetaDataOnLoadDTO loadAllMetaData();

}
