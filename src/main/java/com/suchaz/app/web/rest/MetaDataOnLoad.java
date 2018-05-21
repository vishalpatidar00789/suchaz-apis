package com.suchaz.app.web.rest;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;
import com.suchaz.app.service.MetaDataOnLoadService;
import com.suchaz.app.service.dto.MetaDataOnLoadDTO;

import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing UserProfile.
 */
@RestController
@RequestMapping("/api")
public class MetaDataOnLoad {

    private final Logger log = LoggerFactory.getLogger(MetaDataOnLoad.class);

    private final MetaDataOnLoadService metaDataOnLoadService;

    public MetaDataOnLoad(MetaDataOnLoadService metaDataOnLoadService) {
        this.metaDataOnLoadService = metaDataOnLoadService;
    }
    /**
     * GET  /metaData-OnLoad : get Meta Data.
     *
     * @return the ResponseEntity with status 200 (OK) and with body the metaDataOnLoadDTO, or with status 404 (Not Found)
     */
    @GetMapping("/metaData-OnLoad")
    @Timed
    public ResponseEntity<MetaDataOnLoadDTO> getMetaDataOnLoad() {
        log.debug("REST request to MetaDataOnLoad with Ip : {}", "User's IP");
        MetaDataOnLoadDTO metaDataOnLoadDTO = metaDataOnLoadService.loadAllMetaDataForBeta();
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(metaDataOnLoadDTO));
    }

}
