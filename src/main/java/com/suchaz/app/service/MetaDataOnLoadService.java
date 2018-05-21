package com.suchaz.app.service;

import com.suchaz.app.service.dto.MetaDataOnLoadDTO;

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
    
    
    /**
     * Get the all MetaData specially for Beta Version. Only SuchAZ Menu and Weekly Featured Prodcuts are being returned
     *
     * @return the entity
     */
    MetaDataOnLoadDTO loadAllMetaDataForBeta();

}
