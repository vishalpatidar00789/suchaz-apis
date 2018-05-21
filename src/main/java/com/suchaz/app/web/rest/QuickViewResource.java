package com.suchaz.app.web.rest;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;
import com.suchaz.app.service.QuickViewService;
import com.suchaz.app.service.dto.QuickViewDTO;

import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing UserProfile.
 */
@RestController
@RequestMapping("/api")
public class QuickViewResource {

    private final Logger log = LoggerFactory.getLogger(QuickViewResource.class);

    private final QuickViewService quickViewService;

    public QuickViewResource(QuickViewService quickViewService) {
        this.quickViewService = quickViewService;
    }
    /**
     * GET  /Quick View: get Quick View Data.
     *
     * @return the ResponseEntity with status 200 (OK) and with body the metaDataOnLoadDTO, or with status 404 (Not Found)
     */
   // @GetMapping("/QuickViewList/{idList}")
    @RequestMapping(value = "/QuickView/{idList}", method = RequestMethod.POST)
    @Timed
    public List<QuickViewDTO> getRangeOfQuickView(@PathVariable("idList") String listOfIds) {
        log.debug("REST request to getRangeOfQuickViewDTO from QuickViewDTO with Ip : {}", "User's IP");
        String[] strListOfIds = listOfIds.split(",");
        Long[] lngListOfIds = new Long[strListOfIds.length];
        for(int i=0;i<strListOfIds.length;i++)
        {
        	lngListOfIds[i] = Long.parseLong(strListOfIds[i]);
        }
        return quickViewService.findRangeOfItem(lngListOfIds);
    }
    
    @GetMapping("/QuickView/{id}")
    @Timed
    public ResponseEntity<QuickViewDTO> getQuickView(@PathVariable Long id) {
        log.debug("REST request to getQuickViewDTO from QuickViewDTO with Ip : {}", "User's IP");
        QuickViewDTO QuickViewDTO = quickViewService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(QuickViewDTO));
    }

}
