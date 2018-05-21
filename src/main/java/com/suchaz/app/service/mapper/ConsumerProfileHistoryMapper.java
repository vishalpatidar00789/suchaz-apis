package com.suchaz.app.service.mapper;

import org.mapstruct.Mapper;

import com.suchaz.app.domain.ConsumerProfileHistory;
import com.suchaz.app.service.dto.ConsumerProfileHistoryDTO;

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
