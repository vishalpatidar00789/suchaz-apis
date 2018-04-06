package com.suchaz.app.service.mapper;

import com.suchaz.app.domain.*;
import com.suchaz.app.service.dto.ConsumerProfileHistoryDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity ConsumerProfileHistory and its DTO ConsumerProfileHistoryDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ConsumerProfileHistoryMapper extends EntityMapper<ConsumerProfileHistoryDTO, ConsumerProfileHistory> {



    default ConsumerProfileHistory fromId(Long id) {
        if (id == null) {
            return null;
        }
        ConsumerProfileHistory consumerProfileHistory = new ConsumerProfileHistory();
        consumerProfileHistory.setId(id);
        return consumerProfileHistory;
    }
}
