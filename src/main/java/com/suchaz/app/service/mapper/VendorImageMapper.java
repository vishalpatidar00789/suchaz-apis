package com.suchaz.app.service.mapper;

import com.suchaz.app.domain.*;
import com.suchaz.app.service.dto.VendorImageDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity VendorImage and its DTO VendorImageDTO.
 */
@Mapper(componentModel = "spring", uses = {VendorMapper.class})
public interface VendorImageMapper extends EntityMapper<VendorImageDTO, VendorImage> {

    @Mapping(source = "vendor.id", target = "vendorId")
    @Mapping(source = "vendor.vendorName", target = "vendorVendorName")
    VendorImageDTO toDto(VendorImage vendorImage);

    @Mapping(source = "vendorId", target = "vendor")
    VendorImage toEntity(VendorImageDTO vendorImageDTO);

    default VendorImage fromId(Long id) {
        if (id == null) {
            return null;
        }
        VendorImage vendorImage = new VendorImage();
        vendorImage.setId(id);
        return vendorImage;
    }
}
